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
 * Specifies the function Behavior. Currently only non-blocking functions are supported. If not
 * specified, the system keeps the current function call behavior. This field is currently only
 * supported by the BidiGenerateContent method.
 */
@Serializable
@JvmInline
value class Behavior(val value: String) {
  companion object {

    /** This value is unspecified. */
    val UNSPECIFIED = Behavior("UNSPECIFIED")

    /**
     * If set, the system will wait to receive the function response before continuing the
     * conversation.
     */
    val BLOCKING = Behavior("BLOCKING")

    /**
     * If set, the system will not wait to receive the function response. Instead, it will attempt
     * to handle function responses as they become available while maintaining the conversation
     * between the user and the model.
     */
    val NON_BLOCKING = Behavior("NON_BLOCKING")
  }
}
