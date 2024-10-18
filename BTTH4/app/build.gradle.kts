//
//plugins {
//    id("com.android.application") // Plugin Android Application
//    id("com.google.gms.google-services") // Plugin Google Services
//}
//
//android {
//    namespace = "com.example.btth4" // Namespace của ứng dụng
//    compileSdk = 34 // Phiên bản SDK biên dịch
//
//    defaultConfig {
//        applicationId = "com.example.btth4" // ID ứng dụng
//        minSdk = 24 // Phiên bản tối thiểu của SDK
//        targetSdk = 34 // Phiên bản SDK mục tiêu
//        versionCode = 1 // Mã phiên bản
//        versionName = "1.0" // Tên phiên bản
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Trình chạy kiểm tra
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false // Tắt tối ưu hóa mã cho phiên bản phát hành
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro" // File quy tắc ProGuard
//            )
//        }
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8 // Đặt tương thích mã nguồn
//        targetCompatibility = JavaVersion.VERSION_1_8 // Đặt tương thích mã đích
//    }
//}
//
//dependencies {
//    // Các thư viện cần thiết cho ứng dụng
//    implementation(libs.appcompat) // Thư viện AppCompat
//    implementation(libs.material) // Thư viện Material Design
//    implementation(libs.activity) // Thư viện Activity
//    implementation(libs.constraintlayout) // Thư viện ConstraintLayout
//    testImplementation(libs.junit) // Thư viện kiểm tra JUnit
//    androidTestImplementation(libs.ext.junit) // Thư viện kiểm tra JUnit mở rộng
//    androidTestImplementation(libs.espresso.core) // Thư viện Espresso cho kiểm tra giao diện
//
//    // Import Firebase BoM
//    implementation(platform("com.google.firebase:firebase-bom:33.4.0")) // BoM giúp quản lý phiên bản Firebase
//
//    // Thêm Firebase Analytics
//    implementation("com.google.firebase:firebase-analytics") // Thư viện Firebase Analytics
//
//    // Thêm Firebase Realtime Database nếu cần
//    implementation("com.google.firebase:firebase-database") // Thư viện Firebase Realtime Database
//}


plugins {
    id("com.android.application")
    id("com.google.gms.google-services") // Plugin Google Services
}

android {
    namespace = "com.example.btth4"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.btth4"
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
    // Các thư viện cần thiết
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Import Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.4.0"))

    // Thêm Firebase Realtime Database
    implementation("com.google.firebase:firebase-database")

    // Thêm Firebase Analytics
    implementation("com.google.firebase:firebase-analytics")
}
