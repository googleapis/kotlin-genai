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

/** Options about which input is included in the user's turn. */
@Serializable
@JvmInline
value class TurnCoverage(val value: String) {
  companion object {

    /** If unspecified, the default behavior is `TURN_INCLUDES_ONLY_ACTIVITY`. */
    val TURN_COVERAGE_UNSPECIFIED = TurnCoverage("TURN_COVERAGE_UNSPECIFIED")

    /**
     * The users turn only includes activity since the last turn, excluding inactivity (e.g. silence
     * on the audio stream). This is the default behavior.
     */
    val TURN_INCLUDES_ONLY_ACTIVITY = TurnCoverage("TURN_INCLUDES_ONLY_ACTIVITY")

    /**
     * The users turn includes all realtime input since the last turn, including inactivity (e.g.
     * silence on the audio stream).
     */
    val TURN_INCLUDES_ALL_INPUT = TurnCoverage("TURN_INCLUDES_ALL_INPUT")

    /**
     * Includes audio activity and all video since the last turn. With automatic activity detection,
     * audio activity means speech and excludes silence.
     */
    val TURN_INCLUDES_AUDIO_ACTIVITY_AND_ALL_VIDEO =
      TurnCoverage("TURN_INCLUDES_AUDIO_ACTIVITY_AND_ALL_VIDEO")
  }
}
