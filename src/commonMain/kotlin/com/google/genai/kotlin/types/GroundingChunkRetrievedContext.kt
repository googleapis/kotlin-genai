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
 * Context retrieved from a data source to ground the model's response. This is used when a
 * retrieval tool fetches information from a user-provided corpus or a public dataset.
 */
@Serializable
data class GroundingChunkRetrievedContext(

  /**
   * Output only. The full resource name of the referenced Vertex AI Search document. This is used
   * to identify the specific document that was retrieved. The format is
   * `projects/{project}/locations/{location}/collections/{collection}/dataStores/{data_store}/branches/{branch}/documents/{document}`.
   * This field is not supported in Gemini API.
   */
  val documentName: String? = null,

  /**
   * Additional context for a Retrieval-Augmented Generation (RAG) retrieval result. This is
   * populated only when the RAG retrieval tool is used. This field is not supported in Gemini API.
   */
  val ragChunk: RagChunk? = null,

  /** The content of the retrieved data source. */
  val text: String? = null,

  /** The title of the retrieved data source. */
  val title: String? = null,

  /** The URI of the retrieved data source. */
  val uri: String? = null,

  /**
   * Optional. User-provided metadata about the retrieved context. This field is not supported in
   * Vertex AI.
   */
  val customMetadata: List<GroundingChunkCustomMetadata>? = null,

  /**
   * Optional. Name of the `FileSearchStore` containing the document. Example:
   * `fileSearchStores/123`. This field is not supported in Vertex AI.
   */
  val fileSearchStore: String? = null,

  /**
   * Optional. Page number of the retrieved context, if applicable. This field is not supported in
   * Vertex AI.
   */
  val pageNumber: Int? = null,

  /**
   * Optional. The media blob resource name for multimodal file search results. Format:
   * fileSearchStores/{file_search_store_id}/media/{blob_id}. This field is not supported in Vertex
   * AI.
   */
  val mediaId: String? = null,
)
