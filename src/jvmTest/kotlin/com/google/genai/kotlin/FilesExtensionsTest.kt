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

package com.google.genai.kotlin

import com.google.genai.kotlin.types.File
import com.google.genai.kotlin.types.UploadFileConfig
import com.google.genai.kotlin.types.DownloadFileConfig
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.http.content.OutgoingContent
import io.ktor.utils.io.ByteReadChannel
import java.nio.file.Files as NioFiles
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.coroutines.test.runTest

class FilesExtensionsTest {

  @Test
  fun testUploadJavaFile() = runTest {
    val requests = mutableListOf<HttpRequestData>()
    val engine = MockEngine { request ->
      requests.add(request)
      when {
        request.url.encodedPath.endsWith("/upload/v1beta/files") -> {
          respond(
            content = "{}",
            status = HttpStatusCode.OK,
            headers = headersOf(
              "X-Goog-Upload-URL" to listOf("https://mock-upload.googleapis.com/upload/session-123"),
              HttpHeaders.ContentType to listOf("application/json")
            )
          )
        }
        request.url.toString() == "https://mock-upload.googleapis.com/upload/session-123" -> {
          val requestBody = (request.body as OutgoingContent.ByteArrayContent).bytes()
          assertEquals("hello from jvm file", requestBody.decodeToString())

          val mockResponseBody = """{
            "file": {
              "name": "files/file-jvm-123",
              "mimeType": "text/plain",
              "displayName": "test-jvm-file.txt",
              "sizeBytes": "19"
            }
          }"""
          respond(
            content = mockResponseBody,
            status = HttpStatusCode.OK,
            headers = headersOf(
              "X-Goog-Upload-Status" to listOf("final"),
              HttpHeaders.ContentType to listOf("application/json")
            )
          )
        }
        else -> respond("Not Found", status = HttpStatusCode.NotFound)
      }
    }

    // Create a temporary file
    val tempFile = NioFiles.createTempFile("test-jvm-file", ".txt").toFile()
    tempFile.writeText("hello from jvm file")
    tempFile.deleteOnExit()

    try {
      ApiClient(apiKey = "test-key", engine = engine).use { apiClient ->
        val files = Files(apiClient)
        val file = files.upload(tempFile)

        assertNotNull(file)
        assertEquals("files/file-jvm-123", file.name)
        assertEquals("text/plain", file.mimeType)
        assertEquals("test-jvm-file.txt", file.displayName)
      }
    } finally {
      tempFile.delete()
    }
  }

  @Test
  fun testDownloadToFile() = runTest {
    val requests = mutableListOf<HttpRequestData>()
    val engine = MockEngine { request ->
      requests.add(request)
      when {
        request.url.encodedPath.endsWith("/files/file-jvm-123:download") && request.url.encodedQuery.contains("alt=media") -> {
          respond(
            content = ByteReadChannel("hello jvm download".encodeToByteArray()),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType to listOf("application/octet-stream"))
          )
        }
        else -> respond("Not Found", status = HttpStatusCode.NotFound)
      }
    }

    val tempOutputFile = NioFiles.createTempFile("downloaded-file", ".txt")
    try {
      ApiClient(apiKey = "test-key", engine = engine).use { apiClient ->
        val files = Files(apiClient)
        files.downloadToFile("files/file-jvm-123", tempOutputFile)

        val content = NioFiles.readString(tempOutputFile)
        assertEquals("hello jvm download", content)
      }
    } finally {
      NioFiles.deleteIfExists(tempOutputFile)
    }
  }
}
