import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

private fun BaseExtension.setupAndroid() {
    compileSdkVersion(34)
    defaultConfig {
        minSdk = 26
        targetSdk = 34

        versionCode = 1
        versionName = "0.0.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

fun Project.setupAndroidModule() {
    val androidExtension: BaseExtension = extensions.findByType<LibraryExtension>()
        ?: extensions.findByType<com.android.build.gradle.AppExtension>()
        ?: error("Could not found Android application or library plugin applied on module $name")

    androidExtension.apply {
        setupAndroid()

        testOptions {
            unitTests.all {
                it.useJUnitPlatform()
            }
        }
    }
}

fun Project.setupKMP() {
    plugins.withType<org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper> {
        extensions.configure<KotlinMultiplatformExtension> {
            explicitApi()

            androidTarget()
            iosX64()
            iosArm64()
            iosSimulatorArm64()
            jvm("desktop")
        }

        findAndroidExtension().apply {
            setupAndroid()
            sourceSets["main"].apply {
                res.srcDirs("src/androidMain/resources")
                manifest.srcFile("src/androidMain/AndroidManifest.xml")
            }
        }

    }

    configure<KotlinMultiplatformExtension> {
        jvmToolchain(17)
    }

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}

fun Project.applyKotlinOptions() {
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs += "-Xcontext-receivers"
        }
    }
}

private fun Project.findAndroidExtension(): BaseExtension =
    extensions.findByType<LibraryExtension>()
        ?: extensions.findByType<com.android.build.gradle.AppExtension>()
        ?: error("Could not found Android application or library plugin applied on module $name")