package angu.labs.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import setupKMP
import applyKotlinOptions

class PluginKmp : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            plugins.apply("org.jetbrains.kotlin.multiplatform")
            plugins.apply("com.android.library")
            setupKMP()
            applyKotlinOptions()
        }
    }
}