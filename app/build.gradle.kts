plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.serialization)

}

android {
    namespace = "org.mahd_e_learning_platform"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.mahd_e_learning_platform"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}
kotlin {
    jvmToolchain(17)
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)


    //Retrofit
    implementation (libs.retrofit)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // ViewModel utilities for Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    ksp(libs.androidx.lifecycle.compiler)
    //Serialization
    implementation(libs.kotlinx.serialization)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
//    implementation(libs.androidx.hilt.navigation)
//    implementation(libs.androidx.hilt.lifecycle.viewmodel)
    implementation(libs.androidx.hilt.navigation.compose)

    //Data store
    implementation(libs.androidx.datastore.preferences)

    //SplashScreen
    implementation(libs.androidx.core.splashscreen)

}
