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
 * A piece of evidence that supports a claim made by the model.
 *
 * This is used to show a citation for a claim made by the model. When grounding is enabled, the
 * model returns a `GroundingChunk` that contains a reference to the source of the information.
 */
@Serializable
data class GroundingChunk(

  /** A grounding chunk from an image search result. See the `Image` message for details. */
  val image: GroundingChunkImage? = null,

  /**
   * A `Maps` chunk is a piece of evidence that comes from Google Maps.
   *
   * It contains information about a place, such as its name, address, and reviews. This is used to
   * provide the user with rich, location-based information.
   */
  val maps: GroundingChunkMaps? = null,

  /**
   * A grounding chunk from a data source retrieved by a retrieval tool, such as Vertex AI Search.
   * See the `RetrievedContext` message for details
   */
  val retrievedContext: GroundingChunkRetrievedContext? = null,

  /**
   * A grounding chunk from a web page, typically from Google Search. See the `Web` message for
   * details.
   */
  val web: GroundingChunkWeb? = null,
)
