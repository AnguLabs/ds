package angu.labs.plugins

import applyKotlinOptions
import org.gradle.api.Plugin
import org.gradle.api.Project
import setupAndroidModule

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("com.android.application")
            plugins.apply("org.jetbrains.kotlin.android")
            setupAndroidModule()
            applyKotlinOptions()
        }
    }
}