import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.github.ben-manes.versions") version "0.42.0"
}

android {
    compileSdk = Versions.CompileSdk

    defaultConfig {
        applicationId = "me.zama.holdmywidgets"
        minSdk = 23
        targetSdk = Versions.TargetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "me.zama.holdmywidgets.HoldMyWidgetsTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    coreLibraryDesugaring(Libs.desugaring)

    implementation(Libs.okHttp)

    implementation(Libs.AndroidX.coreKtx)

    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.uiToolingPreview)

    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)
    implementation(Libs.AndroidX.activityCompose)

    implementation(Libs.Hilt.android)

    kapt(Libs.Hilt.compiler)
    kaptAndroidTest(Libs.Hilt.compiler)

    testImplementation(Libs.jUnit)
    androidTestImplementation(Libs.AndroidX.androidJUnit)
    androidTestImplementation(Libs.AndroidX.espresso)
    androidTestImplementation(Libs.AndroidX.Compose.uiTestJUnit)
    androidTestImplementation(Libs.Hilt.testing)

    debugImplementation(Libs.AndroidX.Compose.uiTooling)
    debugImplementation(Libs.leakCanary)
}

kapt {
    correctErrorTypes = true
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    val regex = """^[0-9,.v-]+(-r)?$""".toRegex()
    val stableKeywords = setOf("RELEASE", "FINAL", "GA")

    fun isNonStable(version: String) = stableKeywords
        .none(version.toUpperCase()::contains)
        && !regex.matches(version)

    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}