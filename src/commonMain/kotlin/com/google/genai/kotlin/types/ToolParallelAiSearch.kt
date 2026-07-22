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
import kotlinx.serialization.json.JsonElement

/**
 * ParallelAiSearch tool type. A tool that uses the Parallel.ai search engine for grounding. This
 * data type is not supported in Gemini API.
 */
@Serializable
data class ToolParallelAiSearch(

  /**
   * Optional. The API key for ParallelAiSearch. If an API key is not provided, the system will
   * attempt to verify access by checking for an active Parallel.ai subscription through the Google
   * Cloud Marketplace. See https://docs.parallel.ai/search/search-quickstart for more details.
   */
  val apiKey: String? = null,

  /**
   * Optional. Custom configs for ParallelAiSearch. This field can be used to pass any parameter
   * from the Parallel.ai Search API. See the Parallel.ai documentation for the full list of
   * available parameters and their usage: https://docs.parallel.ai/api-reference/search-beta/search
   * Currently only `source_policy`, `excerpts`, `max_results`, `mode`, `fetch_policy` can be set
   * via this field. For example: { "source_policy": { "include_domains":
   * ["google.com", "wikipedia.org"], "exclude_domains": ["example.com"] }, "fetch_policy": {
   * "max_age_seconds": 3600 } }
   */
  val customConfigs: Map<String, JsonElement>? = null,

  /**
   * Optional. Deprecated: Use `enable_zero_data_retention` instead. Instructs Vertex Grounding to
   * use Parallel's Zero Data Retention Marketplace product. If this value is "false" or omitted,
   * the Parallel Web Search for Grounding standard subscription will be used. If this value is
   * "true", the Parallel Web Search for Grounding - ZDR subscription will be used.
   */
  val enableDataRetention: Boolean? = null,

  /**
   * Optional. Instructs Vertex Grounding to use Parallel's Zero Data Retention Marketplace product.
   * If this value is "false" or omitted, the Parallel Web Search for Grounding standard
   * subscription will be used. If this value is "true", the Parallel Web Search for Grounding - ZDR
   * subscription will be used.
   */
  val enableZeroDataRetention: Boolean? = null,
)
