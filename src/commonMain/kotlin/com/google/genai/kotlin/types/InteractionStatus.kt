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

/** The different activity states of the live session. */
@Serializable
@JvmInline
value class InteractionStatus(val value: String) {
  companion object {

    /** Unspecified interaction status. */
    val INTERACTION_STATUS_UNSPECIFIED = InteractionStatus("INTERACTION_STATUS_UNSPECIFIED")

    /**
     * The server is still actively processing user input or running background reasoning. More
     * model output may follow.
     */
    val IN_PROGRESS = InteractionStatus("IN_PROGRESS")

    /** Deprecated: Use IDLE instead. */
    val REQUIRES_ACTION = InteractionStatus("REQUIRES_ACTION")

    /** The server has completed all processing and background reasoning. */
    val IDLE = InteractionStatus("IDLE")
  }
}
