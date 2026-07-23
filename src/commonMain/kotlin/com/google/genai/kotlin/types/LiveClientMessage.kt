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

/** Messages sent by the client in the API call. */
@Serializable
data class LiveClientMessage(

  /**
   * Message to be sent by the system when connecting to the API. SDK users should not send this
   * message.
   */
  val setup: LiveClientSetup? = null,

  /** Incremental update of the current conversation delivered from the client. */
  val clientContent: LiveClientContent? = null,

  /** User input that is sent in real time. */
  val realtimeInput: LiveClientRealtimeInput? = null,

  /** Response to a `ToolCallMessage` received from the server. */
  val toolResponse: LiveClientToolResponse? = null,
)
