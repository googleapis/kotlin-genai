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
 * Defines the types of Google Maps grounding that can be enabled and their configurations. This
 * data type is not supported in Gemini API.
 */
@Serializable
data class GoogleMapsGroundingTypes(

  /**
   * Optional. Enables grounding with Google Maps Places. This is the default grounding type when no
   * `GroundingTypes` are specified.
   */
  val places: GoogleMapsPlaces? = null,

  /**
   * Optional. Enables grounding with Google Maps Routing APIs (ComputeRoutes and SearchAlongRoute).
   */
  val routing: GoogleMapsRouting? = null,
)
