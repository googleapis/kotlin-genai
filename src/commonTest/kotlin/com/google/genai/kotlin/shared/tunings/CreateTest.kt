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
import com.google.genai.kotlin.types.CreateTuningJobConfig
import com.google.genai.kotlin.types.TuningDataset
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 120.seconds, testBody = testBody)

class CreateTest : SharedTestBase() {
  // Tuning is not supported on the global endpoint, so these pin the client to a region.
  private val tunableModel = "gemini-3.1-flash-lite"

  private fun trainingDataset() =
    TuningDataset(
      gcsUri =
        "gs://cloud-samples-data/ai-platform/generative_ai/gemini-2_0/text/sft_train_data.jsonl"
    )

  /** Canonical tunings/create: test_tune. */
  @Test
  fun testTune() = runTest {
    listOf(true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.tunings.CreateTest.testTune.$suffix"
      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      runLive {
        val job =
          client.tunings.tune(
            baseModel = tunableModel,
            trainingDataset = trainingDataset(),
            config = CreateTuningJobConfig(epochCount = 1),
          )
        assertNotNull(job.name)
        // Cancel, or the nightly leaves a real SFT job running to completion.
        tryCleanup { client.tunings.cancel(name = job.name!!) }
      }
    }
  }
}
