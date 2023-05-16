plugins {
  kotlin("jvm") version "1.8.20"
  `maven-publish`
}

group = "com.lucasalfare.flarqueology"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("com.google.code.gson:gson:2.8.9")
}

publishing {
  publications {
    create<MavenPublication>("Maven") {
      from(components["kotlin"])
    }
  }
}