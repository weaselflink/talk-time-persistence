@file:Suppress("PropertyName")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val junit_version: String by project
val strikt_version: String by project

plugins {
    kotlin("jvm")
    id("com.adarshr.test-logger")
    id("com.github.ben-manes.versions")
}

group = "talks.timepersistence"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:$junit_version")
    testImplementation("io.strikt:strikt-core:$strikt_version")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}