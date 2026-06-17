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

// Auto-generated code. Do not edit.

package com.google.genai.kotlin.types

import kotlinx.serialization.Serializable

/** Fine-tuning job creation parameters - optional fields. */
@Serializable
data class CreateTuningJobParameters(

  /** The base model that is being tuned, e.g., "gemini-2.5-flash". */
  val baseModel: String? = null,

  /**
   * Cloud Storage path to file containing training dataset for tuning. The dataset must be
   * formatted as a JSONL file.
   */
  val trainingDataset: TuningDataset? = null,

  /** Configuration for the tuning job. */
  val config: CreateTuningJobConfig? = null,
)
