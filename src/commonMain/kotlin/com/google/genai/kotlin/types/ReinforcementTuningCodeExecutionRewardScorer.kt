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
 * ReinforcementTuningCodeExecutionRewardScorer allows users to implement a function to evaluate
 * rewards for the sample response. The function signature is as follows: ``` def evaluate(example:
 * dict[str, Any], response: dict[str, Any]) -> float: ... ``` `example` is a
 * ReinforcementTuningExample in ProtoJSON format, (i.e., the format is the same as as one line in
 * the training/validation dataset except that the keys must be in camel case). System instructions
 * (i.e., `example.get("systemInstruction")`) and references (i.e., `example.get("references")`) are
 * also included in the `example` provided that they are set in the training/validation dataset.
 * `response` is a Content in ProtoJSON format (i.e., keys must be in camel case), which is the same
 * as the Online Prediction response for Gemini models. Note: Reward output by the `evaluate`
 * function is clipped to be within `[-1, 1]`, i.e., `reward = max(min(reward, 1.0), -1.0)`. This
 * data type is not supported in Gemini API.
 */
@Serializable
data class ReinforcementTuningCodeExecutionRewardScorer(

  /**
   * The python code snippet as a string for evaluating rewards. The following is an example python
   * code snippet that returns a reward `1.0` for a parsed response matching the user-provided
   * reference answer in per prompt references map. ``` def evaluate(example, response) -> float:
   * response_str = response.get("parts", [])0 references = example.get("references", {}) if
   * response_str == references.get("concise_answer"): return 1.0 return -1.0 ``` Note: Reward
   * output by the evaluate function is clipped to be within `[-1, 1]`, i.e., `reward =
   * max(min(reward, 1.0), -1.0)`.
   */
  val pythonCodeSnippet: String? = null
)
