plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.6.10"
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "net.numa08.rdblogger"
        minSdk = 28
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = Dependencies.composeVersion
    }
    packagingOptions {
        resources {
            excludes += listOf("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.compose.ui:ui:${Dependencies.composeVersion}")
    implementation("androidx.compose.material:material:${Dependencies.composeVersion}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Dependencies.composeVersion}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Dependencies.lifecycleRuntime}")
    implementation("androidx.navigation:navigation-compose:${Dependencies.navigation}")
    implementation("androidx.hilt:hilt-navigation-compose:${Dependencies.hiltNavigation}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Dependencies.lifecycleRuntime}")
    implementation("androidx.activity:activity-compose:${Dependencies.activityCompose}")
    implementation("androidx.room:room-runtime:${Dependencies.roomVersion}")
    annotationProcessor("androidx.room:room-compiler:${Dependencies.roomVersion}")
    kapt("androidx.room:room-compiler:${Dependencies.roomVersion}")
    implementation("androidx.room:room-ktx:${Dependencies.roomVersion}")
    implementation("com.jakewharton.timber:timber:${Dependencies.timber}")
    implementation("com.google.dagger:hilt-android:${Dependencies.hiltPlugin}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    kapt("com.google.dagger:hilt-android-compiler:${Dependencies.hiltPlugin}")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Dependencies.composeVersion}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Dependencies.composeVersion}")
}