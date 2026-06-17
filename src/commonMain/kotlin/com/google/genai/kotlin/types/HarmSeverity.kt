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
 * Output only. The severity of harm for this category. This enum is not supported in Gemini API.
 */
@Serializable
@JvmInline
value class HarmSeverity(val value: String) {
  companion object {

    /** The harm severity is unspecified. */
    val HARM_SEVERITY_UNSPECIFIED = HarmSeverity("HARM_SEVERITY_UNSPECIFIED")

    /** The harm severity is negligible. */
    val HARM_SEVERITY_NEGLIGIBLE = HarmSeverity("HARM_SEVERITY_NEGLIGIBLE")

    /** The harm severity is low. */
    val HARM_SEVERITY_LOW = HarmSeverity("HARM_SEVERITY_LOW")

    /** The harm severity is medium. */
    val HARM_SEVERITY_MEDIUM = HarmSeverity("HARM_SEVERITY_MEDIUM")

    /** The harm severity is high. */
    val HARM_SEVERITY_HIGH = HarmSeverity("HARM_SEVERITY_HIGH")
  }
}
