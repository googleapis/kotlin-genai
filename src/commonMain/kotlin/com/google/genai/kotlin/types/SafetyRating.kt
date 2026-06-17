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
 * A safety rating for a piece of content. The safety rating contains the harm category and the harm
 * probability level.
 */
@Serializable
data class SafetyRating(

  /** Output only. Indicates whether the content was blocked because of this rating. */
  val blocked: Boolean? = null,

  /** Output only. The harm category of this rating. */
  val category: HarmCategory? = null,

  /**
   * Output only. The overwritten threshold for the safety category of Gemini 2.0 image out. If
   * minors are detected in the output image, the threshold of each safety category will be
   * overwritten if user sets a lower threshold. This field is not supported in Gemini API.
   */
  val overwrittenThreshold: HarmBlockThreshold? = null,

  /** Output only. The probability of harm for this category. */
  val probability: HarmProbability? = null,

  /**
   * Output only. The probability score of harm for this category. This field is not supported in
   * Gemini API.
   */
  val probabilityScore: Double? = null,

  /**
   * Output only. The severity of harm for this category. This field is not supported in Gemini API.
   */
  val severity: HarmSeverity? = null,

  /**
   * Output only. The severity score of harm for this category. This field is not supported in
   * Gemini API.
   */
  val severityScore: Double? = null,
)
