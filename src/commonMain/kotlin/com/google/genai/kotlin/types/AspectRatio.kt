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

/** The aspect ratio for the image output. */
@Serializable
@JvmInline
value class AspectRatio(val value: String) {
  companion object {

    /** Default value. This value is unused. */
    val ASPECT_RATIO_UNSPECIFIED = AspectRatio("ASPECT_RATIO_UNSPECIFIED")

    /** 1:1 aspect ratio. */
    val ASPECT_RATIO_ONE_BY_ONE = AspectRatio("ASPECT_RATIO_ONE_BY_ONE")

    /** 2:3 aspect ratio. */
    val ASPECT_RATIO_TWO_BY_THREE = AspectRatio("ASPECT_RATIO_TWO_BY_THREE")

    /** 3:2 aspect ratio. */
    val ASPECT_RATIO_THREE_BY_TWO = AspectRatio("ASPECT_RATIO_THREE_BY_TWO")

    /** 3:4 aspect ratio. */
    val ASPECT_RATIO_THREE_BY_FOUR = AspectRatio("ASPECT_RATIO_THREE_BY_FOUR")

    /** 4:3 aspect ratio. */
    val ASPECT_RATIO_FOUR_BY_THREE = AspectRatio("ASPECT_RATIO_FOUR_BY_THREE")

    /** 4:5 aspect ratio. */
    val ASPECT_RATIO_FOUR_BY_FIVE = AspectRatio("ASPECT_RATIO_FOUR_BY_FIVE")

    /** 5:4 aspect ratio. */
    val ASPECT_RATIO_FIVE_BY_FOUR = AspectRatio("ASPECT_RATIO_FIVE_BY_FOUR")

    /** 9:16 aspect ratio. */
    val ASPECT_RATIO_NINE_BY_SIXTEEN = AspectRatio("ASPECT_RATIO_NINE_BY_SIXTEEN")

    /** 16:9 aspect ratio. */
    val ASPECT_RATIO_SIXTEEN_BY_NINE = AspectRatio("ASPECT_RATIO_SIXTEEN_BY_NINE")

    /** 21:9 aspect ratio. */
    val ASPECT_RATIO_TWENTY_ONE_BY_NINE = AspectRatio("ASPECT_RATIO_TWENTY_ONE_BY_NINE")

    /** 1:8 aspect ratio. */
    val ASPECT_RATIO_ONE_BY_EIGHT = AspectRatio("ASPECT_RATIO_ONE_BY_EIGHT")

    /** 8:1 aspect ratio. */
    val ASPECT_RATIO_EIGHT_BY_ONE = AspectRatio("ASPECT_RATIO_EIGHT_BY_ONE")

    /** 1:4 aspect ratio. */
    val ASPECT_RATIO_ONE_BY_FOUR = AspectRatio("ASPECT_RATIO_ONE_BY_FOUR")

    /** 4:1 aspect ratio. */
    val ASPECT_RATIO_FOUR_BY_ONE = AspectRatio("ASPECT_RATIO_FOUR_BY_ONE")
  }
}
