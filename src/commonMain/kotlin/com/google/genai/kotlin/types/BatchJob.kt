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

/** Config for batches.create return value. */
@Serializable
data class BatchJob(

  /** The resource name of the BatchJob. Output only.". */
  val name: String? = null,

  /** The display name of the BatchJob. */
  val displayName: String? = null,

  /** The state of the BatchJob. */
  val state: JobState? = null,

  /**
   * Output only. Only populated when the job's state is JOB_STATE_FAILED or JOB_STATE_CANCELLED.
   */
  val error: JobError? = null,

  /** The time when the BatchJob was created. */
  @Serializable(with = InstantSerializer::class) val createTime: Instant? = null,

  /** Output only. Time when the Job for the first time entered the `JOB_STATE_RUNNING` state. */
  @Serializable(with = InstantSerializer::class) val startTime: Instant? = null,

  /**
   * The time when the BatchJob was completed. This field is for Gemini Enterprise Agent Platform
   * only.
   */
  @Serializable(with = InstantSerializer::class) val endTime: Instant? = null,

  /** The time when the BatchJob was last updated. */
  @Serializable(with = InstantSerializer::class) val updateTime: Instant? = null,

  /** The name of the model that produces the predictions via the BatchJob. */
  val model: String? = null,

  /** Configuration for the input data. This field is for Gemini Enterprise Agent Platform only. */
  val src: BatchJobSource? = null,

  /** Configuration for the output data. */
  val dest: BatchJobDestination? = null,

  /** Information further describing the output of this job. Output only. */
  val outputInfo: BatchJobOutputInfo? = null,

  /**
   * Statistics on completed and failed prediction instances. This field is for Gemini Enterprise
   * Agent Platform only.
   */
  val completionStats: CompletionStats? = null,
)
