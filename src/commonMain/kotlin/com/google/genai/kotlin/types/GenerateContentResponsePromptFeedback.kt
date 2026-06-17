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

import kotlinx.serialization.Serializable

/**
 * Content filter results for a prompt sent in the request. Note: This is sent only in the first
 * stream chunk and only if no candidates were generated due to content violations.
 */
@Serializable
data class GenerateContentResponsePromptFeedback(

  /** Output only. The reason why the prompt was blocked. */
  val blockReason: BlockedReason? = null,

  /**
   * Output only. A readable message that explains the reason why the prompt was blocked. This field
   * is not supported in Gemini API.
   */
  val blockReasonMessage: String? = null,

  /** Output only. A list of safety ratings for the prompt. There is one rating per category. */
  val safetyRatings: List<SafetyRating>? = null,
)
