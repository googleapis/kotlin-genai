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

package com.google.genai.kotlin.shared.files

import com.google.genai.kotlin.shared.SharedTestBase
import com.google.genai.kotlin.types.FileState
import com.google.genai.kotlin.types.UploadFileConfig
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 120.seconds, testBody = testBody)

class UploadGetDeleteTest : SharedTestBase() {
  /**
   * Canonical files/upload_get_delete: test_upload_get_delete_image, Gemini API only. commonTest
   * has no test-asset directory, so this uploads inline bytes instead of the canonical PNG.
   */
  @Test
  fun testUploadGetDelete() = runTest {
    listOf(false).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.files.UploadGetDeleteTest.testUploadGetDelete.$suffix"
      val client = createClient(enterprise, testName)

      runLive {
        val file =
          client.files.upload(
            byteArray = """{"text": "shared integration test"}""".encodeToByteArray(),
            config =
              UploadFileConfig(mimeType = "application/json", displayName = "shared-upload.json"),
          )
        assertNotNull(file.name)

        // Delete in a finally so a failed assertion cannot leak the uploaded file.
        try {
          var got = client.files.get(file.name!!)
          var attempts = 0
          while (got.state != FileState.ACTIVE && got.state != FileState.FAILED && attempts < 10) {
            delay(2000)
            got = client.files.get(file.name!!)
            attempts++
          }
          assertEquals(file.name, got.name)
          assertEquals(FileState.ACTIVE, got.state)
        } finally {
          tryCleanup { client.files.delete(file.name!!) }
        }
      }
    }
  }
}
