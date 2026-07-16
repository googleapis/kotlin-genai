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

/** Adapter size for tuning. This enum is not supported in Gemini API. */
@Serializable
@JvmInline
value class AdapterSize(val value: String) {
  companion object {

    /** Adapter size is unspecified. */
    val ADAPTER_SIZE_UNSPECIFIED = AdapterSize("ADAPTER_SIZE_UNSPECIFIED")

    /** Adapter size 1. */
    val ADAPTER_SIZE_ONE = AdapterSize("ADAPTER_SIZE_ONE")

    /** Adapter size 2. */
    val ADAPTER_SIZE_TWO = AdapterSize("ADAPTER_SIZE_TWO")

    /** Adapter size 4. */
    val ADAPTER_SIZE_FOUR = AdapterSize("ADAPTER_SIZE_FOUR")

    /** Adapter size 8. */
    val ADAPTER_SIZE_EIGHT = AdapterSize("ADAPTER_SIZE_EIGHT")

    /** Adapter size 16. */
    val ADAPTER_SIZE_SIXTEEN = AdapterSize("ADAPTER_SIZE_SIXTEEN")

    /** Adapter size 32. */
    val ADAPTER_SIZE_THIRTY_TWO = AdapterSize("ADAPTER_SIZE_THIRTY_TWO")
  }
}
