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

/** Outcome of the code execution. */
@Serializable
@JvmInline
value class Outcome(val value: String) {
  companion object {

    /** Unspecified status. This value should not be used. */
    val OUTCOME_UNSPECIFIED = Outcome("OUTCOME_UNSPECIFIED")

    /** Code execution completed successfully. `output` contains the stdout, if any. */
    val OUTCOME_OK = Outcome("OUTCOME_OK")

    /** Code execution failed. `output` contains the stderr and stdout, if any. */
    val OUTCOME_FAILED = Outcome("OUTCOME_FAILED")

    /**
     * Code execution ran for too long, and was cancelled. There may or may not be a partial
     * `output` present.
     */
    val OUTCOME_DEADLINE_EXCEEDED = Outcome("OUTCOME_DEADLINE_EXCEEDED")
  }
}
