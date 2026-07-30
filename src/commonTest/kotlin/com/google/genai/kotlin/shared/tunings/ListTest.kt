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

package com.google.genai.kotlin.shared.tunings

import com.google.genai.kotlin.shared.SharedTestBase
import com.google.genai.kotlin.types.ListTuningJobsConfig
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 120.seconds, testBody = testBody)

class ListTest : SharedTestBase() {
  /** Canonical tunings/list: test_default (pageSize 1), Agent Platform only. */
  @Test
  fun testDefault() = runTest {
    listOf(true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.tunings.ListTest.testDefault.$suffix"
      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      runLive {
        val pager = client.tunings.list(ListTuningJobsConfig(pageSize = 1))
        assertNotNull(pager)
        val jobs = pager.take(1).toList()
        assertTrue(jobs.size <= 1, "Expected at most 1 tuning job per page, got ${jobs.size}")
      }
    }
  }
}
