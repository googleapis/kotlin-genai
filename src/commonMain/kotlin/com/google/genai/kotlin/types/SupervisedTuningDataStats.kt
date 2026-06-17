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

/** Tuning data statistics for Supervised Tuning. This data type is not supported in Gemini API. */
@Serializable
data class SupervisedTuningDataStats(

  /**
   * Output only. For each index in `truncated_example_indices`, the user-facing reason why the
   * example was dropped.
   */
  val droppedExampleReasons: List<String>? = null,

  /** Output only. Number of billable characters in the tuning dataset. */
  val totalBillableCharacterCount: Long? = null,

  /** Output only. Number of billable tokens in the tuning dataset. */
  val totalBillableTokenCount: Long? = null,

  /**
   * Output only. The number of examples in the dataset that have been dropped. An example can be
   * dropped for reasons including: too many tokens, contains an invalid image, contains too many
   * images, etc.
   */
  val totalTruncatedExampleCount: Long? = null,

  /** Output only. Number of tuning characters in the tuning dataset. */
  val totalTuningCharacterCount: Long? = null,

  /** Output only. A partial sample of the indices (starting from 1) of the dropped examples. */
  val truncatedExampleIndices: List<Long>? = null,

  /** Output only. Number of examples in the tuning dataset. */
  val tuningDatasetExampleCount: Long? = null,

  /** Output only. Number of tuning steps for this Tuning Job. */
  val tuningStepCount: Long? = null,

  /** Output only. Sample user messages in the training dataset uri. */
  val userDatasetExamples: List<Content>? = null,

  /** Output only. Dataset distributions for the user input tokens. */
  val userInputTokenDistribution: SupervisedTuningDatasetDistribution? = null,

  /** Output only. Dataset distributions for the messages per example. */
  val userMessagePerExampleDistribution: SupervisedTuningDatasetDistribution? = null,

  /** Output only. Dataset distributions for the user output tokens. */
  val userOutputTokenDistribution: SupervisedTuningDatasetDistribution? = null,
)
