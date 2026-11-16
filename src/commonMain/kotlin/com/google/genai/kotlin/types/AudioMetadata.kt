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
 * Metadata describes the input audio content.
 *
 * Set this in `Part.audio_metadata` or `FunctionResponsePart.audio_metadata` when `inline_data` or
 * `file_data` contains audio to configure static audio preprocessing.
 */
@Serializable
data class AudioMetadata(

  /** The start offset of the audio, relative to the start of the audio stream. */
  @Serializable(with = DurationStringSerializer::class) val startOffset: Duration? = null,

  /** The end offset of the audio, relative to the start of the audio stream. */
  @Serializable(with = DurationStringSerializer::class) val endOffset: Duration? = null,
)
