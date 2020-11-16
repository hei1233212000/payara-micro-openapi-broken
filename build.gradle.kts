import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.noarg.gradle.NoArgExtension

plugins {
    val kotlinVersion = "1.4.10"
    idea
    kotlin("jvm") version kotlinVersion
    war
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.noarg") version kotlinVersion
    id("fish.payara.micro-gradle-plugin") version "1.0.3"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
}

group = "bug"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    maven {
        url = uri("https://raw.github.com/payara/Payara_PatchedProjects/master")
    }
}

val payaraMicroVersion = "5.2020.6"
dependencyManagement {
    imports {
        mavenBom("fish.payara.api:payara-bom:$payaraMicroVersion")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    providedCompile("org.eclipse.microprofile:microprofile")
}

/**
 * Check the guide in https://docs.payara.fish/documentation/ecosystem/gradle-plugin.html
 */
payaraMicro {
    payaraVersion = payaraMicroVersion
    deployWar = true
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

configure<AllOpenExtension> {
    annotation("javax.enterprise.context.RequestScoped")
    annotation("javax.enterprise.context.ApplicationScoped")
}

configure<NoArgExtension> {
    annotation("javax.enterprise.context.RequestScoped")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("poc.microprofile.annotation.DeserializableModel")
}
