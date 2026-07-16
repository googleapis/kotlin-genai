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

/** Output only. Pairwise metric choice. This enum is not supported in Gemini API. */
@Serializable
@JvmInline
value class PairwiseChoice(val value: String) {
  companion object {

    /** Unspecified prediction choice. */
    val PAIRWISE_CHOICE_UNSPECIFIED = PairwiseChoice("PAIRWISE_CHOICE_UNSPECIFIED")

    /** Baseline prediction wins */
    val BASELINE = PairwiseChoice("BASELINE")

    /** Candidate prediction wins */
    val CANDIDATE = PairwiseChoice("CANDIDATE")

    /** Winner cannot be determined */
    val TIE = PairwiseChoice("TIE")
  }
}
