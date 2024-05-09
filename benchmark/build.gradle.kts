plugins {
    java
    kotlin("jvm") version libs.versions.kotlin
    id("org.jetbrains.kotlinx.benchmark") version "0.4.10"
    kotlin("plugin.allopen") version libs.versions.kotlin
    kotlin("plugin.serialization") version libs.versions.kotlin
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

benchmark {
    configurations {
        named("main") {
            reportFormat = "text"
        }
    }
    targets {
        register("main")
    }
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.10")

    val jacksonVersion = "2.17.0"
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-avro:$jacksonVersion")

    implementation(project(":"))
}