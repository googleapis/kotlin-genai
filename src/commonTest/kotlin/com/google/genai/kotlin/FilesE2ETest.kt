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

import com.google.genai.kotlin.types.ListFilesConfig
import com.google.genai.kotlin.types.UploadFileConfig
import com.google.genai.kotlin.types.FileState
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.delay
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 60.seconds, testBody = testBody)

class FilesE2ETest : BaseTestServer() {

  @Test
  fun testFilesLifecycle() = runTest {
    // Files are only supported in MLDev mode (enterprise = false)
    val testName = "FilesE2ETest.testFilesLifecycle.mldev"
    val client = createClient(enterprise = false, testName = testName)

    // Using a manually uploaded file ID for recording
    val fileName = "files/x6epcgntsxvu"

    // 1. Get
    val retrievedFile = client.files.get(fileName)
    assertNotNull(retrievedFile)
    assertEquals(fileName, retrievedFile.name)
    assertEquals("text/plain", retrievedFile.mimeType)

    // 2. List
    val pager = client.files.list(ListFilesConfig(pageSize = 10))
    assertEquals("files", pager.name)
    val filesList = pager.take(10).toList()
    assertTrue(filesList.isNotEmpty())
    val foundUploaded = filesList.any { it.name == fileName }
    assertTrue(foundUploaded, "Uploaded file not found in list")

    // 4. Delete
    val deleteResponse = client.files.delete(fileName)
    assertNotNull(deleteResponse)
  }

  @Test
  fun testUploadLifecycle() = runTest {
    val testName = "FilesE2ETest.testUploadLifecycle.mldev"
    val client = createClient(enterprise = false, testName = testName)

    val content = """{"text": "hello world from e2e test"}""".encodeToByteArray()
    val file = client.files.upload(
      byteArray = content,
      config = UploadFileConfig(
        mimeType = "application/json",
        displayName = "e2e-test-file.json"
      )
    )
    assertNotNull(file)
    assertNotNull(file.name)
    assertEquals("application/json", file.mimeType)

    // Poll until ACTIVE
    var retrievedFile = client.files.get(file.name!!)
    var attempts = 0
    while (retrievedFile.state != FileState.ACTIVE && retrievedFile.state != FileState.FAILED && attempts < 10) {
      delay(2000)
      retrievedFile = client.files.get(file.name!!)
      attempts++
    }
    assertEquals(FileState.ACTIVE, retrievedFile.state)

    // Clean up
    client.files.delete(file.name!!)
  }
}
