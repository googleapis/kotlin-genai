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

/** Server validation behavior when thought_signatures fail prefix binding. */
@Serializable
@JvmInline
value class PrefixMismatchBehavior(val value: String) {
  override fun toString(): String = value

  companion object {

    /** Uses the model's default validation policy. */
    val PREFIX_MISMATCH_BEHAVIOR_UNSPECIFIED =
      PrefixMismatchBehavior("PREFIX_MISMATCH_BEHAVIOR_UNSPECIFIED")

    /**
     * Fails with `HTTP 400` (`INVALID_ARGUMENT`) when input `thought_signature`s fail same-model
     * prefix validation. Cross-model and cross-organization signatures are still dropped silently
     * (`200 OK`).
     */
    val ERROR = PrefixMismatchBehavior("ERROR")

    /**
     * Drops prefix-mismatched `thought_signature`s on the server while keeping visible text,
     * `function_call`, and media parts intact. Corrupted signature bytes and trailing model
     * prefills still fail with `HTTP 400`.
     */
    val DROP = PrefixMismatchBehavior("DROP")
  }
}
