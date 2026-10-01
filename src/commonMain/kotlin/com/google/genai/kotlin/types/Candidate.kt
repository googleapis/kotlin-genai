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

/** A response candidate generated from the model. */
@Serializable
data class Candidate(

  /** Contains the multi-part content of the response. */
  val content: Content? = null,

  /** Source attribution of the generated content. */
  val citationMetadata: CitationMetadata? = null,

  /** Describes the reason the model stopped generating tokens. */
  val finishMessage: String? = null,

  /** Number of tokens for this candidate. */
  val tokenCount: Int? = null,

  /**
   * The reason why the model stopped generating tokens. If empty, the model has not stopped
   * generating the tokens.
   */
  val finishReason: FinishReason? = null,

  /**
   * Output only. Metadata returned when grounding is enabled. It contains the sources used to
   * ground the generated content.
   */
  val groundingMetadata: GroundingMetadata? = null,

  /**
   * Output only. The average log probability of the tokens in this candidate. This is a
   * length-normalized score that can be used to compare the quality of candidates of different
   * lengths. A higher average log probability suggests a more confident and coherent response.
   */
  val avgLogprobs: Double? = null,

  /**
   * Output only. The 0-based index of this candidate in the list of generated responses. This is
   * useful for distinguishing between multiple candidates when `candidate_count` > 1.
   */
  val index: Int? = null,

  /**
   * Output only. The detailed log probability information for the tokens in this candidate. This is
   * useful for debugging, understanding model uncertainty, and identifying potential
   * "hallucinations".
   */
  val logprobsResult: LogprobsResult? = null,

  /**
   * Output only. A list of ratings for the safety of a response candidate. There is at most one
   * rating per category.
   */
  val safetyRatings: List<SafetyRating>? = null,

  /**
   * Output only. Metadata returned when the model uses the `url_context` tool to get information
   * from a user-provided URL.
   */
  val urlContextMetadata: UrlContextMetadata? = null,
)
