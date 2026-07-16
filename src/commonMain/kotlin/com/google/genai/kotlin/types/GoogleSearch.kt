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

/** GoogleSearch tool type. Tool to support Google Search in Model. Powered by Google. */
@Serializable
data class GoogleSearch(

  /**
   * Optional. Sites with confidence level chosen & above this value will be blocked from the search
   * results. This field is not supported in Gemini API.
   */
  val blockingConfidence: PhishBlockThreshold? = null,

  /**
   * Optional. List of domains to be excluded from the search results. The default limit is 2000
   * domains. Example: ["amazon.com", "facebook.com"]. This field is not supported in Gemini API.
   */
  val excludeDomains: List<String>? = null,

  /** Optional. The set of search types to enable. If not set, web search is enabled by default. */
  val searchTypes: SearchTypes? = null,

  /**
   * Optional. Filter search results to a specific time range. If customers set a start time, they
   * must set an end time (and vice versa). This field is not supported in Vertex AI.
   */
  val timeRangeFilter: Interval? = null,
)
