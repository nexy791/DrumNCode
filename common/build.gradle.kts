plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.test.common"
    compileSdk = 33

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        viewBinding = true
    }
}

dependencies {

    api("androidx.core:core-ktx:1.9.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.9.0")

    // ext
    api("androidx.activity:activity-ktx:1.7.0")
    api("androidx.fragment:fragment-ktx:1.3.6")

    // retrofit
    val retrofitVersion = "2.9.0"
    api("com.squareup.retrofit2:retrofit:$retrofitVersion")
    api("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    api("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // moshi
    api("com.squareup.moshi:moshi-kotlin:1.14.0")

    // state
    api("com.redmadrobot:state-delegator:1.7")

    // insetter
    api("dev.chrisbanes.insetter:insetter:0.6.1")

    // koin
    api("io.insert-koin:koin-android:3.4.0")

    // db
    val roomVersion = "2.5.2"
    api("androidx.room:room-runtime:$roomVersion")
    api("androidx.room:room-ktx:$roomVersion")

    // flows & coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
}