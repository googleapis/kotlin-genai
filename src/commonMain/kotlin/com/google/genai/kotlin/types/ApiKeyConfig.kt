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

/** Config for authentication with API key. This data type is not supported in Gemini API. */
@Serializable
data class ApiKeyConfig(

  /**
   * Optional. The name of the SecretManager secret version resource storing the API key. Format:
   * `projects/{project}/secrets/{secrete}/versions/{version}` - If both `api_key_secret` and
   * `api_key_string` are specified, this field takes precedence over `api_key_string`. - If
   * specified, the `secretmanager.versions.access` permission should be granted to Vertex AI
   * Extension Service Agent
   * (https://cloud.google.com/vertex-ai/docs/general/access-control#service-agents) on the
   * specified resource.
   */
  val apiKeySecret: String? = null,

  /** Optional. The API key to be used in the request directly. */
  val apiKeyString: String? = null,

  /** Optional. The location of the API key. */
  val httpElementLocation: HttpElementLocation? = null,

  /**
   * Optional. The parameter name of the API key. E.g. If the API request is
   * "https://example.com/act?api_key=", "api_key" would be the parameter name.
   */
  val name: String? = null,
)
