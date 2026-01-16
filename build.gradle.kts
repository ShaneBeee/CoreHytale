plugins {
    id("java")
}

group = "com.shanebeestudios"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("/Users/ShaneBee/Desktop/Server/Hytale/Assets/HytaleServer.jar"))
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
