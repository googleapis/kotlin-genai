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
 * ExaAiSearch tool type. A tool that uses the Exa.ai search engine for grounding. This data type is
 * not supported in Gemini API.
 */
@Serializable
data class ToolExaAiSearch(

  /** Required. The API key for ExaAiSearch. */
  val apiKey: String? = null,

  /** Optional. This field can be used to pass any parameter from the Exa.ai Search API. */
  val customConfigs: Map<String, JsonElement>? = null,
)
