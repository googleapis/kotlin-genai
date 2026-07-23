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

/** Parameters for sending realtime input to the live API. */
@Serializable
data class LiveSendRealtimeInputParameters(

  /** Realtime input to send to the session. */
  val media: Blob? = null,

  /** The realtime audio input stream. */
  val audio: Blob? = null,

  /**
   * Indicates that the audio stream has ended, e.g. because the microphone was turned off.
   *
   * This should only be sent when automatic activity detection is enabled (which is the default).
   *
   * The client can reopen the stream by sending an audio message.
   */
  val audioStreamEnd: Boolean? = null,

  /** The realtime video input stream. */
  val video: Blob? = null,

  /** The realtime text input stream. */
  val text: String? = null,

  /** Marks the start of user activity. */
  val activityStart: ActivityStart? = null,

  /** Marks the end of user activity. */
  val activityEnd: ActivityEnd? = null,
)
