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

/** The tuning task for Veo. This enum is not supported in Gemini API. */
@Serializable
@JvmInline
value class TuningTask(val value: String) {
  companion object {

    /** Default value. This value is unused. */
    val TUNING_TASK_UNSPECIFIED = TuningTask("TUNING_TASK_UNSPECIFIED")

    /** Tuning task for image to video. */
    val TUNING_TASK_I2V = TuningTask("TUNING_TASK_I2V")

    /** Tuning task for text to video. */
    val TUNING_TASK_T2V = TuningTask("TUNING_TASK_T2V")

    /** Tuning task for reference to video. */
    val TUNING_TASK_R2V = TuningTask("TUNING_TASK_R2V")
  }
}
