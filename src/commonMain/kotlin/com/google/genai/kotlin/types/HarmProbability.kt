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

/** Output only. The probability of harm for this category. */
@Serializable
@JvmInline
value class HarmProbability(val value: String) {
  companion object {

    /** The harm probability is unspecified. */
    val HARM_PROBABILITY_UNSPECIFIED = HarmProbability("HARM_PROBABILITY_UNSPECIFIED")

    /** The harm probability is negligible. */
    val NEGLIGIBLE = HarmProbability("NEGLIGIBLE")

    /** The harm probability is low. */
    val LOW = HarmProbability("LOW")

    /** The harm probability is medium. */
    val MEDIUM = HarmProbability("MEDIUM")

    /** The harm probability is high. */
    val HIGH = HarmProbability("HIGH")
  }
}
