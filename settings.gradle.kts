pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.gradle.enterprise") version ("3.16.2")
}

rootProject.name = "avro4k-core"

include("benchmark")

gradleEnterprise {
    if (System.getenv("CI") != null) {
        buildScan {
            publishAlways()
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
        }
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("kotlin", "2.0.0")
            version("jvm", "21")

            library("apache-avro", "org.apache.avro", "avro").version("1.11.3")
            library("okio", "com.squareup.okio", "okio").version("3.9.0")

            val kotlinxSerialization = "1.7.0"
            library("kotlinx-serialization-core", "org.jetbrains.kotlinx", "kotlinx-serialization-core").version(kotlinxSerialization)
            library("kotlinx-serialization-json", "org.jetbrains.kotlinx", "kotlinx-serialization-json").version(kotlinxSerialization)

            val kotestVersion = "5.9.1"
            library("kotest-core", "io.kotest", "kotest-assertions-core").version(kotestVersion)
            library("kotest-json", "io.kotest", "kotest-assertions-json").version(kotestVersion)
            library("kotest-junit5", "io.kotest", "kotest-runner-junit5").version(kotestVersion)
            library("kotest-property", "io.kotest", "kotest-property").version(kotestVersion)

            plugin("dokka", "org.jetbrains.dokka").version("1.9.20")
            plugin("kotest", "io.kotest").version("0.4.11")
            plugin("github-versions", "com.github.ben-manes.versions").version("0.51.0")
            plugin("nexus-publish", "io.github.gradle-nexus.publish-plugin").version("2.0.0")
            plugin("spotless", "com.diffplug.spotless").version("6.25.0")
            plugin("kover", "org.jetbrains.kotlinx.kover").version("0.8.1")
            plugin("binary-compatibility-validator", "org.jetbrains.kotlinx.binary-compatibility-validator").version("0.14.0")
        }
    }
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}