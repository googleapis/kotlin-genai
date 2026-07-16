/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.kotlin.examples

import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.AdapterSize
import com.google.genai.kotlin.types.AutoraterConfig
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.CreateTuningJobConfig
import com.google.genai.kotlin.types.ListTuningJobsConfig
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.ReinforcementTuningAutoraterScorer
import com.google.genai.kotlin.types.ReinforcementTuningExample
import com.google.genai.kotlin.types.SingleReinforcementTuningRewardConfig
import com.google.genai.kotlin.types.TuningDataset
import com.google.genai.kotlin.types.TuningMethod
import com.google.genai.kotlin.types.TuningValidationDataset
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to manage Tunings (Tuning Jobs, SFT, Preference
 * Tuning/DPO, Distillation, RLHF, and Reward Validation).
 *
 * Note: Tunings are only supported in Gemini Enterprise Agent Platform (Vertex AI).
 *
 * Usage:
 *
 * 1. Setup ADC to get credentials:
 *    https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * Then set Project, Location (must be us-central1 for Tunings), and GOOGLE_GENAI_USE_ENTERPRISE
 * flag as environment variables:
 *
 * export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT_NUMBER
 *
 * export GOOGLE_CLOUD_LOCATION=us-central1
 *
 * export GOOGLE_GENAI_USE_ENTERPRISE=true
 *
 * 2. Run the example:
 * <pre>
 * ./gradlew :example:runExample -PmainClass=com.google.genai.kotlin.examples.TuningsOperations
 * </pre>
 */
