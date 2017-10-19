import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven("http://130.10.7.123:8081/repository/maven-snapshots/")
        maven("http://130.10.8.208/repository/bksx-maven/")
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:1.5.7.RELEASE")
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.1")
    }
}

apply {
    plugin("org.springframework.boot")
    plugin("org.junit.platform.gradle.plugin")
}

plugins {
    val kotlinVersion = "1.1.51"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("io.spring.dependency-management") version "1.0.3.RELEASE"
}

version = "1.0.0-SNAPSHOT"

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}

repositories {
    maven("http://130.10.7.123:8081/repository/maven-snapshots/")
    maven("http://130.10.8.208/repository/bksx-maven/")
    mavenCentral()
    //maven("http://repo.spring.io/milestone")
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.jetbrains.kotlin:kotlin-stdlib")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    testCompile("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    compile ("com.sx:bksx-redis:v0.9.3.20171016-20171017.071049-3:jdk6")
}

