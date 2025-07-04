plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.akari"
    compileSdk = 34


        lintOptions {
            disable("Instantiatable")
        }


    defaultConfig {
        applicationId = "com.example.akari"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {

    implementation ("androidx.activity:activity:1.8.0")
    implementation ("androidx.fragment:fragment:1.6.2")
    implementation ("androidx.appcompat:appcompat:1.6.1")

    implementation ("androidx.camera:camera-core:1.3.0")
    implementation ("androidx.camera:camera-camera2:1.3.0")
    implementation ("com.google.zxing:core:3.5.2") // Use the latest version available
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation ("com.opencsv:opencsv:5.8")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}