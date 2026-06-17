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

/** Function calling mode. */
@Serializable
@JvmInline
value class FunctionCallingConfigMode(val value: String) {
  companion object {

    /** Unspecified function calling mode. This value should not be used. */
    val MODE_UNSPECIFIED = FunctionCallingConfigMode("MODE_UNSPECIFIED")

    /**
     * Default model behavior, model decides to predict either function calls or natural language
     * response.
     */
    val AUTO = FunctionCallingConfigMode("AUTO")

    /**
     * Model is constrained to always predicting function calls only. If "allowed_function_names"
     * are set, the predicted function calls will be limited to any one of "allowed_function_names",
     * else the predicted function calls will be any one of the provided "function_declarations".
     */
    val ANY = FunctionCallingConfigMode("ANY")

    /**
     * Model will not predict any function calls. Model behavior is same as when not passing any
     * function declarations.
     */
    val NONE = FunctionCallingConfigMode("NONE")

    /**
     * Model is constrained to predict either function calls or natural language response. If
     * "allowed_function_names" are set, the predicted function calls will be limited to any one of
     * "allowed_function_names", else the predicted function calls will be any one of the provided
     * "function_declarations".
     */
    val VALIDATED = FunctionCallingConfigMode("VALIDATED")
  }
}
