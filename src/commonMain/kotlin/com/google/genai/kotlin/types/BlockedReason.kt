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

/** Output only. The reason why the prompt was blocked. */
@Serializable
@JvmInline
value class BlockedReason(val value: String) {
  companion object {

    /** The blocked reason is unspecified. */
    val BLOCKED_REASON_UNSPECIFIED = BlockedReason("BLOCKED_REASON_UNSPECIFIED")

    /** The prompt was blocked for safety reasons. */
    val SAFETY = BlockedReason("SAFETY")

    /**
     * The prompt was blocked for other reasons. For example, it may be due to the prompt's
     * language, or because it contains other harmful content.
     */
    val OTHER = BlockedReason("OTHER")

    /** The prompt was blocked because it contains a term from the terminology blocklist. */
    val BLOCKLIST = BlockedReason("BLOCKLIST")

    /** The prompt was blocked because it contains prohibited content. */
    val PROHIBITED_CONTENT = BlockedReason("PROHIBITED_CONTENT")

    /** The prompt was blocked because it contains content that is unsafe for image generation. */
    val IMAGE_SAFETY = BlockedReason("IMAGE_SAFETY")

    /** The prompt was blocked by Model Armor. This enum value is not supported in Gemini API. */
    val MODEL_ARMOR = BlockedReason("MODEL_ARMOR")

    /**
     * The prompt was blocked as a jailbreak attempt. This enum value is not supported in Gemini
     * API.
     */
    val JAILBREAK = BlockedReason("JAILBREAK")
  }
}
