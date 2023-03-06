import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"

    `maven-publish`
}

group = "net.gloryx"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://maven.gloryx.net/all")
}

kotlin {
    jvm()
    /*
    ios()
    macosX64()
    macosArm64()
     */
    sourceSets {
        //forEach { it.languageSettings.enableLanguageFeature("ContextReceivers")}

        val commonMain by getting {
            dependencies {
                implementation(libs.bundles.ktor.plugins)
                implementation(libs.ratelimit)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.engine.java)
            }
        }

        /*
        val appleMain by creating {
            dependsOn(commonMain)

            dependencies {
                implementation(libs.ktor.engine.apple)
            }
        }

        val iosMain by getting {
            dependsOn(appleMain)
        }

        val macMain by creating {
            dependsOn(appleMain)
        }

        val macosX64Main by getting {
            dependsOn(macMain)
        }
        val macosArm64Main by getting {
            dependsOn(macMain)
        }
         */
    }
}

/*
tasks.withType<KotlinCompile> {
    compilerOptions.freeCompilerArgs.add("-Xcontext-receivers")
}
 */