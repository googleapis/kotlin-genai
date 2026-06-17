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

/** Configuration for generating videos. */
@Serializable
data class GenerateVideosConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /** Number of output videos. */
  val numberOfVideos: Int? = null,

  /** The gcs bucket where to save the generated videos. */
  val outputGcsUri: String? = null,

  /** Frames per second for video generation. */
  val fps: Int? = null,

  /** Duration of the clip for video generation in seconds. */
  val durationSeconds: Int? = null,

  /**
   * The RNG seed. If RNG seed is exactly same for each request with unchanged inputs, the
   * prediction results will be consistent. Otherwise, a random RNG seed will be used each time to
   * produce a different result.
   */
  val seed: Int? = null,

  /**
   * The aspect ratio for the generated video. 16:9 (landscape) and 9:16 (portrait) are supported.
   */
  val aspectRatio: String? = null,

  /** The resolution for the generated video. 720p and 1080p are supported. */
  val resolution: String? = null,

  /**
   * Whether allow to generate person videos, and restrict to specific ages. Supported values are:
   * dont_allow, allow_adult.
   */
  val personGeneration: String? = null,

  /** The pubsub topic where to publish the video generation progress. */
  val pubsubTopic: String? = null,

  /** Explicitly state what should not be included in the generated videos. */
  val negativePrompt: String? = null,

  /** Whether to use the prompt rewriting logic. */
  val enhancePrompt: Boolean? = null,

  /** Whether to generate audio along with the video. */
  val generateAudio: Boolean? = null,

  /**
   * Image to use as the last frame of generated videos. Only supported for image to video use
   * cases.
   */
  val lastFrame: Image? = null,

  /**
   * The images to use as the references to generate the videos. If this field is provided, the text
   * prompt field must also be provided. The image, video, or last_frame field are not supported.
   * Each image must be associated with a type. Veo 2 supports up to 3 asset images *or* 1 style
   * image.
   */
  val referenceImages: List<VideoGenerationReferenceImage>? = null,

  /** The mask to use for generating videos. */
  val mask: VideoGenerationMask? = null,

  /** Compression quality of the generated videos. */
  val compressionQuality: VideoCompressionQuality? = null,

  /** User specified labels to track billing usage. */
  val labels: Map<String, String>? = null,

  /**
   * Webhook configuration for receiving notifications when the video generation operation
   * completes.
   */
  val webhookConfig: WebhookConfig? = null,

  /** Resize mode of the image input for video generation. */
  val resizeMode: ImageResizeMode? = null,
)
