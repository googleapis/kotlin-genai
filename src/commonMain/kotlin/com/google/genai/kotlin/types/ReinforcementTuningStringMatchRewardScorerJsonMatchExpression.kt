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
 * Converts parsed responses to JSON format, finds the first-level matching key, then performs
 * StringMatchExpression on the value.
 */
@Serializable
data class ReinforcementTuningStringMatchRewardScorerJsonMatchExpression(

  /** Json key name to find the value to match against. */
  val keyName: String? = null,

  /** String match expression to match against the value of json key. */
  val valueStringMatchExpression: ReinforcementTuningStringMatchRewardScorerStringMatchExpression? =
    null,
)
