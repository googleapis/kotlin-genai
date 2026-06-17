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

import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/** Type of auth scheme. This enum is not supported in Gemini API. */
@Serializable
@JvmInline
value class AuthType(val value: String) {
  companion object {

    /**  */
    val AUTH_TYPE_UNSPECIFIED = AuthType("AUTH_TYPE_UNSPECIFIED")

    /** No Auth. */
    val NO_AUTH = AuthType("NO_AUTH")

    /** API Key Auth. */
    val API_KEY_AUTH = AuthType("API_KEY_AUTH")

    /** HTTP Basic Auth. */
    val HTTP_BASIC_AUTH = AuthType("HTTP_BASIC_AUTH")

    /** Google Service Account Auth. */
    val GOOGLE_SERVICE_ACCOUNT_AUTH = AuthType("GOOGLE_SERVICE_ACCOUNT_AUTH")

    /** OAuth auth. */
    val OAUTH = AuthType("OAUTH")

    /** OpenID Connect (OIDC) Auth. */
    val OIDC_AUTH = AuthType("OIDC_AUTH")
  }
}
