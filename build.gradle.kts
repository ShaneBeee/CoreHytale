plugins {
    id("java")
}

group = "com.shanebeestudios"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("/Users/ShaneBee/Desktop/Server/Hytale/HytaleServer.jar"))
    implementation("org.jetbrains:annotations:26.0.2")
}

tasks {
    register("server", Copy::class) {
        dependsOn("jar")
        from("build/libs") {
            include("CoreHytale-*.jar")
            destinationDir = file("/Users/ShaneBee/Desktop/Server/Hytale/mods/")
        }
    }
    processResources {
        filesNotMatching("assets/**") {
            expand("pluginVersion" to version)
        }

    }
}
