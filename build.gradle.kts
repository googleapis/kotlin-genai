/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.android.library)
  alias(libs.plugins.dokka)
}

group = "com.google.genai.kotlin"

version = "0.1.0-SNAPSHOT"

repositories { mavenCentral() }

kotlin {
  compilerOptions { freeCompilerArgs.add("-Xexpect-actual-classes") }

  androidTarget {
    publishLibraryVariants("release")
    compilations.all { kotlinOptions { jvmTarget = "17" } }
  }
  jvm { compilations.all { kotlinOptions { jvmTarget = "17" } } }

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(libs.kotlinx.serialization.json)
        api(libs.kotlinx.datetime)
        implementation(libs.ktor.client.core)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.ktor.serialization.kotlinx.json)
        implementation(libs.kotlinx.coroutines.core)
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
        implementation(libs.kotlinx.coroutines.test)
        implementation(libs.mockk)
        implementation(libs.ktor.client.mock)
        implementation("com.google.testserver:test-server-sdk-kotlin") {
          version { branch = "main" }
        }
      }
    }
    val jvmAndroidMain by creating {
      dependsOn(commonMain)
      dependencies {
        api(libs.google.auth.library)
        implementation(libs.ktor.client.okhttp)
      }
    }
    val jvmMain by getting { dependsOn(jvmAndroidMain) }
    val androidMain by getting { dependsOn(jvmAndroidMain) }
  }
}

tasks.withType<Test> {
  testLogging {
    events("failed")
    showExceptions = true
    showCauses = true
    showStackTraces = true
    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
  }
}

android {
  namespace = "com.google.genai.kotlin"
  compileSdk = 34

  defaultConfig { minSdk = 21 }

  compileOptions {
    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_17
    targetCompatibility = org.gradle.api.JavaVersion.VERSION_17
  }
}

// Pass testMode property ONLY to JVM tests: ./gradlew jvmTest -PtestMode=record
tasks.named<Test>("jvmTest") {
  val mode =
    if (project.hasProperty("testMode")) {
      project.property("testMode").toString()
    } else {
      "replay"
    }
  environment("TEST_MODE", mode)
}
