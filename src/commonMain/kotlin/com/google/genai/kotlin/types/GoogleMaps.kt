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

/** Tool to retrieve knowledge from Google Maps. */
@Serializable
data class GoogleMaps(

  /**
   * The authentication config to access the API. Only API key is supported. This field is not
   * supported in Gemini API.
   */
  val authConfig: AuthConfig? = null,

  /**
   * Deprecated. The Google Maps contextual widget behavior in Grounding with Google Maps is being
   * deprecated; this field is planned for removal and no longer has any effect once removed.
   * Optional. Whether to return a widget context token in the GroundingMetadata of the response.
   */
  val enableWidget: Boolean? = null,
)
