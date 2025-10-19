plugins {
    java
    application
}

group = "com.github.sujankumarmitra"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

// Dependency versions
val lombokVersion = "1.18.42"
val junitVersion = "5.13.4"
val pdfboxVersion = "3.0.6"
val commonsCliVersion = "1.10.0"
val mainClassName = "com.github.sujankumarmitra.pdf4j.launcher.CommandLineLauncher"

dependencies {
    implementation("org.apache.pdfbox:pdfbox:$pdfboxVersion")
    implementation("commons-cli:commons-cli:$commonsCliVersion")
    
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Jar>("standaloneJar") {
    archiveBaseName.set("pdf4j")
    archiveClassifier.set("standalone")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    
    manifest {
        attributes["Main-Class"] = mainClassName
    }
    
    from(sourceSets.main.get().output)
    
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().map { zipTree(it) }
    })
}

application {
    mainClass.set(mainClassName)
}
