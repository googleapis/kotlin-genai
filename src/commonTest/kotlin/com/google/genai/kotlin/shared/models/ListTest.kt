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

package com.google.genai.kotlin.shared.models

import com.google.genai.kotlin.shared.SHARED_MODEL
import com.google.genai.kotlin.shared.SharedTestBase
import com.google.genai.kotlin.types.ListModelsConfig
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
  /** Canonical models/list: test_list (pageSize 1). */
  @Test
  fun testList() = runTest {
    listOf(false, true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.models.ListTest.testList.$suffix"
      val client = createClient(enterprise, testName)

      runLive {
        val pager = client.models.list(ListModelsConfig(pageSize = 1))
        assertNotNull(pager)
        val models = pager.take(1).toList()
        assertTrue(models.size <= 1, "Expected at most 1 model per page, got ${models.size}")
      }
    }
  }
}
