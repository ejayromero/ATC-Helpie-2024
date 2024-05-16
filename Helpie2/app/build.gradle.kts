plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("kotlin-kapt")
}

android {
    namespace = "com.example.helpie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.helpie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    // Retrofit with Kotlin serialization Converter

    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.okhttp)

// Kotlin serialization
    implementation(libs.kotlinx.serialization.json)

    //xml
    implementation(libs.simple.xml)
    implementation(libs.converter.simplexml)
    implementation(libs.okhttp.v493)

    //ojp implementation
   // implementation(libs.ojp.android)

    //planificator
    implementation(libs.annotation)
    implementation(libs.core)
    kapt(libs.processor)
    implementation(libs.converter.htmlescape)
    //implementation(libs.timber)
    implementation(libs.joda.time)

    implementation(libs.koin.core.v330)
    implementation(libs.insert.koin.koin.android)

    implementation(libs.okhttp.v4120)
    implementation(libs.logging.interceptor)

    implementation(libs.retrofit.converter.v0813)

    implementation(libs.gson)

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.0")


}