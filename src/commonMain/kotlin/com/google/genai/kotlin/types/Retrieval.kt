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
 * Defines a retrieval tool that model can call to access external knowledge. This data type is not
 * supported in Gemini API.
 */
@Serializable
data class Retrieval(

  /** Optional. Deprecated. This option is no longer supported. */
  val disableAttribution: Boolean? = null,

  /** Use data source powered by external API for grounding. */
  val externalApi: ExternalApi? = null,

  /** Set to use data source powered by Vertex AI Search. */
  val vertexAiSearch: VertexAISearch? = null,

  /**
   * Set to use data source powered by Vertex RAG store. User data is uploaded via the
   * VertexRagDataService.
   */
  val vertexRagStore: VertexRagStore? = null,
)
