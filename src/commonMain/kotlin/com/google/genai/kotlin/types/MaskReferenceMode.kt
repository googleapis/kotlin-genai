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

/** Enum representing the mask mode of a mask reference image. */
@Serializable
@JvmInline
value class MaskReferenceMode(val value: String) {
  companion object {

    /**  */
    val MASK_MODE_DEFAULT = MaskReferenceMode("MASK_MODE_DEFAULT")

    /**  */
    val MASK_MODE_USER_PROVIDED = MaskReferenceMode("MASK_MODE_USER_PROVIDED")

    /**  */
    val MASK_MODE_BACKGROUND = MaskReferenceMode("MASK_MODE_BACKGROUND")

    /**  */
    val MASK_MODE_FOREGROUND = MaskReferenceMode("MASK_MODE_FOREGROUND")

    /**  */
    val MASK_MODE_SEMANTIC = MaskReferenceMode("MASK_MODE_SEMANTIC")
  }
}