object TuningsOperations {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      Client().use { client ->
        try {
          // 1. List existing Tuning Jobs
          println("--- 1. Listing Tuning Jobs ---")
          val jobsFlow = client.tunings.list(ListTuningJobsConfig(pageSize = 5))
          val jobs = jobsFlow.take(5).toList()

          if (jobs.isEmpty()) {
            println("No tuning jobs found.")
          } else {
            println("Found ${jobs.size} tuning jobs:")
            for (job in jobs) {
              println("- ${job.name} (State: ${job.state})")
            }

            // 2. Get details for the first job
            val firstJobName = jobs.first().name
            if (firstJobName != null) {
              println("\n--- 2. Fetching details for $firstJobName ---")
              val detailedJob = client.tunings.get(firstJobName)
              println("Detailed Job: $detailedJob")
            }
          }

          // 3. Supervised Fine-Tuning (SFT)
          println("\n--- 3. Starting Supervised Fine-Tuning (SFT) Job ---")
          val sftJob =
            client.tunings.tune(
              baseModel = "gemini-2.5-flash",
              trainingDataset =
                TuningDataset(
                  gcsUri =
                    "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl"
                ),
              config =
                CreateTuningJobConfig(
                  tunedModelDisplayName = "My Supervised Tuned Model",
                  epochCount = 1,
                  learningRateMultiplier = 1.0,
                  adapterSize = AdapterSize("ADAPTER_SIZE_ONE"),
                ),
            )
          println("SFT Job created: ${sftJob.name} (State: ${sftJob.state})")

          // 4. Preference Tuning (DPO)
          println("\n--- 4. Starting Preference Tuning (DPO) Job ---")
          val dpoJob =
            client.tunings.tune(
              baseModel = "gemini-2.5-flash",
              trainingDataset =
                TuningDataset(
                  gcsUri =
                    "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl"
                ),
              config =
                CreateTuningJobConfig(
                  tunedModelDisplayName = "My Preference Tuned Model",
                  method = TuningMethod("PREFERENCE_TUNING"),
                  epochCount = 1,
                ),
            )
          println("Preference Tuning Job created: ${dpoJob.name} (State: ${dpoJob.state})")

          // 5. Distillation Tuning (Teacher-Student)
          println("\n--- 5. Starting Distillation Tuning Job ---")
          val distillationJob =
            client.tunings.tune(
              baseModel = "qwen/qwen3@qwen3-4b", // Student model
              trainingDataset =
                TuningDataset(gcsUri = "gs://YOUR_BUCKET_NAME/distillation_dataset.jsonl"),
              config =
                CreateTuningJobConfig(
                  tunedModelDisplayName = "My Distilled Model",
                  method = TuningMethod("DISTILLATION"),
                  baseTeacherModel = "deepseek-ai/deepseek-r1-0528-maas",
                  epochCount = 20,
                  validationDataset =
                    TuningValidationDataset(
                      gcsUri = "gs://YOUR_BUCKET_NAME/distillation_val_dataset.jsonl"
                    ),
                  outputUri = "gs://YOUR_BUCKET_NAME/distillation_output",
                ),
            )
          println(
            "Distillation Job created: ${distillationJob.name} (State: ${distillationJob.state})"
          )

          // 6. Tuning on a Pre-Tuned Model Checkpoint
          println("\n--- 6. Starting Tuning Job on a Pre-Tuned Model ---")
          val preTunedJob =
            client.tunings.tune(
              baseModel =
                "projects/YOUR_PROJECT_NUMBER/locations/us-central1/models/YOUR_PRETUNED_MODEL_ID",
              trainingDataset =
                TuningDataset(
                  gcsUri =
                    "gs://cloud-samples-data/ai-platform/generative_ai/gemini-2_0/text/sft_train_data.jsonl"
                ),
              config =
                CreateTuningJobConfig(
                  tunedModelDisplayName = "My Further Tuned Model",
                  preTunedModelCheckpointId = "3",
                ),
            )
          println("Pre-tuned Job created: ${preTunedJob.name} (State: ${preTunedJob.state})")

          // 7. Reinforcement Tuning (RLHF)
          println("\n--- 7. Starting Reinforcement Tuning Job ---")
          val rlhfJob =
            client.tunings.tune(
              baseModel = "gemini-2.5-flash",
              trainingDataset =
                TuningDataset(
                  gcsUri =
                    "gs://cloud-samples-data/ai-platform/generative_ai/gemini-1_5/text/sft_train_data.jsonl"
                ),
              config =
                CreateTuningJobConfig(
                  tunedModelDisplayName = "My RLHF Model",
                  method = TuningMethod("REINFORCEMENT_TUNING"),
                  epochCount = 1,
                  learningRateMultiplier = 1.0,
                  adapterSize = AdapterSize("ADAPTER_SIZE_ONE"),
                  rewardConfig =
                    SingleReinforcementTuningRewardConfig(
                      autoraterScorer =
                        ReinforcementTuningAutoraterScorer(
                          autoraterConfig = AutoraterConfig(autoraterModel = "YOUR_AUTORATER_MODEL")
                        )
                    ),
                ),
            )
          println("RLHF Job created: ${rlhfJob.name} (State: ${rlhfJob.state})")

          // 8. Validate Reward
          println("\n--- 8. Validating Reward for Reinforcement Tuning ---")
          val project = System.getenv("GOOGLE_CLOUD_PROJECT") ?: "YOUR_PROJECT_NUMBER"
          val location = System.getenv("GOOGLE_CLOUD_LOCATION") ?: "us-central1"
          val rewardResponse =
            client.tunings.validateReward(
              parent = "projects/$project/locations/$location",
              sampleResponse = Content(parts = listOf(Part(text = "The answer is 42."))),
              example =
                ReinforcementTuningExample(
                  contents =
                    listOf(Content(parts = listOf(Part(text = "What is the answer to life?"))))
                ),
              singleRewardConfig =
                SingleReinforcementTuningRewardConfig(
                  autoraterScorer =
                    ReinforcementTuningAutoraterScorer(
                      autoraterConfig = AutoraterConfig(autoraterModel = "YOUR_AUTORATER_MODEL")
                    )
                ),
            )
          println("Reward Validation Score: ${rewardResponse.overallReward}")
        } catch (e: Exception) {
          System.err.println("Operation failed: ${e.message}")
          e.printStackTrace()
        }
      }

      kotlin.system.exitProcess(0)
    }
}
