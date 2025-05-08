import java.util.Properties

plugins {
    `java-library`
    `maven-publish`
    idea
    base
    alias(libs.plugins.mod.dev.gradle)
}

val mcVersion: String by rootProject
val mcVersionRange: String by rootProject
val modGroupId: String by rootProject
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
    val modid: String = "wild_wind_${project.name.lowercase()}"
    val modName: String = "Wild wind: ${project.name}"
    val modVersion: String by project

    base {
        archivesName = modid
    }
    description = modName
    version = modVersion
    val copyJar = tasks.register<Copy>("copyToRootLibs") {
        into(rootProject.tasks.jar.get().outputs.files)
        from(tasks.jar.get().outputs.files)
    }
    tasks.build {
        dependsOn(copyJar.get())
    }
    neoForge {
        version = neoVersion
        parchment {
            minecraftVersion = parchmentMinecraftVersion
                .replace("{mcVersion}", mcVersion)
            mappingsVersion = parchmentMappingsVersion
        }
        val datagenDir = rootProject.file("src/${project.name}/generated")
        datagenDir.mkdirs()

    }
}