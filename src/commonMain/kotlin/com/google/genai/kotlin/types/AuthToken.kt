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

/** Config for auth_tokens.create parameters. */
@Serializable
data class AuthToken(

  /** The name of the auth token. */
  val name: String? = null,

  /**
   * Optional. Input only. Immutable. An optional time after which, when using the resulting token,
   * messages in BidiGenerateContent sessions will be rejected. (Gemini may preemptively close the
   * session after this time.) If not set then this defaults to 30 minutes in the future. If set,
   * this value must be less than 20 hours in the future.
   */
  val expireTime: String? = null,

  /**
   * Optional. Input only. Immutable. The time after which new Live API sessions using the token
   * resulting from this request will be rejected. If not set this defaults to 60 seconds in the
   * future. If set, this value must be less than 20 hours in the future.
   */
  val newSessionExpireTime: String? = null,

  /**
   * Optional. Input only. Immutable. The number of times the token can be used. If this value is
   * zero then no limit is applied. Resuming a Live API session does not count as a use. If
   * unspecified, the default is 1.
   */
  val uses: Int? = null,
)
