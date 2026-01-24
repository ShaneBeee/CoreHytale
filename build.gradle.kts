plugins {
    id("java")
}

group = "com.shanebeestudios"
version = "1.0.0"
val hytaleVersion = "2026.01.23-6e2d4fc36"

repositories {
    mavenCentral()
    maven("https://maven.hytale.com/release")
    maven("https://maven.hytale.com/pre-release")
}

dependencies {
    implementation("com.hypixel.hytale:Server:$hytaleVersion")
    implementation("org.jetbrains:annotations:26.0.2")
}

tasks {
    register("survival-server", Copy::class) {
        dependsOn("jar")
        from("build/libs") {
            include("CoreHytale-*.jar")
            destinationDir = file("/Users/ShaneBee/Desktop/Server/Hytale/Survival/mods/")
        }
    }
    register("creative-server", Copy::class) {
        dependsOn("jar")
        from("build/libs") {
            include("CoreHytale-*.jar")
            destinationDir = file("/Users/ShaneBee/Desktop/Server/Hytale/Creative/mods/")
        }
    }
    processResources {
        filesNotMatching("assets/**") {
            expand("pluginVersion" to version)
        }

    }
}
