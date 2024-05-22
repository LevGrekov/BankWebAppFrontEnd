import org.jetbrains.compose.desktop.application.dsl.TargetFormat
val ktor_version = "2.3.10"
val kotlinx_coroutines = "1.8.1"

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.22"
    id("org.jetbrains.compose")
}

group = "ru.levgrekov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("br.com.devsrsouza.compose.icons.jetbrains:line-awesome:1.0.0")
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "BankFrontend"
            packageVersion = "1.0.0"
        }
    }
}
