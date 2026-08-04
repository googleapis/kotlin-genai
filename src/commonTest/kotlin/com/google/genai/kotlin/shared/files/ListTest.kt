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

import com.google.genai.kotlin.shared.SHARED_MODEL
import com.google.genai.kotlin.shared.SharedTestBase
import com.google.genai.kotlin.types.ListFilesConfig
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 120.seconds, testBody = testBody)

class ListTest : SharedTestBase() {
  /** Canonical files/list: test_list_files (pageSize 2), Gemini API only. */
  @Test
  fun testListFiles() = runTest {
    listOf(false, true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.files.ListTest.testListFiles.$suffix"
      val client = createClient(enterprise, testName)

      if (enterprise) {
        // The Files API is Gemini Developer API only.
        val e =
          kotlin.runCatching { client.files.list(ListFilesConfig(pageSize = 2)).take(1).toList() }
            .exceptionOrNull()
        assertNotNull(e, "Expected the Agent Platform backend to reject files.list")
        assertTrue(
          e.message!!.contains("only supported in Gemini Developer API mode"),
          "Unexpected message: ${e.message}",
        )
        return@forEach
      }

      runLive {
        val pager = client.files.list(ListFilesConfig(pageSize = 2))
        assertNotNull(pager)
        val files = pager.take(2).toList()
        assertTrue(files.size <= 2, "Expected at most 2 files, got ${files.size}")
      }
    }
  }
}
