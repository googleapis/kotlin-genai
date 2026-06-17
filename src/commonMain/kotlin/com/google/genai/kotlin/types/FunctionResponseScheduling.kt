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
 * Specifies how the response should be scheduled in the conversation. Only applicable to
 * NON_BLOCKING function calls, is ignored otherwise. Defaults to WHEN_IDLE.
 */
@Serializable
@JvmInline
value class FunctionResponseScheduling(val value: String) {
  companion object {

    /** This value is unused. */
    val SCHEDULING_UNSPECIFIED = FunctionResponseScheduling("SCHEDULING_UNSPECIFIED")

    /** Only add the result to the conversation context, do not interrupt or trigger generation. */
    val SILENT = FunctionResponseScheduling("SILENT")

    /**
     * Add the result to the conversation context, and prompt to generate output without
     * interrupting ongoing generation.
     */
    val WHEN_IDLE = FunctionResponseScheduling("WHEN_IDLE")

    /**
     * Add the result to the conversation context, interrupt ongoing generation and prompt to
     * generate output.
     */
    val INTERRUPT = FunctionResponseScheduling("INTERRUPT")
  }
}
