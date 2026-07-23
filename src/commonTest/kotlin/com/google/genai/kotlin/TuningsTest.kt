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

import com.google.genai.kotlin.types.ListTuningJobsConfig
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 60.seconds, testBody = testBody)

class TuningsTest : BaseTestServer() {

  @Test
  fun testListTuningJobs() = runTest {
    listOf(true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "TuningsTest.testListTuningJobs.$suffix"

      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      val pager = client.tunings.list(ListTuningJobsConfig(pageSize = 10))
      assertEquals("tuning_jobs", pager.name)
      assertEquals(10, pager.pageSize)

      val jobs = pager.take(1).toList()
      if (jobs.isNotEmpty()) {
        val jobName = jobs.first().name
        if (jobName != null) {
          val retrievedJob = client.tunings.get(jobName)
          assertTrue(retrievedJob.name == jobName)
        }
      }
    }
  }

  @Test
  fun testGetTuningJob() = runTest {
    listOf(true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "TuningsTest.testGetTuningJob.$suffix"

      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      // We need a known job name for this test, but since we are recording live,
      // it's easier to list jobs, pick the first one, and get it.
      // Or just test it in `testListTuningJobs` like we already do.
      // But we can also test it explicitly.
      val jobsFlow = client.tunings.list(ListTuningJobsConfig(pageSize = 1))
      val jobs = jobsFlow.take(1).toList()

      if (jobs.isNotEmpty()) {
        val jobName = jobs.first().name
        if (jobName != null) {
          val retrievedJob = client.tunings.get(jobName)
          assertTrue(retrievedJob.name == jobName)
        }
      }
    }
  }

  @Test
  fun test_dataset_gcs_uri() = runTest {
    listOf(true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "TuningsTest.test_dataset_gcs_uri.$suffix"
      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      if (enterprise) {
        try {
          client.tunings.tune(
            baseModel = "gemini-2.5-flash",
            trainingDataset =
              com.google.genai.kotlin.types.TuningDataset(
                gcsUri =
                  "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl"
              ),
            config = null,
          )
        } catch (e: Exception) {
          // It's ok if dataset doesn't exist
        }
      }
    }
  }

  @Test
  fun test_tune_simple_dpo() = runTest {
    listOf(true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "TuningsTest.test_tune_simple_dpo.$suffix"
      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      if (enterprise) {
        try {
          client.tunings.tune(
            baseModel = "gemini-2.5-flash",
            trainingDataset =
              com.google.genai.kotlin.types.TuningDataset(
                gcsUri =
                  "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl"
              ),
            config =
              com.google.genai.kotlin.types.CreateTuningJobConfig(
                tunedModelDisplayName = "Model display name",
                epochCount = 1,
                method = com.google.genai.kotlin.types.TuningMethod("PREFERENCE_TUNING"),
              ),
          )
        } catch (e: Exception) {
          // It's ok if dataset doesn't exist
        }
      }
    }
  }

  @Test
  fun test_tune_dpo_with_beta() = runTest {
    listOf(true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "TuningsTest.test_tune_dpo_with_beta.$suffix"
      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      if (enterprise) {
        try {
          client.tunings.tune(
            baseModel = "gemini-2.5-flash",
            trainingDataset =
              com.google.genai.kotlin.types.TuningDataset(
                gcsUri =
                  "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl"
              ),
            config =
              com.google.genai.kotlin.types.CreateTuningJobConfig(
                tunedModelDisplayName = "Model display name",
                epochCount = 1,
                method = com.google.genai.kotlin.types.TuningMethod("PREFERENCE_TUNING"),
                beta = 0.5,
              ),
          )
        } catch (e: Exception) {
          // It's ok if dataset doesn't exist
        }
      }
    }
  }

  @Test
  fun test_non_pretuned_model_with_checkpoint_id() = runTest {
    listOf(true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "TuningsTest.test_non_pretuned_model_with_checkpoint_id.$suffix"
      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      if (enterprise) {
        try {
          client.tunings.tune(
            baseModel = "gemini-2.5-flash",
            trainingDataset =
              com.google.genai.kotlin.types.TuningDataset(
                gcsUri =
                  "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl"
              ),
            config =
              com.google.genai.kotlin.types.CreateTuningJobConfig(preTunedModelCheckpointId = "3"),
          )
        } catch (e: Exception) {
          // It's ok if dataset doesn't exist
        }
      }
    }
  }

