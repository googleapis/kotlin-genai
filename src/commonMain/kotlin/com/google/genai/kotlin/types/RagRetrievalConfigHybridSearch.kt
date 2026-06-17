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

/** Config for Hybrid Search. This data type is not supported in Gemini API. */
@Serializable
data class RagRetrievalConfigHybridSearch(

  /**
   * Optional. Alpha value controls the weight between dense and sparse vector search results. The
   * range is [0, 1], while 0 means sparse vector search only and 1 means dense vector search only.
   * The default value is 0.5 which balances sparse and dense vector search equally.
   */
  val alpha: Double? = null
)
