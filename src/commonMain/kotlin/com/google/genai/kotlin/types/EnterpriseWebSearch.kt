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
 * Tool to search public web data, powered by Vertex AI Search and Sec4 compliance. This data type
 * is not supported in Gemini API.
 */
@Serializable
data class EnterpriseWebSearch(

  /**
   * Optional. Sites with confidence level chosen & above this value will be blocked from the search
   * results.
   */
  val blockingConfidence: PhishBlockThreshold? = null,

  /**
   * Optional. List of domains to be excluded from the search results. The default limit is 2000
   * domains.
   */
  val excludeDomains: List<String>? = null,
)
