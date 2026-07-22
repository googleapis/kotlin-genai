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
  kotlin("jvm")
  application
}

repositories { mavenCentral() }

dependencies {
  implementation(rootProject)

  implementation(kotlin("stdlib"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
  implementation(libs.ktor.client.core)
}

application {
  // Define the main class for the default 'run' task.
  // You can change this to run different examples.
  mainClass.set("com.google.genai.kotlin.examples.GenerateContent")
}

// Optional: Task to run a specific example easily from command line
// Usage: ./gradlew :examples:runExample -PmainClass=com.google.genai.kotlin.examples.ChatKt
tasks.register<JavaExec>("runExample") {
  classpath = sourceSets["main"].runtimeClasspath
  if (project.hasProperty("mainClass")) {
    mainClass.set(project.property("mainClass") as String)
  } else {
    mainClass.set("com.google.genai.kotlin.examples.GenerateContent")
  }
}
