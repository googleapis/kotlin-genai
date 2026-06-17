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

import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/**
 * Output only. The detail state of the tuning job (while the overall `JobState` is running). This
 * enum is not supported in Gemini API.
 */
@Serializable
@JvmInline
value class TuningJobState(val value: String) {
  companion object {

    /** Default tuning job state. */
    val TUNING_JOB_STATE_UNSPECIFIED = TuningJobState("TUNING_JOB_STATE_UNSPECIFIED")

    /** Tuning job is waiting for job quota. */
    val TUNING_JOB_STATE_WAITING_FOR_QUOTA = TuningJobState("TUNING_JOB_STATE_WAITING_FOR_QUOTA")

    /** Tuning job is validating the dataset. */
    val TUNING_JOB_STATE_PROCESSING_DATASET = TuningJobState("TUNING_JOB_STATE_PROCESSING_DATASET")

    /** Tuning job is waiting for hardware capacity. */
    val TUNING_JOB_STATE_WAITING_FOR_CAPACITY =
      TuningJobState("TUNING_JOB_STATE_WAITING_FOR_CAPACITY")

    /** Tuning job is running. */
    val TUNING_JOB_STATE_TUNING = TuningJobState("TUNING_JOB_STATE_TUNING")

    /** Tuning job is doing some post processing steps. */
    val TUNING_JOB_STATE_POST_PROCESSING = TuningJobState("TUNING_JOB_STATE_POST_PROCESSING")
  }
}
