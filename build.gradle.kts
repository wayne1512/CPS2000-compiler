plugins {
    id("java")
    id("application")
}

apply(plugin = "java")


group = "org.example"
version = "1.0-SNAPSHOT"

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

application {
    mainClass.set("Main")
}