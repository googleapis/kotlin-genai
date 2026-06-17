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

import kotlin.time.Duration
import kotlinx.serialization.Serializable

/**
 * Provides metadata for a video, including the start and end offsets for clipping and the frame
 * rate.
 */
@Serializable
data class VideoMetadata(

  /** Optional. The end offset of the video. */
  val endOffset: Duration? = null,

  /**
   * Optional. The frame rate of the video sent to the model. If not specified, the default value is
   * 1.0. The valid range is (0.0, 24.0].
   */
  val fps: Double? = null,

  /** Optional. The start offset of the video. */
  val startOffset: Duration? = null,
)
