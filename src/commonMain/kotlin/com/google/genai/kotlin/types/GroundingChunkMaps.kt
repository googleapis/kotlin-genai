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
 * A `Maps` chunk is a piece of evidence that comes from Google Maps.
 *
 * It contains information about a place, such as its name, address, and reviews. This is used to
 * provide the user with rich, location-based information.
 */
@Serializable
data class GroundingChunkMaps(

  /**
   * The sources that were used to generate the place answer.
   *
   * This includes review snippets and photos that were used to generate the answer, as well as URIs
   * to flag content.
   */
  val placeAnswerSources: GroundingChunkMapsPlaceAnswerSources? = null,

  /**
   * This Place's resource name, in `places/{place_id}` format.
   *
   * This can be used to look up the place in the Google Maps API.
   */
  val placeId: String? = null,

  /** The text of the place answer. */
  val text: String? = null,

  /** The title of the place. */
  val title: String? = null,

  /** The URI of the place. */
  val uri: String? = null,

  /** Output only. Route information. */
  val route: GroundingChunkMapsRoute? = null,
)
