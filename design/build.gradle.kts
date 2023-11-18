import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("angu.labs.kmp")
    id("org.jetbrains.compose")
}

kotlin {
    explicitApi()

    sourceSets {
        val desktopMain by getting

        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
    }
}

android {
    namespace = "angu.labs.ds.app"

//    defaultConfig {
//        minSdk = libs.versions.android.minSdk.get().toInt()
//        compileSdk = libs.versions.android.compileSdk.get().toInt()
//    }
}