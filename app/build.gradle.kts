plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:31.0.1-jre")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.platform:junit-platform-suite:1.9.2")
    testImplementation("io.cucumber:cucumber-java8:7.11.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.11.0")
    testImplementation("io.cucumber:cucumber-picocontainer:7.11.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
    testImplementation("io.mockk:mockk:1.13.3")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

application {
    mainClass.set("hotel.AppKt")
}
