/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.kotlin.examples

import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.FileState
import com.google.genai.kotlin.types.ListFilesConfig
import com.google.genai.kotlin.types.UploadFileConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to manage files.
 * 
 * NOTE: The Files API is only supported on the Gemini Developer API.
 *
 * Usage:
 *
 * 1. Set an API key environment variable. You can find a
 * list of available API keys here: https://aistudio.google.com/app/apikey
 *
 * export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * 2. Run the example:
 * <pre>
 * ./gradlew :example:runExample -PmainClass=com.google.genai.kotlin.examples.FilesOperations
 * </pre>
 */
object FilesOperations {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      // Instantiate the client. The client by default uses the Gemini Developer API.
      Client().use { client ->
        // Read a local file for upload
        val content = java.io.File("src/main/resources/google.png").readBytes()

        println("Uploading file...")
        val file = client.files.upload(
          byteArray = content,
          config = UploadFileConfig(
            mimeType = "image/png",
            displayName = "google.png"
          )
        )
        println("Uploaded file: ${file.name}")

        // Poll until ACTIVE
        var retrievedFile = client.files.get(name = file.name!!)
        var attempts = 0
        while (retrievedFile.state != FileState.ACTIVE && retrievedFile.state != FileState.FAILED && attempts < 10) {
          println("File state is ${retrievedFile.state}, polling...")
          delay(2000)
          retrievedFile = client.files.get(name = file.name!!)
          attempts++
        }
        println("Get file: $retrievedFile")

        val pager = client.files.list(ListFilesConfig(pageSize = 10))
        val filesList = pager.take(10).toList()
        println("Listed files: ${filesList.map { it.name }}")

        client.files.delete(name = file.name!!)
        println("Deleted file: ${file.name}")
      }

      kotlin.system.exitProcess(0)
    }
}
