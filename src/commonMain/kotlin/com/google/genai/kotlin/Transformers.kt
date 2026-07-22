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
import com.google.genai.kotlin.types.File
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
      is List<*> -> origin.map { if (it is String) Content(parts = listOf(Part(text = it))) else it }
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

  fun tExtractModels(origin: Any?): Any? {
    if (origin == null) return emptyList<Any?>()
    val response = origin as? Map<*, *> ?: return emptyList<Any?>()
    return response["models"] ?: response["tunedModels"] ?: response["publisherModels"] ?: emptyList<Any?>()
  }

  fun tModelsUrl(apiClient: ApiClient, baseModels: Any?): String {
    val isBaseModel = baseModels as? Boolean ?: false
    if (isBaseModel) {
      return if (apiClient.enterprise) "publishers/google/models" else "models"
    } else {
      return if (apiClient.enterprise) "models" else "tunedModels"
    }
  }

  fun tTuningJobStatus(status: Any?): String? {
    val statusStr = status as? String ?: return null
    return when (statusStr) {
      "STATE_UNSPECIFIED" -> "JOB_STATE_UNSPECIFIED"
      "CREATING" -> "JOB_STATE_RUNNING"
      "ACTIVE" -> "JOB_STATE_SUCCEEDED"
      "FAILED" -> "JOB_STATE_FAILED"
      else -> statusStr
    }
  }

  fun tBatchJobName(apiClient: ApiClient, name: Any?): Any? {
    if (name == null) return null
    val nameStr = name.toString().replace("\"", "")
    if (apiClient.enterprise) {
      if (nameStr.startsWith("projects/") && nameStr.contains("/batchPredictionJobs/")) {
        return nameStr.substringAfterLast("/")
      }
      return nameStr
    } else {
      if (nameStr.startsWith("batches/")) {
        return nameStr.substringAfterLast("/")
      }
      return nameStr
    }
  }

  fun tJobState(state: Any?): Any? {
    if (state == null) return null
    return state.toString().replace("BATCH_STATE_", "JOB_STATE_")
  }

  fun tBatchJobSource(source: Any?): Any? = source
  fun tBatchJobDestination(destination: Any?): Any? = destination
  fun tRecvBatchJobDestination(destination: Any?): Any? = destination

  fun tFileName(origin: Any?): String? {
    if (origin == null) return null
    var name = when (origin) {
      is String -> origin
      is File -> origin.name ?: throw IllegalArgumentException("File name is required.")
      else -> origin.toString().replace("\"", "")
    }

    if (name.startsWith("https://")) {
      val suffix = name.substringAfter("files/")
      val regex = Regex("[a-z0-9-]+")
      name = regex.find(suffix)?.value ?: throw IllegalArgumentException("Invalid file URI: $origin")
    } else if (name.startsWith("files/")) {
      name = name.substringAfter("files/")
    }
    return name
  }

  /** Checks if the model uses the vertex embedContent endpoint. */
  fun tIsVertexEmbedContentModel(model: String): Boolean {
    return (model.contains("gemini") && model != "gemini-embedding-001") || model.contains("maas")
  }

  /** Transforms contents for embedding requests. */
  fun tContentsForEmbed(apiClient: ApiClient, origin: Any?): Any? {
    if (origin == null) return null
    val list = when (origin) {
      is List<*> -> origin
      else -> listOf(origin)
    }
    if (apiClient.enterprise) {
      val textParts = mutableListOf<String>()
      for (item in list) {
        if (item is Content) {
          item.parts?.forEach { part ->
            part.text?.let { textParts.add(it) }
          }
        } else if (item is Map<*, *>) {
          val parts = item["parts"] as? List<*>
          parts?.forEach { partObj ->
            if (partObj is Part) {
              partObj.text?.let { textParts.add(it) }
            } else if (partObj is Map<*, *>) {
              (partObj["text"] as? String)?.let { textParts.add(it) }
            }
          }
        } else if (item is String) {
          textParts.add(item)
        }
      }
      return textParts
    } else {
      return list.mapNotNull { item ->
        when (item) {
          is String -> Common.dataClassToMap(Content(parts = listOf(Part(text = item))))
          is Content -> Common.dataClassToMap(item)
          is Map<*, *> -> {
            @Suppress("UNCHECKED_CAST")
            item as Map<String, Any?>
          }
          else -> null
        }
      }
    }
  }
}
