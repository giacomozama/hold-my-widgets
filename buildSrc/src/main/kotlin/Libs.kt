
object Libs {
    const val jUnit = "junit:junit:4.13.2"

    const val desugaring = "com.android.tools:desugar_jdk_libs:1.1.5"
    const val okHttp = "com.squareup.okhttp3:okhttp:4.9.3"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.9.1"

    object AndroidX {

        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val activityCompose = "androidx.activity:activity-compose:1.4.0"

        const val androidJUnit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"

        object Compose {

            const val ui = "androidx.compose.ui:ui:${Versions.Compose}"
            const val material = "androidx.compose.material:material:${Versions.Compose}"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose}"
            const val uiTestJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.Compose}"
        }

        object Lifecycle {
            private const val version = "2.4.1"

            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        }
    }

    object Hilt {
        private const val version = "2.41"

        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
    }

    object Kotlin {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
    }
}