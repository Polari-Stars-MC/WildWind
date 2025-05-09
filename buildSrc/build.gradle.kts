plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.squareup:kotlinpoet:1.12.0")
}

kotlin {
    jvmToolchain(21)
}