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
 * JsonMatchExpression supports converting the parsed responses to JSON format, finding the value in
 * the JSON response that matches the key_name in the first level, and performing
 * StringMatchExpression operation on the matched JSON value. This data type is not supported in
 * Gemini API.
 */
@Serializable
data class ReinforcementTuningStringMatchRewardScorerJsonMatchExpression(

  /**
   * The key name to find the value in the parsed response that's in JSON format. Only first-level
   * key matching is supported.
   */
  val keyName: String? = null,

  /**
   * String match expression to match against the extracted value from the JSON representation of
   * the parsed response.
   */
  val valueStringMatchExpression: ReinforcementTuningStringMatchRewardScorerStringMatchExpression? =
    null,
)
