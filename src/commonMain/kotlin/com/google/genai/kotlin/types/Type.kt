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

/** Data type of the schema field. */
@Serializable
@JvmInline
value class Type(val value: String) {
  companion object {

    /** Not specified, should not be used. */
    val TYPE_UNSPECIFIED = Type("TYPE_UNSPECIFIED")

    /** OpenAPI string type */
    val STRING = Type("STRING")

    /** OpenAPI number type */
    val NUMBER = Type("NUMBER")

    /** OpenAPI integer type */
    val INTEGER = Type("INTEGER")

    /** OpenAPI boolean type */
    val BOOLEAN = Type("BOOLEAN")

    /** OpenAPI array type */
    val ARRAY = Type("ARRAY")

    /** OpenAPI object type */
    val OBJECT = Type("OBJECT")

    /** Null type */
    val NULL = Type("NULL")
  }
}
