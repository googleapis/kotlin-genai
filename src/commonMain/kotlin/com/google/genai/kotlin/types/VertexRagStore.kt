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

/** Retrieve from Vertex RAG Store for grounding. This data type is not supported in Gemini API. */
@Serializable
data class VertexRagStore(

  /** Optional. Deprecated. Please use rag_resources instead. */
  val ragCorpora: List<String>? = null,

  /**
   * Optional. The representation of the rag source. It can be used to specify corpus only or
   * ragfiles. Currently only support one corpus or multiple files from one corpus. In the future we
   * may open up multiple corpora support.
   */
  val ragResources: List<VertexRagStoreRagResource>? = null,

  /** Optional. The retrieval config for the Rag query. */
  val ragRetrievalConfig: RagRetrievalConfig? = null,

  /** Optional. Number of top k results to return from the selected corpora. */
  val similarityTopK: Int? = null,

  /**
   * Optional. Currently only supported for Gemini Multimodal Live API. In Gemini Multimodal Live
   * API, if `store_context` bool is specified, Gemini will leverage it to automatically memorize
   * the interactions between the client and Gemini, and retrieve context when needed to augment the
   * response generation for users' ongoing and future interactions.
   */
  val storeContext: Boolean? = null,

  /** Optional. Only return results with vector distance smaller than the threshold. */
  val vectorDistanceThreshold: Double? = null,
)
