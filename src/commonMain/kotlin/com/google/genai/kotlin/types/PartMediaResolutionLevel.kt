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

/** The tokenization quality used for given media. */
@Serializable
@JvmInline
value class PartMediaResolutionLevel(val value: String) {
  companion object {

    /** Media resolution has not been set. */
    val MEDIA_RESOLUTION_UNSPECIFIED = PartMediaResolutionLevel("MEDIA_RESOLUTION_UNSPECIFIED")

    /** Media resolution set to low. */
    val MEDIA_RESOLUTION_LOW = PartMediaResolutionLevel("MEDIA_RESOLUTION_LOW")

    /** Media resolution set to medium. */
    val MEDIA_RESOLUTION_MEDIUM = PartMediaResolutionLevel("MEDIA_RESOLUTION_MEDIUM")

    /** Media resolution set to high. */
    val MEDIA_RESOLUTION_HIGH = PartMediaResolutionLevel("MEDIA_RESOLUTION_HIGH")

    /** Media resolution set to ultra high. */
    val MEDIA_RESOLUTION_ULTRA_HIGH = PartMediaResolutionLevel("MEDIA_RESOLUTION_ULTRA_HIGH")
  }
}
