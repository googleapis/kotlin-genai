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

/** Enum that controls the generation of people. */
@Serializable
@JvmInline
value class PersonGeneration(val value: String) {
  companion object {

    /** Block generation of images of people. */
    val DONT_ALLOW = PersonGeneration("DONT_ALLOW")

    /** Generate images of adults, but not children. */
    val ALLOW_ADULT = PersonGeneration("ALLOW_ADULT")

    /** Generate images that include adults and children. */
    val ALLOW_ALL = PersonGeneration("ALLOW_ALL")
  }
}
