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

/** Defines what effect activity has. This enum is not supported in Vertex AI. */
@Serializable
@JvmInline
value class ActivityHandling(val value: String) {
  companion object {

    /** If unspecified, the default behavior is `START_OF_ACTIVITY_INTERRUPTS`. */
    val ACTIVITY_HANDLING_UNSPECIFIED = ActivityHandling("ACTIVITY_HANDLING_UNSPECIFIED")

    /**
     * If true, start of activity will interrupt the model's response (also called "barge in"). The
     * model's current response will be cut-off in the moment of the interruption. This is the
     * default behavior.
     */
    val START_OF_ACTIVITY_INTERRUPTS = ActivityHandling("START_OF_ACTIVITY_INTERRUPTS")

    /** The model's response will not be interrupted. */
    val NO_INTERRUPTION = ActivityHandling("NO_INTERRUPTION")
  }
}
