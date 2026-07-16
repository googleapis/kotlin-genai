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
 * Defines how to parse sample response config for reinforcement tuning. The parsed response (i.e.,
 * substring) will be passed to the reward functions. For example, the input prompt might be: >
 * "Perform step-by-step thoughts first to problem A, finally output answer in the <ans> </ans>
 * block." The sample response from the model under tuning might look like: > "<ans>Yes</ans>" Here,
 * users can define the following parse config: ``` { "parseType": "REGEX_EXTRACT",
 * "regexExtractExpression": ".*(.*?)" } ``` The resulting parsed response would be `"Yes"` and will
 * be passed to the reward functions for evaluating rewards. This data type is not supported in
 * Gemini API.
 */
@Serializable
data class ReinforcementTuningParseResponseConfig(

  /** Defines the type for parsing sample response. */
  val parseType: ResponseParseType? = null,

  /**
   * Defines the regex for extracting the important part of sample response. This field is only used
   * when parse_type is ResponseParseType.REGEX_EXTRACT.
   */
  val regexExtractExpression: String? = null,
)
