plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.example.testapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.testapp"
        minSdk = 23
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.kotlin.stdlib.jdk)
    // Вспомогательные библиотеки
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.pinview)
    // Android KTX
    implementation(libs.androidx.core.ktx)
    // Навигация
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    // Корутины для работы вне основного потока
    implementation(libs.kotlinx.kotlin.coroutines.core)
    implementation(libs.kotlinx.kotlin.coroutines.android)
    // Retrofit для сетевого взаимодействия
    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.moshi)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
    // Moshi для парсинга формата JSON
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    // Joda Time для работы со временем
    implementation(libs.joda.time)
    // ViewModel и LiveData (архитектурные компоненты)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    // Dagger
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    // Логирование
    implementation(libs.timber)
    // Glide для работы с изображениями
    implementation(libs.glide)
    // База данных Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    ksp(libs.room.compiler)
    // Kotlin расширения и поддержка корутин для Room
    implementation(libs.room.ktx)
    // WorkManager для фоновых задач
    implementation(libs.work.runtime.ktx)
    //Библиотеки  для тестирования
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}