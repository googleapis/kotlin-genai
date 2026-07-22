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
import com.google.genai.kotlin.types.ListFilesConfig
import com.google.genai.kotlin.types.DeleteFileConfig
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.http.content.OutgoingContent
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readUTF8Line
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json

class FilesTest {

  @Test
  fun testUploadBytesResumableProtocol() = runTest {
    val requests = mutableListOf<HttpRequestData>()
    val engine = MockEngine { request ->
      requests.add(request)
      when {
        // Step 1: Create File API call (Resumable upload initiation)
        request.url.encodedPath.endsWith("/upload/v1beta/files") -> {
          assertEquals("POST", request.method.value)
          assertEquals("resumable", request.headers["X-Goog-Upload-Protocol"])
          assertEquals("start", request.headers["X-Goog-Upload-Command"])
          assertEquals("11", request.headers["X-Goog-Upload-Header-Content-Length"])
          assertEquals("text/plain", request.headers["X-Goog-Upload-Header-Content-Type"])
          assertEquals("test-file", request.headers["X-Goog-Upload-File-Name"])

          respond(
            content = "{}",
            status = HttpStatusCode.OK,
            headers = headersOf(
              "X-Goog-Upload-URL" to listOf("https://mock-upload.googleapis.com/upload/session-123"),
              HttpHeaders.ContentType to listOf("application/json")
            )
          )
        }
        // Step 2: Upload chunk data API call
        request.url.toString() == "https://mock-upload.googleapis.com/upload/session-123" -> {
          assertEquals("POST", request.method.value)
          assertEquals("upload, finalize", request.headers["X-Goog-Upload-Command"])
          assertEquals("0", request.headers["X-Goog-Upload-Offset"])
          val contentType = request.headers[HttpHeaders.ContentType] ?: request.body.contentType?.toString()
          assertEquals("application/octet-stream", contentType)

          val requestBody = (request.body as OutgoingContent.ByteArrayContent).bytes()
          assertEquals("hello world", requestBody.decodeToString())

          val mockResponseBody = """{
            "file": {
              "name": "files/file-123",
              "mimeType": "text/plain",
              "displayName": "test-file",
              "sizeBytes": "11"
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

    ApiClient(apiKey = "test-key", engine = engine).use { apiClient ->
      val files = Files(apiClient)
      val bytes = "hello world".encodeToByteArray()
      val config = UploadFileConfig(
        mimeType = "text/plain",
        displayName = "test-file"
      )
      val file = files.upload(bytes, config)

      assertNotNull(file)
      assertEquals("files/file-123", file.name)
      assertEquals("text/plain", file.mimeType)
      assertEquals("test-file", file.displayName)
      assertEquals(2, requests.size)
    }
  }

  @Test
  fun testDownloadFile() = runTest {
    val requests = mutableListOf<HttpRequestData>()
    val engine = MockEngine { request ->
      requests.add(request)
      when {
        request.url.encodedPath.endsWith("/files/file-123:download") && request.url.encodedQuery.contains("alt=media") -> {
          assertEquals("GET", request.method.value)
          respond(
            content = ByteReadChannel("file content here".encodeToByteArray()),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType to listOf("application/octet-stream"))
          )
        }
        else -> respond("Not Found", status = HttpStatusCode.NotFound)
      }
    }

    ApiClient(apiKey = "test-key", engine = engine).use { apiClient ->
      val files = Files(apiClient)
      val channel = files.download("files/file-123")
      assertNotNull(channel)
      val content = channel.readUTF8Line()
      assertEquals("file content here", content)
      assertEquals(1, requests.size)
    }
  }

  @Test
  fun testGetFile() = runTest {
    val requests = mutableListOf<HttpRequestData>()
    val engine = MockEngine { request ->
      requests.add(request)
      when {
        request.url.encodedPath.endsWith("/files/file-123") -> {
          assertEquals("GET", request.method.value)
          val mockResponseBody = """{
            "name": "files/file-123",
            "mimeType": "text/plain",
            "displayName": "test-file",
            "sizeBytes": "11"
          }"""
          respond(
            content = mockResponseBody,
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType to listOf("application/json"))
          )
        }
        else -> respond("Not Found", status = HttpStatusCode.NotFound)
      }
    }

    ApiClient(apiKey = "test-key", engine = engine).use { apiClient ->
      val files = Files(apiClient)
      val file = files.get("files/file-123")
      assertNotNull(file)
      assertEquals("files/file-123", file.name)
      assertEquals("test-file", file.displayName)
      assertEquals(1, requests.size)
    }
  }

  @Test
  fun testDeleteFile() = runTest {
    val requests = mutableListOf<HttpRequestData>()
    val engine = MockEngine { request ->
      requests.add(request)
      when {
        request.url.encodedPath.endsWith("/files/file-123") -> {
          assertEquals("DELETE", request.method.value)
          respond(
            content = "{}",
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType to listOf("application/json"))
          )
        }
        else -> respond("Not Found", status = HttpStatusCode.NotFound)
      }
    }

    ApiClient(apiKey = "test-key", engine = engine).use { apiClient ->
      val files = Files(apiClient)
      val response = files.delete("files/file-123")
      assertNotNull(response)
      assertEquals(1, requests.size)
    }
  }

  @Test
  fun testUploadBytesResumableProtocolMultiChunk() = runTest {
    val requests = mutableListOf<HttpRequestData>()
    val engine = MockEngine { request ->
      requests.add(request)
      when {
        // Step 1: Initiation
        request.url.encodedPath.endsWith("/upload/v1beta/files") -> {
          assertEquals("POST", request.method.value)
          assertEquals("resumable", request.headers["X-Goog-Upload-Protocol"])
          assertEquals("start", request.headers["X-Goog-Upload-Command"])
          assertEquals("18874368", request.headers["X-Goog-Upload-Header-Content-Length"])
          assertEquals("application/octet-stream", request.headers["X-Goog-Upload-Header-Content-Type"])

          respond(
            content = "{}",
            status = HttpStatusCode.OK,
            headers = headersOf(
              "X-Goog-Upload-URL" to listOf("https://mock-upload.googleapis.com/upload/session-456"),
              HttpHeaders.ContentType to listOf("application/json")
            )
          )
        }
        // Step 2: Upload chunk 1 (0 to 8MB)
        request.url.toString() == "https://mock-upload.googleapis.com/upload/session-456" &&
            request.headers["X-Goog-Upload-Offset"] == "0" -> {
          assertEquals("POST", request.method.value)
          assertEquals("upload", request.headers["X-Goog-Upload-Command"])
          val requestBody = (request.body as OutgoingContent.ByteArrayContent).bytes()
          assertEquals(8388608, requestBody.size)

          respond(
            content = "{}",
            status = HttpStatusCode.OK,
            headers = headersOf(
              "X-Goog-Upload-Status" to listOf("active"),
              HttpHeaders.ContentType to listOf("application/json")
            )
          )
        }
        // Step 3: Upload chunk 2 (8MB to 16MB)
        request.url.toString() == "https://mock-upload.googleapis.com/upload/session-456" &&
            request.headers["X-Goog-Upload-Offset"] == "8388608" -> {
          assertEquals("POST", request.method.value)
          assertEquals("upload", request.headers["X-Goog-Upload-Command"])
          val requestBody = (request.body as OutgoingContent.ByteArrayContent).bytes()
          assertEquals(8388608, requestBody.size)

          respond(
            content = "{}",
            status = HttpStatusCode.OK,
            headers = headersOf(
              "X-Goog-Upload-Status" to listOf("active"),
              HttpHeaders.ContentType to listOf("application/json")
            )
          )
        }
        // Step 4: Upload chunk 3 (16MB to 18MB, finalize)
        request.url.toString() == "https://mock-upload.googleapis.com/upload/session-456" &&
            request.headers["X-Goog-Upload-Offset"] == "16777216" -> {
          assertEquals("POST", request.method.value)
          assertEquals("upload, finalize", request.headers["X-Goog-Upload-Command"])
          val requestBody = (request.body as OutgoingContent.ByteArrayContent).bytes()
          assertEquals(2097152, requestBody.size)

          val mockResponseBody = """{
            "file": {
              "name": "files/file-456",
              "mimeType": "application/octet-stream",
              "displayName": "large-file",
              "sizeBytes": "18874368"
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

    ApiClient(apiKey = "test-key", engine = engine).use { apiClient ->
      val files = Files(apiClient)
      val bytes = ByteArray(18 * 1024 * 1024) { 2 }
      val config = UploadFileConfig(
        mimeType = "application/octet-stream",
        displayName = "large-file"
      )
      val file = files.upload(bytes, config)

      assertNotNull(file)
      assertEquals("files/file-456", file.name)
      assertEquals("application/octet-stream", file.mimeType)
      assertEquals("large-file", file.displayName)
      assertEquals(18874368L, file.sizeBytes)
      assertEquals(4, requests.size)
    }
  }

  @Test
  fun testUploadEmptyFile() = runTest {
    val requests = mutableListOf<HttpRequestData>()
    val engine = MockEngine { request ->
      requests.add(request)
      when {
        // Step 1: Initiation
        request.url.encodedPath.endsWith("/upload/v1beta/files") -> {
          assertEquals("POST", request.method.value)
          assertEquals("resumable", request.headers["X-Goog-Upload-Protocol"])
          assertEquals("start", request.headers["X-Goog-Upload-Command"])
          assertEquals("0", request.headers["X-Goog-Upload-Header-Content-Length"])
          assertEquals("text/plain", request.headers["X-Goog-Upload-Header-Content-Type"])

          respond(
            content = "{}",
            status = HttpStatusCode.OK,
            headers = headersOf(
              "X-Goog-Upload-URL" to listOf("https://mock-upload.googleapis.com/upload/session-empty"),
              HttpHeaders.ContentType to listOf("application/json")
            )
          )
        }
        // Step 2: Upload chunk (offset 0, command upload, finalize)
        request.url.toString() == "https://mock-upload.googleapis.com/upload/session-empty" -> {
          assertEquals("POST", request.method.value)
          assertEquals("upload, finalize", request.headers["X-Goog-Upload-Command"])
          assertEquals("0", request.headers["X-Goog-Upload-Offset"])
          val requestBody = (request.body as OutgoingContent.ByteArrayContent).bytes()
          assertEquals(0, requestBody.size)

          val mockResponseBody = """{
            "file": {
              "name": "files/file-empty",
              "mimeType": "text/plain",
              "displayName": "empty-file",
              "sizeBytes": "0"
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

    ApiClient(apiKey = "test-key", engine = engine).use { apiClient ->
      val files = Files(apiClient)
      val bytes = ByteArray(0)
      val config = UploadFileConfig(
        mimeType = "text/plain",
        displayName = "empty-file"
      )
      val file = files.upload(bytes, config)

      assertNotNull(file)
      assertEquals("files/file-empty", file.name)
      assertEquals(0L, file.sizeBytes)
      assertEquals(2, requests.size)
    }
  }
}
