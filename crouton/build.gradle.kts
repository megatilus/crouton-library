import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.dokka") version "1.9.20"
    id("maven-publish")
    id("signing")
    id("com.gradleup.nmcp") version("0.0.8")
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:1.6.8")
    implementation("androidx.annotation:annotation:1.8.0")
    implementation("androidx.compose.animation:animation:1.6.8")
    implementation("androidx.compose.foundation:foundation:1.6.8")
    implementation("androidx.compose.material3:material3:1.2.1")
}

//val publicationName = "release"

/*publishing {
    configure<PublishingExtension> {
        publications {
            create<MavenPublication>(publicationName) {
                groupId = "io.github.megatilus"
                artifactId = "crouton"
                version = "1.0.2"

                artifact(tasks.register("${name}JavadocJar", Jar::class) {
                    archiveClassifier.set("javadoc")
                    archiveExtension.set("jar")
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
}

nmcp {
    publish(publicationName) {
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
}*/

val pluginAndroidLibrary = "com.android.library"
val publicationName = "release"

/*val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")

    if (project.plugins.hasPlugin(pluginAndroidLibrary)) {
        val libExt = checkNotNull(project.extensions.findByType(com.android.build.gradle.LibraryExtension::class.java))
        val libMainSourceSet = libExt.sourceSets.getByName("main")

        from(libMainSourceSet.java.srcDirs)
    } else {
        val sourceSetExt = checkNotNull(project.extensions.findByType(SourceSetContainer::class.java))
        val mainSourceSet = sourceSetExt.getByName("main")

        from(mainSourceSet.java.srcDirs)
    }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")

    val dokkaJavadocTask = tasks.getByName("dokkaJavadoc")

    from(dokkaJavadocTask)
    dependsOn(dokkaJavadocTask)
}*/

afterEvaluate {
    configure<PublishingExtension> {
        publications {
            register<MavenPublication>(publicationName) {
                groupId = "io.github.megatilus"
                artifactId = "crouton"
                version = "1.1.0"

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