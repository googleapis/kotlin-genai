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

/** Determines how likely speech is to be detected. This enum is not supported in Vertex AI. */
@Serializable
@JvmInline
value class StartSensitivity(val value: String) {
  companion object {

    /** The default is START_SENSITIVITY_HIGH. */
    val START_SENSITIVITY_UNSPECIFIED = StartSensitivity("START_SENSITIVITY_UNSPECIFIED")

    /** Automatic detection will detect the start of speech more often. */
    val START_SENSITIVITY_HIGH = StartSensitivity("START_SENSITIVITY_HIGH")

    /** Automatic detection will detect the start of speech less often. */
    val START_SENSITIVITY_LOW = StartSensitivity("START_SENSITIVITY_LOW")
  }
}