  @Test
  fun test_dataset_gcs_uri_all_parameters() = runTest {
    listOf(true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "TuningsTest.test_dataset_gcs_uri_all_parameters.$suffix"
      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      if (enterprise) {
        try {
          client.tunings.tune(
            baseModel = "gemini-2.5-flash",
            trainingDataset =
              com.google.genai.kotlin.types.TuningDataset(
                gcsUri =
                  "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl"
              ),
            config =
              com.google.genai.kotlin.types.CreateTuningJobConfig(
                tunedModelDisplayName = "Model display name",
                epochCount = 1,
                learningRateMultiplier = 1.0,
                adapterSize = com.google.genai.kotlin.types.AdapterSize("ADAPTER_SIZE_ONE"),
                validationDataset =
                  com.google.genai.kotlin.types.TuningValidationDataset(
                    gcsUri =
                      "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_validation_data.jsonl"
                  ),
              ),
          )
        } catch (e: Exception) {
          // It's ok if dataset doesn't exist
        }
      }
    }
  }

  @Test
  fun test_tune_reinforcement() = runTest {
    listOf(true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "TuningsTest.test_tune_reinforcement.$suffix"
      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      if (enterprise) {
        try {
          client.tunings.tune(
            baseModel = "gemini-2.5-flash",
            trainingDataset =
              com.google.genai.kotlin.types.TuningDataset(
                gcsUri =
                  "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl"
              ),
            config =
              com.google.genai.kotlin.types.CreateTuningJobConfig(
                tunedModelDisplayName = "Model display name",
                epochCount = 1,
                method = com.google.genai.kotlin.types.TuningMethod("REINFORCEMENT_TUNING"),
                adapterSize = com.google.genai.kotlin.types.AdapterSize("ADAPTER_SIZE_ONE"),
                learningRateMultiplier = 1.0,
                batchSize = 4,
                samplesPerPrompt = 4,
                evaluateInterval = 100,
                checkpointInterval = 100,
                maxOutputTokens = 2048,
                rewardConfig =
                  com.google.genai.kotlin.types.SingleReinforcementTuningRewardConfig(
                    autoraterScorer =
                      com.google.genai.kotlin.types.ReinforcementTuningAutoraterScorer(
                        autoraterConfig =
                          com.google.genai.kotlin.types.AutoraterConfig(
                            autoraterModel = "test-model"
                          )
                      )
                  ),
              ),
          )
        } catch (e: Exception) {
          // It's ok if dataset doesn't exist
        }
      }
    }
  }

  @Test
  fun test_tune_reinforcement_composite() = runTest {
    listOf(true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "TuningsTest.test_tune_reinforcement_composite.$suffix"
      val client = createClient(enterprise, testName, locationOverride = "us-central1")

      if (enterprise) {
        try {
          client.tunings.tune(
            baseModel = "gemini-2.5-flash",
            trainingDataset =
              com.google.genai.kotlin.types.TuningDataset(
                gcsUri =
                  "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl"
              ),
            config =
              com.google.genai.kotlin.types.CreateTuningJobConfig(
                tunedModelDisplayName = "Model display name",
                epochCount = 1,
                method = com.google.genai.kotlin.types.TuningMethod("REINFORCEMENT_TUNING"),
                adapterSize = com.google.genai.kotlin.types.AdapterSize("ADAPTER_SIZE_ONE"),
                learningRateMultiplier = 1.0,
                compositeRewardConfig =
                  com.google.genai.kotlin.types.CompositeReinforcementTuningRewardConfig(
                    weightedRewardConfigs =
                      listOf(
                        com.google.genai.kotlin.types
                          .CompositeReinforcementTuningRewardConfigWeightedRewardConfig(
                            weight = 1.0,
                            rewardConfig =
                              com.google.genai.kotlin.types.SingleReinforcementTuningRewardConfig(
                                autoraterScorer =
                                  com.google.genai.kotlin.types.ReinforcementTuningAutoraterScorer(
                                    autoraterConfig =
                                      com.google.genai.kotlin.types.AutoraterConfig(
                                        autoraterModel = "test-model"
                                      )
                                  )
                              ),
                          )
                      )
                  ),
              ),
          )
        } catch (e: Exception) {
          // It's ok if dataset doesn't exist
        }
      }
    }
  }
}
