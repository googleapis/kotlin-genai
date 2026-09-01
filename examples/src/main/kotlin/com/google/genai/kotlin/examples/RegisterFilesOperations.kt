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

import com.google.auth.oauth2.GoogleCredentials
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.FileData
import com.google.genai.kotlin.types.Part
import kotlinx.coroutines.runBlocking

/**
 * Example demonstrating how to register a file from a Google Cloud Storage URI.
 *
 * Note: This operation is only supported by the Gemini Developer API (not Gemini Enterprise Agent
 * Platform).
 *
 * Prerequisites:
 * 1. Set `GEMINI_API_KEY` (or `GOOGLE_API_KEY`) -- this is what the client authenticates with.
 * 2. Set up Application Default Credentials (e.g. `gcloud auth application-default login
 *    --no-launch-browser
 *    --scopes="https://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/devstorage.read_only"`).
 * 3. Ensure you have a valid GCS URI that you want to register.
 *
 * Registering is the one Files operation that needs more than an API key: the service reads the
 * object out of Google Cloud Storage on your behalf, which requires an OAuth token. Those
 * credentials are passed to [Files.registerFiles] alone. The client stays an ordinary Gemini
 * Developer API key client -- the [Client] constructor rejects `credentials` without a project,
 * because credentials and API keys are mutually exclusive there.
 *
 * The registered file is scoped to the project of those credentials, so the API key must belong to
 * the same project. If it does not, every later call returns 403 with a message about not having
 * permission to access the file, which does not mention the project mismatch.
 *
 * The bucket also needs the Gemini API service agent granted Storage Object Viewer. See
 * https://ai.google.dev/gemini-api/docs/file-input-methods#registration for the full setup.
 *
 * To run the example:
 * <pre>
 * ./gradlew :examples:runExample -PmainClass=com.google.genai.kotlin.examples.RegisterFilesOperations
 * </pre>
 */
object RegisterFilesOperations {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      // Application Default Credentials supply the OAuth token the service needs to read the
      // object from GCS, and are used only by registerFiles below.
      //
      // createScoped applies to service account credentials. End-user credentials from
      // `gcloud auth application-default login` cannot be re-scoped here -- their scopes are fixed
      // when you consent, so the scopes must be given at login time as shown in prerequisite 2.
      //
      // devstorage.read_only is required, not belt-and-braces. files:register accepts only
      // generative-language, generative-language.retriever.readonly or devstorage.read_only.
      // cloud-platform is not on that list, so a cloud-platform-only token fails with
      // 403 ACCESS_TOKEN_SCOPE_INSUFFICIENT -- even though for Cloud Storage itself
      // cloud-platform is equivalent to devstorage.full_control.
      val credentials =
        GoogleCredentials.getApplicationDefault()
          .createScoped(
            listOf(
              "https://www.googleapis.com/auth/cloud-platform",
              "https://www.googleapis.com/auth/devstorage.read_only",
            )
          )

      // The client itself authenticates with GEMINI_API_KEY / GOOGLE_API_KEY from the environment.
      Client().use { client ->
        // Note: You will need to replace this URI with a valid GCS URI from your project.
        val gcsUri = "gs://cloud-samples-data/generative-ai/image/a-man-and-a-dog.png"

        println("Registering file from URI: $gcsUri")
        val response = client.files.registerFiles(credentials = credentials, uris = listOf(gcsUri))

        val registeredFile = response.files?.firstOrNull()
        if (registeredFile == null) {
          println("No files were registered.")
        } else {
          println("Successfully registered file: ${registeredFile.name}")

          // The registered file is usable from the ordinary API key client.
          val retrieved = client.files.get(name = registeredFile.name!!)
          println("File state: ${retrieved.state}")

          val genResponse =
            client.models.generateContent(
              model = "gemini-flash-latest",
              contents =
                listOf(
                  Content(
                    parts =
                      listOf(
                        Part(text = "What is in this image? One sentence."),
                        Part(
                          fileData =
                            FileData(fileUri = retrieved.uri, mimeType = retrieved.mimeType)
                        ),
                      )
                  )
                ),
            )
          println("Response: ${genResponse.text}")

          client.files.delete(name = registeredFile.name!!)
          println("Deleted file successfully.")
        }
      }

      kotlin.system.exitProcess(0)
    }
}
