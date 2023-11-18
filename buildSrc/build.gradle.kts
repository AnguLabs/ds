plugins {
    `kotlin-dsl`
}

repositories {
    google{
        mavenContent {
            includeGroupByRegex(".*google.*")
            includeGroupByRegex(".*android.*")
        }
    }
    mavenCentral()
    gradlePluginPortal()
}

gradlePlugin {
    plugins.register("plugin-kmp") {
        id = "angu.labs.kmp"
        implementationClass = "angu.labs.plugins.PluginKmp"
    }

    plugins.register("android-application") {
        id = "angu.labs.android.application"
        implementationClass = "angu.labs.plugins.AndroidApplicationPlugin"
    }
}

dependencies {
    implementation(libs.plugin.android)
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.detekt)
    implementation(libs.plugin.ktlint)
    implementation(libs.plugin.multiplatform.compose)
}