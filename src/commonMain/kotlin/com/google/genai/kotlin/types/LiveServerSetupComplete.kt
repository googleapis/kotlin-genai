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

/** Sent in response to a `LiveGenerateContentSetup` message from the client. */
@Serializable
data class LiveServerSetupComplete(

  /** The session id of the live session. */
  val sessionId: String? = null,

  /**
   * Signature of the verified consent audio. This is populated when the request has a
   * ReplicatedVoiceConfig with consent_audio set, if the consent verification was successful. This
   * may be used in a subsequent request instead of the consent_audio to verify the same consent.
   */
  val voiceConsentSignature: VoiceConsentSignature? = null,
)
