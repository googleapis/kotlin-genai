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

import com.google.genai.kotlin.shared.SHARED_MODEL
import com.google.genai.kotlin.shared.SharedTestBase
import com.google.genai.kotlin.types.CreateTuningJobConfig
import com.google.genai.kotlin.types.TuningDataset
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 120.seconds, testBody = testBody)

class CreateTest : SharedTestBase() {
  // Canonical tunings baseModel/dataset. Tuning is not supported on the global
  // endpoint, so these pin the Vertex client to a region -- the same override the
  // Python shared suite applies in tests/conftest.py (cl/955362109).
  private val tunableModel = "gemini-3.1-flash-lite"

  private fun trainingDataset() =
    TuningDataset(
      gcsUri =
        "gs://cloud-samples-data/ai-platform/generative_ai/gemini-2_0/text/sft_train_data.jsonl"
    )

  /**
   * Canonical tunings/create: test_tune.
   *
   * The canonical exceptionIfMldev is "only supported in Gemini Enterprise Agent Platform mode".
   * Kotlin differs: tunings.tune does have a Gemini API code path, but it rejects the canonical
   * gcsUri dataset, so the mldev half asserts that rejection instead.
   */
  @Test
  fun testTune() = runTest {
    listOf(false, true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.tunings.CreateTest.testTune.$suffix"
      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      if (!enterprise) {
        val e =
          kotlin
            .runCatching {
              client.tunings.tune(
                baseModel = tunableModel,
                trainingDataset = trainingDataset(),
                config = CreateTuningJobConfig(epochCount = 1),
              )
            }
            .exceptionOrNull()
        assertNotNull(e, "Expected the Gemini API backend to reject a gcsUri tuning dataset")
        assertTrue(
          e.message!!.contains("gcsUri parameter is not supported in Gemini API"),
          "Unexpected message: ${e.message}",
        )
        return@forEach
      }

      runLive {
        val job =
          client.tunings.tune(
            baseModel = tunableModel,
            trainingDataset = trainingDataset(),
            config = CreateTuningJobConfig(epochCount = 1),
          )
        assertNotNull(job.name)
        // Cancel the submitted job: without this the nightly leaves a real SFT
        // training job running to completion every night.
        tryCleanup { client.tunings.cancel(name = job.name!!) }
      }
    }
  }
}
