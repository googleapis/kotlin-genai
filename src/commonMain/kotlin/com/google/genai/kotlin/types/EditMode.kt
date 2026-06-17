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

/** Enum representing the editing mode. */
@Serializable
@JvmInline
value class EditMode(val value: String) {
  companion object {

    /**  */
    val EDIT_MODE_DEFAULT = EditMode("EDIT_MODE_DEFAULT")

    /**  */
    val EDIT_MODE_INPAINT_REMOVAL = EditMode("EDIT_MODE_INPAINT_REMOVAL")

    /**  */
    val EDIT_MODE_INPAINT_INSERTION = EditMode("EDIT_MODE_INPAINT_INSERTION")

    /**  */
    val EDIT_MODE_OUTPAINT = EditMode("EDIT_MODE_OUTPAINT")

    /**  */
    val EDIT_MODE_CONTROLLED_EDITING = EditMode("EDIT_MODE_CONTROLLED_EDITING")

    /**  */
    val EDIT_MODE_STYLE = EditMode("EDIT_MODE_STYLE")

    /**  */
    val EDIT_MODE_BGSWAP = EditMode("EDIT_MODE_BGSWAP")

    /**  */
    val EDIT_MODE_PRODUCT_IMAGE = EditMode("EDIT_MODE_PRODUCT_IMAGE")
  }
}
