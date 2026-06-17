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
 * Define data stores within engine to filter on in a search call and configurations for those data
 * stores. For more information, see
 * https://cloud.google.com/generative-ai-app-builder/docs/reference/rpc/google.cloud.discoveryengine.v1#datastorespec.
 * This data type is not supported in Gemini API.
 */
@Serializable
data class VertexAISearchDataStoreSpec(

  /**
   * Full resource name of DataStore, such as Format:
   * `projects/{project}/locations/{location}/collections/{collection}/dataStores/{dataStore}`
   */
  val dataStore: String? = null,

  /**
   * Optional. Filter specification to filter documents in the data store specified by data_store
   * field. For more information on filtering, see
   * [Filtering](https://cloud.google.com/generative-ai-app-builder/docs/filter-search-metadata)
   */
  val filter: String? = null,
)
