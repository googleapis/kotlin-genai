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
 * Evaluates parsed response using match type against the expression. Returns `true` if
 * `MatchOperation(target, expression)` evaluates to `true`, and `false` otherwise. This data type
 * is not supported in Gemini API.
 */
@Serializable
data class ReinforcementTuningStringMatchRewardScorerStringMatchExpression(

  /**
   * A string or a regular expression to match against for evaluating rewards. Users can also
   * provide a references map of `{key: value}` whose `value` will be used to replace the
   * placeholder {{references.key}} in the expression. For example, if the following `references`
   * are defined in the training / validation dataset: ``` { "systemInstruction": ..., "contents":
   * ..., "references": { "concise_answer": "Yes", "verbose_answer": "The answer is Yes" } } ``` and
   * if users define the following StringMatchExpression: { "matchOperation": "REGEX_CONTAINS",
   * "expression": ".*{{references.concise_answer}}.*" } On evaluating the reward for each sample
   * response, this StringMatchExpression will be substituted as: ``` { "matchOperation":
   * "REGEX_CONTAINS", "expression": ".*Yes.*" } ```
   */
  val expression: String? = null,

  /** Match operation to use for evaluating rewards. */
  val matchOperation: MatchOperation? = null,
)
