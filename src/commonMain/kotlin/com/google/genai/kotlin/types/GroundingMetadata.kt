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

/** Information for various kinds of grounding. */
@Serializable
data class GroundingMetadata(

  /**
   * A list of supporting references retrieved from the grounding source. This field is populated
   * when the grounding source is Google Search, Vertex AI Search, or Google Maps.
   */
  val groundingChunks: List<GroundingChunk>? = null,

  /** List of grounding support. */
  val groundingSupports: List<GroundingSupport>? = null,

  /** Metadata related to retrieval in the grounding flow. */
  val retrievalMetadata: RetrievalMetadata? = null,

  /** Optional. Google search entry for the following-up web searches. */
  val searchEntryPoint: SearchEntryPoint? = null,

  /** Web search queries for the following-up web search. */
  val webSearchQueries: List<String>? = null,

  /**
   * Optional. Output only. Deprecated: The Google Maps contextual widget behavior in Grounding with
   * Google Maps is being deprecated; this field is planned for removal and will no longer be
   * populated once removed. A token that can be used to render a Google Maps widget with the
   * contextual data. This field is populated only when the grounding source is Google Maps.
   */
  val googleMapsWidgetContextToken: String? = null,

  /**
   * Optional. The image search queries that were used to generate the content. This field is
   * populated only when the grounding source is Google Search with the Image Search search_type
   * enabled.
   */
  val imageSearchQueries: List<String>? = null,

  /**
   * Optional. The queries that were executed by the retrieval tools. This field is populated only
   * when the grounding source is a retrieval tool, such as Vertex AI Search. This field is not
   * supported in Gemini API.
   */
  val retrievalQueries: List<String>? = null,

  /**
   * Optional. Output only. A list of URIs that can be used to flag a place or review for
   * inappropriate content. This field is populated only when the grounding source is Google Maps.
   * This field is not supported in Gemini API.
   */
  val sourceFlaggingUris: List<GroundingMetadataSourceFlaggingUri>? = null,
)
