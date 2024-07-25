import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.gradleup.nmcp)
    alias(libs.plugins.org.jetbrains.dokka)
    id("maven-publish")
    id("signing")
}

android {
    namespace = "com.megatilus.crouton"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    implementation(libs.ui)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.foundation)
    implementation(libs.material3)
}

val pluginAndroidLibrary = "com.android.library"
val publicationName = "release"

afterEvaluate {
    configure<PublishingExtension> {
        publications {
            register<MavenPublication>(publicationName) {
                groupId = "io.github.megatilus"
                artifactId = "crouton"
                version = "1.2.0"

                if (project.plugins.hasPlugin(pluginAndroidLibrary)) {
                    from(components[publicationName])
                } else {
                    from(components["java"])
                }

                artifact(tasks.register("${name}JavadocJar", Jar::class) {
                    archiveClassifier.set("javadoc")
                    archiveExtension.set("jar")
                    from(tasks.getByName("dokkaJavadoc"))
                    dependsOn(tasks.getByName("dokkaJavadoc"))
                })

                pom {
                    name.set("Crouton")
                    description.set("Lightweight library for displaying Toast messages with various styles and customization options.")
                    inceptionYear.set("2024")
                    url.set("https://github.com/megatilus/crouton-library")

                    licenses {
                        license {
                            name.set("ISC License")
                            url.set("https://github.com/megatilus/crouton-library/blob/main/LICENSE")
                            distribution.set("repo")
                        }
                    }
                    developers {
                        developer {
                            id.set("megatilus")
                            name.set("Nutilus")
                            url.set("https://github.com/megatilus")
                        }
                    }
                    scm {
                        url.set("https://github.com/megatilus/crouton-library")
                        connection.set("scm:git:git://github.com/megatilus/crouton-library.git")
                        developerConnection.set("scm:git:ssh://git@github.com/megatilus/crouton-library.git")
                    }
                }
            }
        }
    }

    configure<SigningExtension> {
        useGpgCmd()
        sign(publishing.publications[publicationName])
    }

    nmcp {
        publish("release") {
            val properties = File(rootDir, "local.properties")
            if (properties.exists()) {
                val localProperties = properties.inputStream().use {
                    Properties().apply { load(it) }
                }

                username = localProperties.getProperty("mavenCentralUsername")
                password = localProperties.getProperty("mavenCentralPassword")

                publicationType = "USER_MANAGED"
            }
        }
    }
}