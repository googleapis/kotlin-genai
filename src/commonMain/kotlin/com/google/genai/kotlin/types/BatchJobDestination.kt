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

/** Config for `des` parameter. */
@Serializable
data class BatchJobDestination(

  /** Storage format of the output files. Must be one of: 'jsonl', 'bigquery', 'vertex-dataset'. */
  val format: String? = null,

  /** The Google Cloud Storage URI to the output file. */
  val gcsUri: String? = null,

  /** The BigQuery URI to the output table. */
  val bigqueryUri: String? = null,

  /**
   * The Gemini Developer API's file resource name of the output data (e.g. "files/12345"). The file
   * will be a JSONL file with a single response per line. The responses will be
   * GenerateContentResponse messages formatted as JSON. The responses will be written in the same
   * order as the input requests.
   */
  val fileName: String? = null,

  /**
   * The responses to the requests in the batch. Returned when the batch was built using inlined
   * requests. The responses will be in the same order as the input requests.
   */
  val inlinedResponses: List<InlinedResponse>? = null,

  /**
   * The responses to the requests in the batch. Returned when the batch was built using inlined
   * requests. The responses will be in the same order as the input requests.
   */
  val inlinedEmbedContentResponses: List<InlinedEmbedContentResponse>? = null,

  /**
   * This field is experimental and may change in future versions. The Vertex AI dataset
   * destination.
   */
  val vertexDataset: VertexMultimodalDatasetDestination? = null,
)
