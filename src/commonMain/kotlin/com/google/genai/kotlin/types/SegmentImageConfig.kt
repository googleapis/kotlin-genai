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

import kotlinx.serialization.Serializable

/** Configuration for segmenting an image. */
@Serializable
data class SegmentImageConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /** The segmentation mode to use. */
  val mode: SegmentMode? = null,

  /** The maximum number of predictions to return up to, by top confidence score. */
  val maxPredictions: Int? = null,

  /**
   * The confidence score threshold for the detections as a decimal value. Only predictions with a
   * confidence score higher than this threshold will be returned.
   */
  val confidenceThreshold: Double? = null,

  /**
   * A decimal value representing how much dilation to apply to the masks. 0 for no dilation. 1.0
   * means the masked area covers the whole image.
   */
  val maskDilation: Double? = null,

  /**
   * The binary color threshold to apply to the masks. The threshold can be set to a decimal value
   * between 0 and 255 non-inclusive. Set to -1 for no binary color thresholding.
   */
  val binaryColorThreshold: Double? = null,

  /** User specified labels to track billing usage. */
  val labels: Map<String, String>? = null,
)
