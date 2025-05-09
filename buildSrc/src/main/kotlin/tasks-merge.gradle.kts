import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.registering

val dataAll by tasks.registering {
    subprojects.forEach {
        if (!project.name.equals("All In All")) {
            dependsOn(it.tasks.getByName("runData"))
        }
    }
}

val buildAll by tasks.registering {
    subprojects.forEach {
        dependsOn(it.tasks.getByName("build"))
    }
}