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

/** Class that represents the parameters for generating videos. */
@Serializable
data class GenerateVideosParameters(

  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  val model: String? = null,

  /** The text prompt for generating the videos. Optional if image or video is provided. */
  val prompt: String? = null,

  /**
   * The input image for generating the videos. Optional if prompt is provided. Not allowed if video
   * is provided.
   */
  val image: Image? = null,

  /**
   * The input video for video extension use cases. Optional if prompt is provided. Not allowed if
   * image is provided.
   */
  val video: Video? = null,

  /** A set of source input(s) for video generation. */
  val source: GenerateVideosSource? = null,

  /** Configuration for generating videos. */
  val config: GenerateVideosConfig? = null,
)
