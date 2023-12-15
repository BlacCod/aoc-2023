plugins {
    kotlin("jvm") version "1.9.20"
    id("com.moriatsushi.cacheable") version "0.0.2"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC")
    implementation("com.moriatsushi.cacheable:cacheable-core:0.0.2")


}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
