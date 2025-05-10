import org.slf4j.event.Level
import java.util.Properties

plugins {
    `java-library`
    `maven-publish`
    idea
    base
    alias(libs.plugins.mod.dev.gradle)
    id("tasks-merge")
}
val mcVersion: String by rootProject
val mcVersionRange: String by rootProject
val modGroupId: String by rootProject
val modAuthors: String by rootProject
val modLicense: String by rootProject
val neoVersion: String by rootProject
val neoVersionRange: String by rootProject
val loaderVersionRange: String by rootProject
val parchmentMappingsVersion: String by rootProject
val parchmentMinecraftVersion: String by rootProject
allprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "java-library")
    apply(plugin = "idea")
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://maven.neoforged.net/releases")
    }
    java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    idea {
        module {
            isDownloadSources = true
            isDownloadJavadoc = true
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }
}

val lib = libs

subprojects {
    apply(plugin = lib.plugins.mod.dev.gradle.get().pluginId)

    val props = Properties()
    val propsRoot = Properties()
    val pp = file("gradle.properties")
    val ppRoot = rootProject.file("gradle.properties")
    if (pp.exists()) {
        pp.bufferedReader(Charsets.UTF_8).use {
            props.load(it)
        }
    }
    if (ppRoot.exists()) {
        ppRoot.bufferedReader(Charsets.UTF_8).use {
            propsRoot.load(it)
        }
    }




    val modId: String by project
    val modName: String by project
    val modVersion: String by project
    val modClassPrefix: String by project


    base {
        archivesName = modId
    }
    description = modName
    version = "$mcVersion-$modVersion"
    group = modGroupId

    val replaceProperties: Map<String, String> = mapOf(
        "minecraft_version" to mcVersion,
        "minecraft_version_range" to mcVersionRange,
        "neo_version" to neoVersion,
        "neo_version_range" to neoVersionRange,
        "loader_version_range" to loaderVersionRange,
        "mod_id" to base.archivesName.get(),
        "mod_name" to project.description!!,
        "mod_license" to modLicense,
        "mod_version" to project.version.toString(),
        "mod_authors" to modAuthors,
        "mod_description" to base.archivesName.get(),
        "mod_class_prefix" to modClassPrefix,
    )





    val copyJar = tasks.register<Copy>("copyToRootLibs") {
        into("${rootProject.file("build")}/libs")
        from(tasks.jar.get().outputs.files)
    }
    tasks.build {
        dependsOn(copyJar.get())
    }

    // val a: KFunction1<File, Any> = rootProject::file
    val datagenDir = rootProject.file("src/a_generated/${project.name}")
    datagenDir.mkdirs()
    val resourceDir = rootProject.file("src/${project.name}/resources")
    val javaDir = rootProject.file("src/${project.name}/java")
    val groupDir = javaDir
        .resolve(group.toString().replace(".", "/"))
        .resolve(base.archivesName.get())
    groupDir.mkdirs()

    resourceDir.mkdirs()
    val rootTemplate = rootProject.file("src/templates/rootProject")
    rootTemplate.mkdirs()
    val projectTemplate = rootProject.file("src//templates/${project.name}")
    projectTemplate.mkdirs()
    val libsDir = rootProject.file("libs/${project.name}")
    libsDir.mkdirs()
    val jarJarDir = libsDir.resolve("jarJar")
    jarJarDir.mkdirs()

    tasks.jarJar {
        enabled = true
    }


    val templateResourceMergeDir = replaceProperties
        .mergePath(file("build/ww"),rootTemplate, projectTemplate)


    val at = templateResourceMergeDir.resolve("META-INF/accesstransformer.cfg")
    file("build/generated/sources/modMetadata").deleteRecursively()
    val templateJavaCode = rootProject.file("src/templates/basejava")

    val templateExistsJavaCode = rootProject.file("src/templates/basecheckjava")
    val templateExistsJavaCodePre = file("build/templates/basecheckjava")
    val templateExistsJavaCodePrePre = file("build/templates/basecheckjava-pre")
    println(templateExistsJavaCodePrePre)
    replaceProperties.processFolder(templateExistsJavaCode, templateExistsJavaCodePre, false)
    templateExistsJavaCodePre.discrepancy(templateExistsJavaCodePrePre)

    val existsGeneratedTemplateJava = tasks.register<Copy>("existsGeneratedTemplateJava") {
        from(templateExistsJavaCodePre)
        inputs.properties(replaceProperties)
        expand(replaceProperties)
        into(javaDir)
    }
    val genetatedTemplateJava = tasks.register<Copy>("genetatedTemplateJava") {

        val targetJavaCode = project.file("build/java-pre/javaModMetadata")
        targetJavaCode.deleteRecursively()
        targetJavaCode.mkdirs()
        if (templateJavaCode.exists().and(templateJavaCode.isDirectory)) {
            templateJavaCode.walk().filter { it.isFile }.forEach {
                var t = it.absolutePath
                replaceProperties.forEach { k, v ->
                    t = t.replace("\${$k}", v)
                }
                val relativePath = templateJavaCode.toPath().relativize(File(t).toPath())
                val outputPath = targetJavaCode.toPath().resolve(relativePath).toFile()
                outputPath.parentFile.mkdirs()
                var s = it.readText(Charsets.UTF_8)
                replaceProperties.forEach { k, v ->
                    s = s.replace("\${$k}", v)
                }
                outputPath.writeText(s)
            }
        }
        from(targetJavaCode.absolutePath)
        into("build/java/javaModMetadata")
    }

    val generateModMetadata = tasks.register<ProcessResources>("generateMetadata") {

        inputs.properties(replaceProperties)
        expand(replaceProperties)
        from(templateResourceMergeDir)
        into("build/generated/sources/modMetadata")
    }

    sourceSets.main {
        java {
            srcDir(javaDir)
            srcDir(genetatedTemplateJava)
        }
        resources {
            srcDir(resourceDir)
            srcDir(datagenDir)
            srcDir(generateModMetadata)
        }
    }

    if (at.readBytes().isNotEmpty()) {
        neoForge.setAccessTransformers(at)
    }
    repositories {
        flatDir {
            dir(libsDir.absolutePath)
        }
    }

    dependencies {
        if (libsDir.listFiles()!!.isNotEmpty()) {
            implementation(fileTree(baseDir = libsDir.absolutePath) {
                include("*.jar")
            })
        }
        if (jarJarDir.listFiles()!!.isNotEmpty()) {
            jarJar(fileTree(baseDir = jarJarDir.absolutePath) {
                include("*.jar")
            })
        }
        if (!project.name.equals("Agricultural")) {
            implementation(project(":Agricultural"))
        }
        if (project.name.equals("All In All")) {
            val projectNames = listOf(
                "Deco",
                "Adventure",
                "Agricultural",
                "Vanilla Plus Plus"
            )
            projectNames.forEach {
                implementation(project(":$it"))
                jarJar(project(":$it"))
            }
        }
    }

    tasks.jar {
        exclude(".cache")
        if (at.readBytes().isEmpty()) {
            exclude("META-INF/accesstransformer.cfg")
        }
    }
    neoForge.ideSyncTask(genetatedTemplateJava)
    neoForge.ideSyncTask(generateModMetadata)
    neoForge.ideSyncTask(existsGeneratedTemplateJava)


    neoForge {
        version = neoVersion
        parchment {
            minecraftVersion = parchmentMinecraftVersion
                .replace("{mcVersion}", mcVersion)
            mappingsVersion = parchmentMappingsVersion
        }


        runs {
            configureEach {
                systemProperty("forge.logging.markers", "REGISTRIES")
                logLevel = Level.DEBUG
            }
            listOf("client", "server", "data", "gameTestServer").forEach {
                register(it) {
                    gameDirectory.set(rootProject.file("runs/${project.name}/${it}"))
                    if ("data" != it) {
                        systemProperty("neoforge.enabledGameTestNamespaces", base.archivesName.get())
                    }
                    when (it) {
                        "client" -> client()
                        "gameTestServer" -> type = "gameTestServer"
                        "server" -> {
                            server()
                            programArgument("--nogui")
                        }
                        "data" -> {
                            data()
                            programArguments.addAll(listOf(
                                "--mod", base.archivesName.get(),
                                "--all",
                                "--output", datagenDir.absolutePath,
                                "--existing", resourceDir.absolutePath
                            ))
                            environment("datagen", "true")
                        }
                    }
                }
            }
        }
        mods {
            register(base.archivesName.get()) {
                sourceSet(sourceSets.main.get())
            }
        }
    }
}

