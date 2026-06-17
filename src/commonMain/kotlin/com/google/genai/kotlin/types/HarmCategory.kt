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

/** The harm category to be blocked. */
@Serializable
@JvmInline
value class HarmCategory(val value: String) {
  companion object {

    /** Default value. This value is unused. */
    val HARM_CATEGORY_UNSPECIFIED = HarmCategory("HARM_CATEGORY_UNSPECIFIED")

    /** Abusive, threatening, or content intended to bully, torment, or ridicule. */
    val HARM_CATEGORY_HARASSMENT = HarmCategory("HARM_CATEGORY_HARASSMENT")

    /**
     * Content that promotes violence or incites hatred against individuals or groups based on
     * certain attributes.
     */
    val HARM_CATEGORY_HATE_SPEECH = HarmCategory("HARM_CATEGORY_HATE_SPEECH")

    /** Content that contains sexually explicit material. */
    val HARM_CATEGORY_SEXUALLY_EXPLICIT = HarmCategory("HARM_CATEGORY_SEXUALLY_EXPLICIT")

    /** Content that promotes, facilitates, or enables dangerous activities. */
    val HARM_CATEGORY_DANGEROUS_CONTENT = HarmCategory("HARM_CATEGORY_DANGEROUS_CONTENT")

    /**
     * Deprecated: Election filter is not longer supported. The harm category is civic integrity.
     */
    val HARM_CATEGORY_CIVIC_INTEGRITY = HarmCategory("HARM_CATEGORY_CIVIC_INTEGRITY")

    /** Images that contain hate speech. This enum value is not supported in Gemini API. */
    val HARM_CATEGORY_IMAGE_HATE = HarmCategory("HARM_CATEGORY_IMAGE_HATE")

    /** Images that contain dangerous content. This enum value is not supported in Gemini API. */
    val HARM_CATEGORY_IMAGE_DANGEROUS_CONTENT =
      HarmCategory("HARM_CATEGORY_IMAGE_DANGEROUS_CONTENT")

    /** Images that contain harassment. This enum value is not supported in Gemini API. */
    val HARM_CATEGORY_IMAGE_HARASSMENT = HarmCategory("HARM_CATEGORY_IMAGE_HARASSMENT")

    /**
     * Images that contain sexually explicit content. This enum value is not supported in Gemini
     * API.
     */
    val HARM_CATEGORY_IMAGE_SEXUALLY_EXPLICIT =
      HarmCategory("HARM_CATEGORY_IMAGE_SEXUALLY_EXPLICIT")

    /**
     * Prompts designed to bypass safety filters. This enum value is not supported in Gemini API.
     */
    val HARM_CATEGORY_JAILBREAK = HarmCategory("HARM_CATEGORY_JAILBREAK")
  }
}
