@file:Suppress("PropertyName")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val junit_version: String by project
val test_containers_version: String by project

plugins {
    kotlin("jvm")
    id("com.github.ben-manes.versions")
}

group = "talks.timepersistence"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-nop:1.7.32")
    implementation("org.postgresql:postgresql:42.2.23")
    implementation("org.testcontainers:testcontainers:$test_containers_version")
    implementation("org.testcontainers:postgresql:$test_containers_version")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
