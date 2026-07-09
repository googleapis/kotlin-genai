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

package com.google.genai.kotlin

import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.PrebuiltVoiceConfig
import com.google.genai.kotlin.types.Schema
import com.google.genai.kotlin.types.SpeechConfig
import com.google.genai.kotlin.types.Tool
import com.google.genai.kotlin.types.VoiceConfig

/** Transformers for GenAI Kotlin SDK. */
internal object Transformers {

  /**
   * In other languages, this transformer is used for supporting union types. We pass it through, or
   * wrap Strings/Contents into a List.
   */
  fun tContents(origin: Any?): Any? {
    if (origin == null) return null
    return when (origin) {
      is String -> listOf(Content(parts = listOf(Part(text = origin))))
      is Content -> listOf(origin)
      else -> origin
    }
  }

  /**
   * In other languages, this transformer is used for supporting union types. We pass it through, or
   * wrap Strings into a Content.
   */
  fun tContent(origin: Any?): Any? {
    if (origin == null) return null
    return when (origin) {
      is String -> Content(parts = listOf(Part(text = origin)))
      else -> origin
    }
  }

  /** Transforms an object to a Schema data class for the API. */
  fun tSchema(origin: Any?): Map<String, Any?>? {
    if (origin == null) return null
    return when (origin) {
      is Map<*, *> -> {
        @Suppress("UNCHECKED_CAST")
        origin as Map<String, Any?>
      }
      is Schema -> Common.dataClassToMap(origin)
      else -> throw IllegalArgumentException("Unsupported schema type: ${origin::class}")
    }
  }

  /** Transforms an object to a SpeechConfig data class. */
  fun tSpeechConfig(origin: Any?): Map<String, Any?>? {
    if (origin == null) return null
    return when (origin) {
      is Map<*, *> -> {
        @Suppress("UNCHECKED_CAST")
        origin as Map<String, Any?>
      }
      is SpeechConfig -> Common.dataClassToMap(origin)
      is String -> {
        val speechConfig =
          SpeechConfig(
            voiceConfig = VoiceConfig(prebuiltVoiceConfig = PrebuiltVoiceConfig(voiceName = origin))
          )
        Common.dataClassToMap(speechConfig)
      }
      else -> throw IllegalArgumentException("Unsupported speechConfig type: ${origin::class}")
    }
  }

  /** Pass-through transformer for Tool lists. */
  fun tTools(origin: Any?): Any? {
    return origin
  }

  /** Transforms an object to a Tool data class. */
  fun tTool(origin: Any?): Any? {
    if (origin == null) return null
    return when (origin) {
      is Tool -> origin
      is Map<*, *> -> origin
      else -> throw IllegalArgumentException("Unsupported tool type: ${origin::class}")
    }
  }

  /** Transforms a model name to the correct format for the API. */
  fun tModel(apiClient: ApiClient, origin: Any?): String {
    requireNotNull(origin) { "model is required." }

    val model = if (origin is String) origin else origin.toString().replace("\"", "")
    require(model.isNotEmpty()) { "model is required." }
    require(!model.contains("..") && !model.contains("?") && !model.contains("&")) {
      "invalid model parameter."
    }

    if (apiClient.enterprise) {
      return when {
        model.startsWith("publishers/") ||
          model.startsWith("projects/") ||
          model.startsWith("models/") -> model
        model.contains("/") -> {
          val parts = model.split("/", limit = 2)
          "publishers/${parts[0]}/models/${parts[1]}"
        }
        else -> "publishers/google/models/$model"
      }
    } else {
      return if (model.startsWith("models/") || model.startsWith("tunedModels/")) {
        model
      } else {
        "models/$model"
      }
    }
  }

  /** Transforms a model name to the correct format for the Caches API. */
  fun tCachesModel(apiClient: ApiClient, origin: Any?): String {
    val model = tModel(apiClient, origin)
    if (apiClient.enterprise) {
      if (model.startsWith("publishers/")) {
        // Vertex caches only support model names starting with projects.
        return "projects/${apiClient.project}/locations/${apiClient.location}/$model"
      } else if (model.startsWith("models/")) {
        return "projects/${apiClient.project}/locations/${apiClient.location}/publishers/google/$model"
      }
    }
    return model
  }

  /** Transforms an object to a cached content name for the API. */
  fun tCachedContentName(apiClient: ApiClient, origin: Any?): String? {
    if (origin == null) return null

    val cachedContentName = if (origin is String) origin else origin.toString().replace("\"", "")
    return getResourceName(apiClient, cachedContentName, "cachedContents")
  }

  /** Formats a resource name given the resource name and resource prefix. */
  private fun getResourceName(
    apiClient: ApiClient,
    resourceName: String,
    resourcePrefix: String,
  ): String {
    // If there are no slashes in the name at all, it's just the ID, so we should prepend the
    // collection.
    val shouldPrependCollectionIdentifier = !resourceName.contains('/')

    if (apiClient.enterprise) {
      return when {
        resourceName.startsWith("projects/") -> resourceName
        resourceName.startsWith("locations/") -> "projects/${apiClient.project}/$resourceName"
        resourceName.startsWith("$resourcePrefix/") ->
          "projects/${apiClient.project}/locations/${apiClient.location}/$resourceName"
        shouldPrependCollectionIdentifier ->
          "projects/${apiClient.project}/locations/${apiClient.location}/$resourcePrefix/$resourceName"
        else -> resourceName
      }
    } else {
      return if (shouldPrependCollectionIdentifier) {
        "$resourcePrefix/$resourceName"
      } else {
        resourceName
      }
    }
  }
}
