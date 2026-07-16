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

/** Config for `src` parameter. */
@Serializable
data class BatchJobSource(

  /** Storage format of the input files. Must be one of: 'jsonl', 'bigquery', 'vertex-dataset'. */
  val format: String? = null,

  /** The Google Cloud Storage URIs to input files. */
  val gcsUri: List<String>? = null,

  /** The BigQuery URI to input table. */
  val bigqueryUri: String? = null,

  /**
   * This field is experimental and may change in future versions. The Vertex AI dataset resource
   * name to use as input. Must be of type multimodal.
   */
  val vertexDatasetName: String? = null,

  /** The Gemini Developer API's file resource name of the input data (e.g. "files/12345"). */
  val fileName: String? = null,

  /** The Gemini Developer API's inlined input data to run batch job. */
  val inlinedRequests: List<InlinedRequest>? = null,
)
