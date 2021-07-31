buildscript {

    val kotlinVersion = "1.5.10"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.0")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.5.10")
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}