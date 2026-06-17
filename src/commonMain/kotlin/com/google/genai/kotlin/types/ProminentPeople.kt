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

/**
 * Controls whether prominent people (celebrities) generation is allowed. If used with
 * personGeneration, personGeneration enum would take precedence. For instance, if ALLOW_NONE is
 * set, all person generation would be blocked. If this field is unspecified, the default behavior
 * is to allow prominent people. This enum is not supported in Gemini API.
 */
@Serializable
@JvmInline
value class ProminentPeople(val value: String) {
  companion object {

    /**
     * Unspecified value. The model will proceed with the default behavior, which is to allow
     * generation of prominent people.
     */
    val PROMINENT_PEOPLE_UNSPECIFIED = ProminentPeople("PROMINENT_PEOPLE_UNSPECIFIED")

    /** Allows the model to generate images of prominent people. */
    val ALLOW_PROMINENT_PEOPLE = ProminentPeople("ALLOW_PROMINENT_PEOPLE")

    /** Prevents the model from generating images of prominent people. */
    val BLOCK_PROMINENT_PEOPLE = ProminentPeople("BLOCK_PROMINENT_PEOPLE")
  }
}
