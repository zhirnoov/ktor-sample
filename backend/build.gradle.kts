val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.22"
    id("io.ktor.plugin") version "2.1.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.22"
    id ("com.google.cloud.tools.jib") version "3.3.1"
}

group = "com.zhirnoov"
version = "0.0.1"
application {
    mainClass.set("com.zhirnoov.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-host-common-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")

    implementation("com.h2database:h2:2.1.214")

    implementation ("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation ("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    implementation ("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation ("com.zaxxer:HikariCP:5.0.1")
}