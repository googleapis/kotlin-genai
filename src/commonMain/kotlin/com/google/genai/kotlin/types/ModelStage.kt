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

/** The stage of the underlying model. This enum is not supported in Vertex AI. */
@Serializable
@JvmInline
value class ModelStage(val value: String) {
  companion object {

    /** Unspecified model stage. */
    val MODEL_STAGE_UNSPECIFIED = ModelStage("MODEL_STAGE_UNSPECIFIED")

    /** The underlying model is subject to lots of tunings. */
    val UNSTABLE_EXPERIMENTAL = ModelStage("UNSTABLE_EXPERIMENTAL")

    /** Models in this stage are for experimental purposes only. */
    val EXPERIMENTAL = ModelStage("EXPERIMENTAL")

    /** Models in this stage are more mature than experimental models. */
    val PREVIEW = ModelStage("PREVIEW")

    /** Models in this stage are considered stable and ready for production use. */
    val STABLE = ModelStage("STABLE")

    /**
     * If the model is on this stage, it means that this model is on the path to deprecation in near
     * future. Only existing customers can use this model.
     */
    val LEGACY = ModelStage("LEGACY")

    /** Models in this stage are deprecated. These models cannot be used. */
    val DEPRECATED = ModelStage("DEPRECATED")

    /** Models in this stage are retired. These models cannot be used. */
    val RETIRED = ModelStage("RETIRED")
  }
}
