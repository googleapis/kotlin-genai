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

/** Enum that controls the safety filter level for objectionable content. */
@Serializable
@JvmInline
value class SafetyFilterLevel(val value: String) {
  companion object {

    /**  */
    val BLOCK_LOW_AND_ABOVE = SafetyFilterLevel("BLOCK_LOW_AND_ABOVE")

    /**  */
    val BLOCK_MEDIUM_AND_ABOVE = SafetyFilterLevel("BLOCK_MEDIUM_AND_ABOVE")

    /**  */
    val BLOCK_ONLY_HIGH = SafetyFilterLevel("BLOCK_ONLY_HIGH")

    /**  */
    val BLOCK_NONE = SafetyFilterLevel("BLOCK_NONE")
  }
}
