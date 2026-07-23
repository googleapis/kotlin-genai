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

package com.google.genai.kotlin.examples

import com.google.genai.kotlin.Client

import com.google.auth.oauth2.GoogleCredentials
import kotlinx.coroutines.runBlocking

/**
 * Example demonstrating how to register a file from a Google Cloud Storage URI.
 *
 * Note: This operation is only supported by the Gemini Developer API (not Vertex AI).
 *
 * Prerequisites:
 * 1. Set up Application Default Credentials (e.g. `gcloud auth application-default login --no-launch-browser --scopes="https://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/devstorage.read_only"`).
 * 2. Ensure you have a valid GCS URI that you want to register.
 * 3. The API requires OAuth credentials to fetch from GCS, an API key is insufficient.
 *
 * To run the example:
 * <pre>
 * ./gradlew :example:runExample -PmainClass=com.google.genai.kotlin.examples.RegisterFilesOperations
 * </pre>
 */
object RegisterFilesOperations {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      // Use Application Default Credentials which provide the necessary OAuth tokens
      val credentials = GoogleCredentials.getApplicationDefault()
        .createScoped(
          listOf(
            "https://www.googleapis.com/auth/cloud-platform",
            "https://www.googleapis.com/auth/devstorage.read_only"
          )
        )

      Client(credentials = credentials).use { client ->
        // Note: You will need to replace this URI with a valid GCS URI from your project.
        val gcsUri = "gs://cloud-samples-data/generative-ai/image/a-man-and-a-dog.png"

        println("Registering file from URI: $gcsUri")
        val response = client.files.registerFiles(
          credentials = credentials,
          uris = listOf(gcsUri)
        )

        val registeredFile = response.files?.firstOrNull()
        if (registeredFile != null) {
          println("Successfully registered file: ${registeredFile.name}")

          println("Getting file details...")
          val retrievedFile = client.files.get(name = registeredFile.name!!)
          println("File retrieved: $retrievedFile")

          // Clean up the registered file
          println("Deleting file: ${retrievedFile.name}")
          client.files.delete(name = retrievedFile.name!!)
          println("Deleted file successfully.")
        } else {
          println("No files were registered.")
        }
      }

      kotlin.system.exitProcess(0)
    }
}
