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

/** Encapsulates a review snippet. */
@Serializable
data class GroundingChunkMapsPlaceAnswerSourcesReviewSnippet(

  /** This review's author. */
  val authorAttribution: GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution? = null,

  /** A link where users can flag a problem with the review. */
  val flagContentUri: String? = null,

  /** A link to show the review on Google Maps. */
  val googleMapsUri: String? = null,

  /**
   * A string of formatted recent time, expressing the review time relative to the current time in a
   * form appropriate for the language and country.
   */
  val relativePublishTimeDescription: String? = null,

  /**
   * A reference representing this place review which may be used to look up this place review
   * again.
   */
  val review: String? = null,

  /** Id of the review referencing the place. */
  val reviewId: String? = null,

  /** Title of the review. */
  val title: String? = null,
)
