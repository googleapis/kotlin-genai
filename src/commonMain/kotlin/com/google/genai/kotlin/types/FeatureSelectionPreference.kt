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

/** Options for feature selection preference. */
@Serializable
@JvmInline
value class FeatureSelectionPreference(val value: String) {
  companion object {

    /**  */
    val FEATURE_SELECTION_PREFERENCE_UNSPECIFIED =
      FeatureSelectionPreference("FEATURE_SELECTION_PREFERENCE_UNSPECIFIED")

    /**  */
    val PRIORITIZE_QUALITY = FeatureSelectionPreference("PRIORITIZE_QUALITY")

    /**  */
    val BALANCED = FeatureSelectionPreference("BALANCED")

    /**  */
    val PRIORITIZE_COST = FeatureSelectionPreference("PRIORITIZE_COST")
  }
}
