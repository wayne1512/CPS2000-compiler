import org.gradle.jvm.tasks.Jar


plugins {
    id("java")
    id("application")
}

apply(plugin = "java")


group = "mt.wayne"


application {
    mainClass.set("Main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.opencsv:opencsv:5.7.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.8.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {

    manifest.attributes["Main-Class"] = "Main"
    val dependencies = configurations
            .runtimeClasspath
            .get()
            .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks {
    "build" {
        dependsOn(jar)
    }
}