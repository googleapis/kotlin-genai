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
 * User input that is sent in real time.
 *
 * This is different from `LiveClientContent` in a few ways:
 *
 * - Can be sent continuously without interruption to model generation.
 * - If there is a need to mix data interleaved across the `LiveClientContent` and the
 *   `LiveClientRealtimeInput`, server attempts to optimize for best response, but there are no
 *   guarantees.
 * - End of turn is not explicitly specified, but is rather derived from user activity (for example,
 *   end of speech).
 * - Even before the end of turn, the data is processed incrementally to optimize for a fast start
 *   of the response from the model.
 * - Is always assumed to be the user's input (cannot be used to populate conversation history).
 */
@Serializable
data class LiveClientRealtimeInput(

  /** Inlined bytes data for media input. */
  val mediaChunks: List<Blob>? = null,

  /** Marks the start of user activity. */
  val activityStart: ActivityStart? = null,

  /** Marks the end of user activity. */
  val activityEnd: ActivityEnd? = null,
)
