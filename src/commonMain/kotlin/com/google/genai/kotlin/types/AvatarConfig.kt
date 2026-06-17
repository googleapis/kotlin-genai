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

/** Configures the avatar to be used in the session. */
@Serializable
data class AvatarConfig(

  /** Pre-built avatar id. */
  val avatarName: String? = null,

  /** Customized avatar appearance with a reference image. */
  val customizedAvatar: CustomizedAvatar? = null,

  /** The bitrate of compressed audio. */
  val audioBitrateBps: Int? = null,

  /** The bitrate of compressed video output. */
  val videoBitrateBps: Int? = null,
)
