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
 * Retrieve from data source powered by external API for grounding. The external API is not owned by
 * Google, but need to follow the pre-defined API spec. This data type is not supported in Gemini
 * API.
 */
@Serializable
data class ExternalApi(

  /** The authentication config to access the API. Deprecated. Please use auth_config instead. */
  val apiAuth: ApiAuth? = null,

  /** The API spec that the external API implements. */
  val apiSpec: ApiSpec? = null,

  /** The authentication config to access the API. */
  val authConfig: AuthConfig? = null,

  /** Parameters for the elastic search API. */
  val elasticSearchParams: ExternalApiElasticSearchParams? = null,

  /**
   * The endpoint of the external API. The system will call the API at this endpoint to retrieve the
   * data for grounding. Example: https://acme.com:443/search
   */
  val endpoint: String? = null,

  /** Parameters for the simple search API. */
  val simpleSearchParams: ExternalApiSimpleSearchParams? = null,
)
