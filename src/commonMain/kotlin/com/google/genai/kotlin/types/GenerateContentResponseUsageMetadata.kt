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
 * Usage metadata about the content generation request and response. This message provides a
 * detailed breakdown of token usage and other relevant metrics. This data type is not supported in
 * Gemini API.
 */
@Serializable
data class GenerateContentResponseUsageMetadata(

  /**
   * Output only. A detailed breakdown of the token count for each modality in the cached content.
   */
  val cacheTokensDetails: List<ModalityTokenCount>? = null,

  /** Output only. The number of tokens in the cached content that was used for this request. */
  val cachedContentTokenCount: Int? = null,

  /** The total number of tokens in the generated candidates. */
  val candidatesTokenCount: Int? = null,

  /**
   * Output only. A detailed breakdown of the token count for each modality in the generated
   * candidates.
   */
  val candidatesTokensDetails: List<ModalityTokenCount>? = null,

  /**
   * The total number of tokens in the prompt. This includes any text, images, or other media
   * provided in the request. When `cached_content` is set, this also includes the number of tokens
   * in the cached content.
   */
  val promptTokenCount: Int? = null,

  /** Output only. A detailed breakdown of the token count for each modality in the prompt. */
  val promptTokensDetails: List<ModalityTokenCount>? = null,

  /**
   * Output only. The number of tokens that were part of the model's generated "thoughts" output, if
   * applicable.
   */
  val thoughtsTokenCount: Int? = null,

  /**
   * Output only. The number of tokens in the results from tool executions, which are provided back
   * to the model as input, if applicable.
   */
  val toolUsePromptTokenCount: Int? = null,

  /**
   * Output only. A detailed breakdown by modality of the token counts from the results of tool
   * executions, which are provided back to the model as input.
   */
  val toolUsePromptTokensDetails: List<ModalityTokenCount>? = null,

  /**
   * The total number of tokens for the entire request. This is the sum of `prompt_token_count`,
   * `candidates_token_count`, `tool_use_prompt_token_count`, and `thoughts_token_count`.
   */
  val totalTokenCount: Int? = null,

  /** Output only. The traffic type for this request. */
  val trafficType: TrafficType? = null,
)
