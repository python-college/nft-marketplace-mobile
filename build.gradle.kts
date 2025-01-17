// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    application
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    kotlin("plugin.serialization") version  "2.0.21"
    kotlin("jvm") version "2.0.21"
}

buildscript {
    extra["hilt_version"] = "2.51.1"
    val hilt_version: String by rootProject.extra
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
    }
}