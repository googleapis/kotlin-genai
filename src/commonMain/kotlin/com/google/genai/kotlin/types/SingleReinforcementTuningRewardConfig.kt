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

/** Single reinforcement tuning reward config. */
@Serializable
data class SingleReinforcementTuningRewardConfig(

  /** Scores parsed responses for autorater use cases by using a model to compute the reward. */
  val autoraterScorer: ReinforcementTuningAutoraterScorer? = null,

  /** A unique reward name for identifying each single reinforcement tuning reward. */
  val rewardName: String? = null,

  /**
   * Defines how to parse sample response. For example, given a sample response for evaluating the
   * reward, users might want to extract the text only between `` and `` in the sample response, and
   * keeps only the last one in case there are multiple such tags. To achieve such a purpose, they
   * can define a regex `".*(.*?)"` using the
   * ReinforcementTuningParseResponseConfig.ResponseParseType.REGEX_EXTRACT parse type.
   */
  val parseResponseConfig: ReinforcementTuningParseResponseConfig? = null,

  /**
   * ReinforcementTuningCodeExecutionRewardScorer is used to score parsed responses for code
   * execution use cases.
   */
  val codeExecutionRewardScorer: ReinforcementTuningCodeExecutionRewardScorer? = null,

  /**
   * ReinforcementTuningStringMatchRewardScorer is used to score parsed responses for simple string
   * matching use cases against reference answers.
   */
  val stringMatchRewardScorer: ReinforcementTuningStringMatchRewardScorer? = null,

  /**
   * ReinforcementTuningCloudRunRewardScorer is used to score parsed responses by calling a Cloud
   * Run service.
   */
  val cloudRunRewardScorer: ReinforcementTuningCloudRunRewardScorer? = null,
)
