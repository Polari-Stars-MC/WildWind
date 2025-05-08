import org.slf4j.event.Level

plugins {
    `java-library`
    `maven-publish`
    idea
    base
    alias(libs.plugins.mod.dev.gradle)
}

val mcVersion: String by rootProject
val mcVersionRange: String by rootProject
val modRootName: String by rootProject
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
    val modid = "${rootProject.name.lowercase().replace(" ", "_")}_${project.name.lowercase().replace(" ", "_")}"
    val modName = "$modRootName: ${project.name}"
    val modVersion: String by project

    fun appendFileContent(sourceFile: File, targetFile: File) {
        targetFile.appendText("\n\n")
        targetFile.appendText(sourceFile.readText(Charsets.UTF_8), Charsets.UTF_8)
    }

    fun processFolder(sourceFolder: File, outputFolder: File) {
        if(sourceFolder.exists().not().or(sourceFolder.isDirectory.not())) {
            return
        }

        sourceFolder.walk().filter { it.isFile }.forEach {
            val relativePath = sourceFolder.toPath().relativize(it.toPath())
            val targetFile = outputFolder.toPath().resolve(relativePath).toFile()

            targetFile.parentFile.mkdirs()

            if (targetFile.exists()) {
                appendFileContent(it, targetFile)
            } else {
                it.copyTo(targetFile)
            }
        }
    }

    fun mergePath(rootFile: File, projectFile: File): File {
        val tFile =file("build/neo")
        tFile.deleteRecursively()
        tFile.mkdirs()
        processFolder(rootFile, tFile)
        processFolder(projectFile, tFile)
        return tFile
    }

    base {
        archivesName = modid
    }
    description = modName
    version = "1.21.1-$modVersion"
    group = modGroupId
    val copyJar = tasks.register<Copy>("copyToRootLibs") {
        into("${rootProject.file("build")}/libs")
        from(tasks.jar.get().outputs.files)
    }
    tasks.build {
        dependsOn(copyJar.get())
    }

    val datagenDir = rootProject.file("src/a_generated/${project.name}")
    datagenDir.mkdirs()
    val resourceDir = rootProject.file("src/${project.name}/resources")
    val javaDir = rootProject.file("src/${project.name}/java")
    val groupDir = javaDir
        .resolve(group.toString().replace(".", "/"))
        .resolve(rootProject.name.lowercase().replace(" ", ""))
        .resolve(project.name.lowercase().replace(" ", ""))
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

    val tFile = mergePath(rootTemplate, projectTemplate)
    val at = tFile.resolve("META-INF/accesstransformer.cfg")
    file("build/generated/sources/modMetadata").deleteRecursively()
    val generateModMetadata = tasks.register<ProcessResources>("generateMetadata") {
        val replaceProperties = mapOf(
            "minecraft_version" to mcVersion,
            "minecraft_version_range" to mcVersionRange,
            "neo_version" to neoVersion,
            "neo_version_range" to neoVersionRange,
            "loader_version_range" to loaderVersionRange,
            "mod_id" to base.archivesName.get(),
            "mod_name" to project.description,
            "mod_license" to modLicense,
            "mod_version" to project.version.toString(),
            "mod_authors" to modAuthors,
            "mod_description" to base.archivesName.get(),
            "accessTransformers" to if(at.readBytes().isEmpty()) "" else """
                [[accessTransformers]]
                file="META-INF/accesstransformer.cfg"
            """.trimIndent()
        )
        inputs.properties(replaceProperties)
        expand(replaceProperties)
        from(tFile)
        into("build/generated/sources/modMetadata")
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
        if (!project.name.equals("Material")) {
            implementation(project(":Material"))
        }
        if (project.name.equals("All In All")) {
            val projectNames = listOf(
                "Construction",
                "Material",
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
    neoForge.ideSyncTask(generateModMetadata)
    sourceSets.main {
        java {
            srcDir(javaDir)
        }
        resources {
            srcDir(resourceDir)
            srcDir(datagenDir)
            srcDir(generateModMetadata)
        }
    }

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

val dataAll by tasks.registering {
    subprojects.forEach {
        dependsOn(it.tasks.getByName("runData"))
    }
}

val buildAll by tasks.registering {
    subprojects.forEach {
        dependsOn(it.tasks.build)
    }
}