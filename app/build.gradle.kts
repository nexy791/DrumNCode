plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.test.testfordrumncode"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.test.testfordrumncode"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://www.flickr.com/services/\"")
        buildConfigField("String", "API_KEY", "\"531d11b4153d70488de3e69321772c9d\"")
        buildConfigField("String", "IMAGE_BASE_URL", "\"https://live.staticflickr.com/\"")
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
        buildConfig = true
    }
}

dependencies {

    // moshi
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")

    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    // coil
    implementation("io.coil-kt:coil:2.4.0")

    // room
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")


    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":data"))


}