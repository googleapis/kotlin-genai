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
import com.google.genai.kotlin.downloadToFile
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.readRemaining
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import java.nio.file.Files as NioFiles
import java.nio.file.Paths

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
 * ./gradlew :example:runExample -PmainClass=com.google.genai.kotlin.examples.DownloadFileOperations
 * </pre>
 */
object DownloadFileOperations {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      // Instantiate the client. The client by default uses the Gemini Developer API.
      Client().use { client ->
        val pager = client.files.list(ListFilesConfig(pageSize = 10))
        var fileToDownload: com.google.genai.kotlin.types.File? = null

        // Find a file with a downloadUri (which means it's a GENERATED file)
        for (file in pager.take(50).toList()) {
          if (file.downloadUri != null) {
            fileToDownload = file
            break
          }
        }

        if (fileToDownload != null) {
          println("Downloading generated file: ${fileToDownload.name} (${fileToDownload.downloadUri})")

          // Use the JVM/Android specific extension function to stream directly to disk
          val outputPath = Paths.get("downloaded_generated_file")
          client.files.downloadToFile(fileToDownload, outputPath)

          println(
            "Downloaded file to downloaded_generated_file with size: ${NioFiles.size(outputPath)} bytes"
          )
        } else {
          println("No generated files found with a downloadUri. Skipping download demonstration.")
        }
      }

      kotlin.system.exitProcess(0)
    }
}
