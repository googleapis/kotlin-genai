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

@file:Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER", "UNUSED_ANONYMOUS_PARAMETER")

package com.google.genai.kotlin

import com.google.genai.kotlin.types.ComputeTokensConfig
import com.google.genai.kotlin.types.ComputeTokensParameters
import com.google.genai.kotlin.types.ComputeTokensResponse
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.CountTokensConfig
import com.google.genai.kotlin.types.CountTokensParameters
import com.google.genai.kotlin.types.CountTokensResponse
import com.google.genai.kotlin.types.EmbedContentConfig
import com.google.genai.kotlin.types.EmbedContentParametersPrivate
import com.google.genai.kotlin.types.EmbedContentResponse
import com.google.genai.kotlin.types.EmbeddingApiType
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.GenerateContentParameters
import com.google.genai.kotlin.types.GenerateContentResponse
import com.google.genai.kotlin.types.HttpResponse
import com.google.genai.kotlin.types.Part
import io.ktor.http.encodeURLQueryComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Provides methods for interacting with the available GenAI models. Instantiating this class is not
 * required. After instantiating a [Client], access methods through `client.models` directly.
 */
class Models internal constructor(internal val apiClient: ApiClient) {

  internal fun authConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("apiKey"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("apiKey"),
        Common.getValueByPath(fromObject, arrayOf("apiKey")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("apiKeyConfig")))) {
      throw IllegalArgumentException("apiKeyConfig parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("authType")))) {
      throw IllegalArgumentException("authType parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("googleServiceAccountConfig")))) {
      throw IllegalArgumentException(
        "googleServiceAccountConfig parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("httpBasicAuthConfig")))) {
      throw IllegalArgumentException(
        "httpBasicAuthConfig parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("oauthConfig")))) {
      throw IllegalArgumentException("oauthConfig parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("oidcConfig")))) {
      throw IllegalArgumentException("oidcConfig parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun blobToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("data"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("data"),
        Common.getValueByPath(fromObject, arrayOf("data")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("displayName")))) {
      throw IllegalArgumentException("displayName parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("mimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mimeType"),
        Common.getValueByPath(fromObject, arrayOf("mimeType")),
      )
    }

    return toObject
  }

  internal fun candidateFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("content"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("content"),
        Common.getValueByPath(fromObject, arrayOf("content")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("citationMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("citationMetadata"),
        citationMetadataFromMldev(
          Common.getValueByPath(fromObject, arrayOf("citationMetadata")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tokenCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tokenCount"),
        Common.getValueByPath(fromObject, arrayOf("tokenCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("finishReason"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("finishReason"),
        Common.getValueByPath(fromObject, arrayOf("finishReason")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("groundingMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("groundingMetadata"),
        Common.getValueByPath(fromObject, arrayOf("groundingMetadata")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("avgLogprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("avgLogprobs"),
        Common.getValueByPath(fromObject, arrayOf("avgLogprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("index"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("index"),
        Common.getValueByPath(fromObject, arrayOf("index")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("logprobsResult"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("logprobsResult"),
        Common.getValueByPath(fromObject, arrayOf("logprobsResult")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("safetyRatings"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("safetyRatings"),
        Common.getValueByPath(fromObject, arrayOf("safetyRatings")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("urlContextMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("urlContextMetadata"),
        Common.getValueByPath(fromObject, arrayOf("urlContextMetadata")),
      )
    }

    return toObject
  }

  internal fun citationMetadataFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("citationSources"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("citations"),
        Common.getValueByPath(fromObject, arrayOf("citationSources")),
      )
    }

    return toObject
  }

  internal fun codeExecutionResultToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("outcome"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("outcome"),
        Common.getValueByPath(fromObject, arrayOf("outcome")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("output"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("output"),
        Common.getValueByPath(fromObject, arrayOf("output")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("id")))) {
      throw IllegalArgumentException(
        "id parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun computeTokensParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      val keyArray = Transformers.tContents(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("contents"), result)
    }

    return toObject
  }

  internal fun computeTokensResponseFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tokensInfo"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tokensInfo"),
        Common.getValueByPath(fromObject, arrayOf("tokensInfo")),
      )
    }

    return toObject
  }

  internal fun computerUseToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("environment"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("environment"),
        Common.getValueByPath(fromObject, arrayOf("environment")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("excludedPredefinedFunctions"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("excludedPredefinedFunctions"),
        Common.getValueByPath(fromObject, arrayOf("excludedPredefinedFunctions")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("enablePromptInjectionDetection"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("enablePromptInjectionDetection"),
        Common.getValueByPath(fromObject, arrayOf("enablePromptInjectionDetection")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("disabledSafetyPolicies")))) {
      throw IllegalArgumentException(
        "disabledSafetyPolicies parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun contentEmbeddingFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("values"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("values"),
        Common.getValueByPath(fromObject, arrayOf("values")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("statistics"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("statistics"),
        contentEmbeddingStatisticsFromVertex(
          Common.getValueByPath(fromObject, arrayOf("statistics")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    return toObject
  }

  internal fun contentEmbeddingStatisticsFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("truncated"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("truncated"),
        Common.getValueByPath(fromObject, arrayOf("truncated")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("token_count"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tokenCount"),
        Common.getValueByPath(fromObject, arrayOf("token_count")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tokensDetails"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tokensDetails"),
        Common.getValueByPath(fromObject, arrayOf("tokensDetails")),
      )
    }

    return toObject
  }

  internal fun contentToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("parts"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(partToMldev(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("parts"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("role"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("role"),
        Common.getValueByPath(fromObject, arrayOf("role")),
      )
    }

    return toObject
  }

  internal fun contentToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("parts"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(partToVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("parts"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("role"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("role"),
        Common.getValueByPath(fromObject, arrayOf("role")),
      )
    }

    return toObject
  }

  internal fun countTokensConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))) {
      throw IllegalArgumentException("systemInstruction parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("tools")))) {
      throw IllegalArgumentException("tools parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("generationConfig")))) {
      throw IllegalArgumentException("generationConfig parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun countTokensConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("systemInstruction"),
        contentToVertex(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))
            as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tools"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(toolToVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(parentObject, arrayOf("tools"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("generationConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("generationConfig"),
        generationConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("generationConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    return toObject
  }

  internal fun countTokensParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      val keyArray = Transformers.tContents(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToMldev(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("contents"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        countTokensConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
          rootObject,
        )
    }

    return toObject
  }

  internal fun countTokensParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      val keyArray = Transformers.tContents(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("contents"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        countTokensConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
          rootObject,
        )
    }

    return toObject
  }

  internal fun countTokensResponseFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("totalTokens"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("totalTokens"),
        Common.getValueByPath(fromObject, arrayOf("totalTokens")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("cachedContentTokenCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("cachedContentTokenCount"),
        Common.getValueByPath(fromObject, arrayOf("cachedContentTokenCount")),
      )
    }

    return toObject
  }

  internal fun countTokensResponseFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("totalTokens"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("totalTokens"),
        Common.getValueByPath(fromObject, arrayOf("totalTokens")),
      )
    }

    return toObject
  }

  internal fun embedContentConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("taskType"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("requests[]", "taskType"),
        Common.getValueByPath(fromObject, arrayOf("taskType")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("title"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("requests[]", "title"),
        Common.getValueByPath(fromObject, arrayOf("title")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputDimensionality"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("requests[]", "outputDimensionality"),
        Common.getValueByPath(fromObject, arrayOf("outputDimensionality")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("mimeType")))) {
      throw IllegalArgumentException("mimeType parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("autoTruncate")))) {
      throw IllegalArgumentException("autoTruncate parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("documentOcr")))) {
      throw IllegalArgumentException("documentOcr parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("audioTrackExtraction")))) {
      throw IllegalArgumentException(
        "audioTrackExtraction parameter is not supported in Gemini API."
      )
    }

    return toObject
  }

  internal fun embedContentConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    val discriminatorValueTaskType =
      Common.getValueByPath(rootObject, arrayOf("embeddingApiType"))?.toString() ?: "PREDICT"

    Common.getValueByPath(fromObject, arrayOf("taskType"))?.let { taskType ->
      when (discriminatorValueTaskType) {
        "PREDICT" -> {
          Common.getValueByPath(fromObject, arrayOf("taskType"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("instances[]", "task_type"),
              Common.getValueByPath(fromObject, arrayOf("taskType")),
            )
          }
        }
        "EMBED_CONTENT" -> {
          Common.getValueByPath(fromObject, arrayOf("taskType"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("embedContentConfig", "taskType"),
              Common.getValueByPath(fromObject, arrayOf("taskType")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueTitle =
      Common.getValueByPath(rootObject, arrayOf("embeddingApiType"))?.toString() ?: "PREDICT"

    Common.getValueByPath(fromObject, arrayOf("title"))?.let { title ->
      when (discriminatorValueTitle) {
        "PREDICT" -> {
          Common.getValueByPath(fromObject, arrayOf("title"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("instances[]", "title"),
              Common.getValueByPath(fromObject, arrayOf("title")),
            )
          }
        }
        "EMBED_CONTENT" -> {
          Common.getValueByPath(fromObject, arrayOf("title"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("embedContentConfig", "title"),
              Common.getValueByPath(fromObject, arrayOf("title")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueOutputDimensionality =
      Common.getValueByPath(rootObject, arrayOf("embeddingApiType"))?.toString() ?: "PREDICT"

    Common.getValueByPath(fromObject, arrayOf("outputDimensionality"))?.let { outputDimensionality
      ->
      when (discriminatorValueOutputDimensionality) {
        "PREDICT" -> {
          Common.getValueByPath(fromObject, arrayOf("outputDimensionality"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("parameters", "outputDimensionality"),
              Common.getValueByPath(fromObject, arrayOf("outputDimensionality")),
            )
          }
        }
        "EMBED_CONTENT" -> {
          Common.getValueByPath(fromObject, arrayOf("outputDimensionality"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("embedContentConfig", "outputDimensionality"),
              Common.getValueByPath(fromObject, arrayOf("outputDimensionality")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueMimeType =
      Common.getValueByPath(rootObject, arrayOf("embeddingApiType"))?.toString() ?: "PREDICT"

    Common.getValueByPath(fromObject, arrayOf("mimeType"))?.let { mimeType ->
      when (discriminatorValueMimeType) {
        "PREDICT" -> {
          Common.getValueByPath(fromObject, arrayOf("mimeType"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("instances[]", "mimeType"),
              Common.getValueByPath(fromObject, arrayOf("mimeType")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueAutoTruncate =
      Common.getValueByPath(rootObject, arrayOf("embeddingApiType"))?.toString() ?: "PREDICT"

    Common.getValueByPath(fromObject, arrayOf("autoTruncate"))?.let { autoTruncate ->
      when (discriminatorValueAutoTruncate) {
        "PREDICT" -> {
          Common.getValueByPath(fromObject, arrayOf("autoTruncate"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("parameters", "autoTruncate"),
              Common.getValueByPath(fromObject, arrayOf("autoTruncate")),
            )
          }
        }
        "EMBED_CONTENT" -> {
          Common.getValueByPath(fromObject, arrayOf("autoTruncate"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("embedContentConfig", "autoTruncate"),
              Common.getValueByPath(fromObject, arrayOf("autoTruncate")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueDocumentOcr =
      Common.getValueByPath(rootObject, arrayOf("embeddingApiType"))?.toString() ?: "PREDICT"

    Common.getValueByPath(fromObject, arrayOf("documentOcr"))?.let { documentOcr ->
      when (discriminatorValueDocumentOcr) {
        "EMBED_CONTENT" -> {
          Common.getValueByPath(fromObject, arrayOf("documentOcr"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("embedContentConfig", "documentOcr"),
              Common.getValueByPath(fromObject, arrayOf("documentOcr")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueAudioTrackExtraction =
      Common.getValueByPath(rootObject, arrayOf("embeddingApiType"))?.toString() ?: "PREDICT"

    Common.getValueByPath(fromObject, arrayOf("audioTrackExtraction"))?.let { audioTrackExtraction
      ->
      when (discriminatorValueAudioTrackExtraction) {
        "EMBED_CONTENT" -> {
          Common.getValueByPath(fromObject, arrayOf("audioTrackExtraction"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("embedContentConfig", "audioTrackExtraction"),
              Common.getValueByPath(fromObject, arrayOf("audioTrackExtraction")),
            )
          }
        }
        else -> {}
      }
    }

    return toObject
  }

  internal fun embedContentParametersPrivateToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("requests[]", "content"),
        Transformers.tContentsForEmbed(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("contents")),
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("content"))?.let { node ->
      val unused =
        contentToMldev(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("content")))
            as Map<String, Any?>,
          toObject,
          rootObject,
        )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        embedContentConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
          rootObject,
        )
    }

    Common.setValueByPath(
      toObject,
      arrayOf("requests[]", "model"),
      Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
    )

    return toObject
  }

  internal fun embedContentParametersPrivateToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    val discriminatorValueContents =
      Common.getValueByPath(rootObject, arrayOf("embeddingApiType"))?.toString() ?: "PREDICT"

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { contents ->
      when (discriminatorValueContents) {
        "PREDICT" -> {
          Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
            Common.setValueByPath(
              toObject,
              arrayOf("instances[]", "content"),
              Transformers.tContentsForEmbed(
                apiClient,
                Common.getValueByPath(fromObject, arrayOf("contents")),
              ),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueContent =
      Common.getValueByPath(rootObject, arrayOf("embeddingApiType"))?.toString() ?: "PREDICT"

    Common.getValueByPath(fromObject, arrayOf("content"))?.let { content ->
      when (discriminatorValueContent) {
        "EMBED_CONTENT" -> {
          Common.getValueByPath(fromObject, arrayOf("content"))?.let { node ->
            Common.setValueByPath(
              toObject,
              arrayOf("content"),
              contentToVertex(
                Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("content")))
                  as Map<String, Any?>,
                toObject,
                rootObject,
              ),
            )
          }
        }
        else -> {}
      }
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        embedContentConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
          rootObject,
        )
    }

    return toObject
  }

  internal fun embedContentResponseFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("embeddings"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("embeddings"),
        Common.getValueByPath(fromObject, arrayOf("embeddings")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("metadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("metadata"),
        Common.getValueByPath(fromObject, arrayOf("metadata")),
      )
    }

    return toObject
  }

  internal fun embedContentResponseFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("predictions[]", "embeddings"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentEmbeddingFromVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("embeddings"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("metadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("metadata"),
        Common.getValueByPath(fromObject, arrayOf("metadata")),
      )
    }

    Common.getValueByPath(rootObject, arrayOf("embeddingApiType"))?.let { apiType ->
      if (apiType.toString() == "EMBED_CONTENT") {
        val embedding =
          Common.getValueByPath(fromObject, arrayOf("embedding")) as? MutableMap<String, Any?>
        if (embedding != null) {
          val stats = mutableMapOf<String, Any?>()
          val usageMetadata =
            Common.getValueByPath(fromObject, arrayOf("usageMetadata")) as? Map<*, *>
          if (usageMetadata != null && usageMetadata["promptTokenCount"] != null) {
            stats["tokenCount"] = usageMetadata["promptTokenCount"]!!
          }
          if (usageMetadata != null && usageMetadata["promptTokensDetails"] != null) {
            stats["tokensDetails"] = usageMetadata["promptTokensDetails"]!!
          }
          val truncated = Common.getValueByPath(fromObject, arrayOf("truncated"))
          if (truncated != null) {
            stats["truncated"] = truncated
          }
          embedding["statistics"] = stats
          Common.setValueByPath(toObject, arrayOf("embeddings"), listOf(embedding))
        }
      }
    }

    return toObject
  }

  internal fun executableCodeToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("code"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("code"),
        Common.getValueByPath(fromObject, arrayOf("code")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("language"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("language"),
        Common.getValueByPath(fromObject, arrayOf("language")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("id")))) {
      throw IllegalArgumentException(
        "id parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun fileDataToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("displayName")))) {
      throw IllegalArgumentException("displayName parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("fileUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fileUri"),
        Common.getValueByPath(fromObject, arrayOf("fileUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mimeType"),
        Common.getValueByPath(fromObject, arrayOf("mimeType")),
      )
    }

    return toObject
  }

  internal fun functionCallToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("id"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("id"),
        Common.getValueByPath(fromObject, arrayOf("id")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("args"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("args"),
        Common.getValueByPath(fromObject, arrayOf("args")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("partialArgs")))) {
      throw IllegalArgumentException("partialArgs parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("willContinue")))) {
      throw IllegalArgumentException("willContinue parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun functionCallingConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("allowedFunctionNames"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("allowedFunctionNames"),
        Common.getValueByPath(fromObject, arrayOf("allowedFunctionNames")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mode"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mode"),
        Common.getValueByPath(fromObject, arrayOf("mode")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("streamFunctionCallArguments")))) {
      throw IllegalArgumentException(
        "streamFunctionCallArguments parameter is not supported in Gemini API."
      )
    }

    return toObject
  }

  internal fun generateContentConfigToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("systemInstruction"),
        contentToMldev(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))
            as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("temperature"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("temperature"),
        Common.getValueByPath(fromObject, arrayOf("temperature")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topP"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("topP"),
        Common.getValueByPath(fromObject, arrayOf("topP")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topK"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("topK"),
        Common.getValueByPath(fromObject, arrayOf("topK")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("candidateCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("candidateCount"),
        Common.getValueByPath(fromObject, arrayOf("candidateCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("maxOutputTokens"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("maxOutputTokens"),
        Common.getValueByPath(fromObject, arrayOf("maxOutputTokens")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("stopSequences"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("stopSequences"),
        Common.getValueByPath(fromObject, arrayOf("stopSequences")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseLogprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseLogprobs"),
        Common.getValueByPath(fromObject, arrayOf("responseLogprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("logprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("logprobs"),
        Common.getValueByPath(fromObject, arrayOf("logprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("presencePenalty"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("presencePenalty"),
        Common.getValueByPath(fromObject, arrayOf("presencePenalty")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("frequencyPenalty"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("frequencyPenalty"),
        Common.getValueByPath(fromObject, arrayOf("frequencyPenalty")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("seed"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("seed"),
        Common.getValueByPath(fromObject, arrayOf("seed")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseMimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseMimeType"),
        Common.getValueByPath(fromObject, arrayOf("responseMimeType")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseSchema"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseSchema"),
        Transformers.tSchema(Common.getValueByPath(fromObject, arrayOf("responseSchema"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseJsonSchema"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseJsonSchema"),
        Common.getValueByPath(fromObject, arrayOf("responseJsonSchema")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("modelSelectionConfig")))) {
      throw IllegalArgumentException(
        "modelSelectionConfig parameter is not supported in Gemini API."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("safetySettings"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(safetySettingToMldev(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(parentObject, arrayOf("safetySettings"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("tools"))?.let { node ->
      val keyArray = Transformers.tTools(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(
            toolToMldev(Transformers.tTool(item) as Map<String, Any?>, toObject, rootObject)
          )
        }
      }
      Common.setValueByPath(parentObject, arrayOf("tools"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("toolConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("toolConfig"),
        toolConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("toolConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("cachedContent"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("cachedContent"),
        Transformers.tCachedContentName(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("cachedContent")),
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseModalities"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseModalities"),
        Common.getValueByPath(fromObject, arrayOf("responseModalities")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("speechConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("speechConfig"),
        Transformers.tSpeechConfig(Common.getValueByPath(fromObject, arrayOf("speechConfig"))),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("audioTimestamp")))) {
      throw IllegalArgumentException("audioTimestamp parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("thinkingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thinkingConfig"),
        Common.getValueByPath(fromObject, arrayOf("thinkingConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("imageConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("imageConfig"),
        imageConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("imageConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("enableEnhancedCivicAnswers"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("enableEnhancedCivicAnswers"),
        Common.getValueByPath(fromObject, arrayOf("enableEnhancedCivicAnswers")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("modelArmorConfig")))) {
      throw IllegalArgumentException("modelArmorConfig parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("serviceTier"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("serviceTier"),
        Common.getValueByPath(fromObject, arrayOf("serviceTier")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("routingConfig")))) {
      throw IllegalArgumentException("routingConfig parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("labels")))) {
      throw IllegalArgumentException("labels parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun generateContentConfigToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("systemInstruction"),
        contentToVertex(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))
            as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("temperature"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("temperature"),
        Common.getValueByPath(fromObject, arrayOf("temperature")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topP"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("topP"),
        Common.getValueByPath(fromObject, arrayOf("topP")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topK"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("topK"),
        Common.getValueByPath(fromObject, arrayOf("topK")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("candidateCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("candidateCount"),
        Common.getValueByPath(fromObject, arrayOf("candidateCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("maxOutputTokens"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("maxOutputTokens"),
        Common.getValueByPath(fromObject, arrayOf("maxOutputTokens")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("stopSequences"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("stopSequences"),
        Common.getValueByPath(fromObject, arrayOf("stopSequences")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseLogprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseLogprobs"),
        Common.getValueByPath(fromObject, arrayOf("responseLogprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("logprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("logprobs"),
        Common.getValueByPath(fromObject, arrayOf("logprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("presencePenalty"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("presencePenalty"),
        Common.getValueByPath(fromObject, arrayOf("presencePenalty")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("frequencyPenalty"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("frequencyPenalty"),
        Common.getValueByPath(fromObject, arrayOf("frequencyPenalty")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("seed"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("seed"),
        Common.getValueByPath(fromObject, arrayOf("seed")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseMimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseMimeType"),
        Common.getValueByPath(fromObject, arrayOf("responseMimeType")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseSchema"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseSchema"),
        Transformers.tSchema(Common.getValueByPath(fromObject, arrayOf("responseSchema"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseJsonSchema"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseJsonSchema"),
        Common.getValueByPath(fromObject, arrayOf("responseJsonSchema")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("modelSelectionConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("modelConfig"),
        Common.getValueByPath(fromObject, arrayOf("modelSelectionConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("safetySettings"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("safetySettings"),
        Common.getValueByPath(fromObject, arrayOf("safetySettings")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tools"))?.let { node ->
      val keyArray = Transformers.tTools(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(
            toolToVertex(Transformers.tTool(item) as Map<String, Any?>, toObject, rootObject)
          )
        }
      }
      Common.setValueByPath(parentObject, arrayOf("tools"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("toolConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("toolConfig"),
        toolConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("toolConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("cachedContent"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("cachedContent"),
        Transformers.tCachedContentName(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("cachedContent")),
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseModalities"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseModalities"),
        Common.getValueByPath(fromObject, arrayOf("responseModalities")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("speechConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("speechConfig"),
        speechConfigToVertex(
          Transformers.tSpeechConfig(Common.getValueByPath(fromObject, arrayOf("speechConfig")))
            as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("audioTimestamp"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audioTimestamp"),
        Common.getValueByPath(fromObject, arrayOf("audioTimestamp")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thinkingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thinkingConfig"),
        Common.getValueByPath(fromObject, arrayOf("thinkingConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("imageConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("imageConfig"),
        imageConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("imageConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("enableEnhancedCivicAnswers")))) {
      throw IllegalArgumentException(
        "enableEnhancedCivicAnswers parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("modelArmorConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("modelArmorConfig"),
        Common.getValueByPath(fromObject, arrayOf("modelArmorConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("serviceTier"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("serviceTier"),
        Common.getValueByPath(fromObject, arrayOf("serviceTier")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("routingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("routingConfig"),
        Common.getValueByPath(fromObject, arrayOf("routingConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("labels"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("labels"),
        Common.getValueByPath(fromObject, arrayOf("labels")),
      )
    }

    return toObject
  }

  internal fun generateContentParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      val keyArray = Transformers.tContents(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToMldev(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("contents"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("generationConfig"),
        generateContentConfigToMldev(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    return toObject
  }

  internal fun generateContentParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      val keyArray = Transformers.tContents(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("contents"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("generationConfig"),
        generateContentConfigToVertex(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    return toObject
  }

  internal fun generateContentResponseFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("candidates"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(candidateFromMldev(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("candidates"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("modelVersion"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("modelVersion"),
        Common.getValueByPath(fromObject, arrayOf("modelVersion")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("promptFeedback"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("promptFeedback"),
        Common.getValueByPath(fromObject, arrayOf("promptFeedback")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseId"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseId"),
        Common.getValueByPath(fromObject, arrayOf("responseId")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("usageMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("usageMetadata"),
        Common.getValueByPath(fromObject, arrayOf("usageMetadata")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("modelStatus"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("modelStatus"),
        Common.getValueByPath(fromObject, arrayOf("modelStatus")),
      )
    }

    return toObject
  }

  internal fun generateContentResponseFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("candidates"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("candidates"),
        Common.getValueByPath(fromObject, arrayOf("candidates")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("createTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("createTime"),
        Common.getValueByPath(fromObject, arrayOf("createTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("modelVersion"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("modelVersion"),
        Common.getValueByPath(fromObject, arrayOf("modelVersion")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("promptFeedback"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("promptFeedback"),
        Common.getValueByPath(fromObject, arrayOf("promptFeedback")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseId"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseId"),
        Common.getValueByPath(fromObject, arrayOf("responseId")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("usageMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("usageMetadata"),
        Common.getValueByPath(fromObject, arrayOf("usageMetadata")),
      )
    }

    return toObject
  }

  internal fun generationConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("modelSelectionConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("modelConfig"),
        Common.getValueByPath(fromObject, arrayOf("modelSelectionConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseJsonSchema"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseJsonSchema"),
        Common.getValueByPath(fromObject, arrayOf("responseJsonSchema")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("audioTimestamp"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audioTimestamp"),
        Common.getValueByPath(fromObject, arrayOf("audioTimestamp")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("candidateCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("candidateCount"),
        Common.getValueByPath(fromObject, arrayOf("candidateCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("enableAffectiveDialog"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("enableAffectiveDialog"),
        Common.getValueByPath(fromObject, arrayOf("enableAffectiveDialog")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("frequencyPenalty"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("frequencyPenalty"),
        Common.getValueByPath(fromObject, arrayOf("frequencyPenalty")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("logprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("logprobs"),
        Common.getValueByPath(fromObject, arrayOf("logprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("maxOutputTokens"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("maxOutputTokens"),
        Common.getValueByPath(fromObject, arrayOf("maxOutputTokens")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("presencePenalty"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("presencePenalty"),
        Common.getValueByPath(fromObject, arrayOf("presencePenalty")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseLogprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseLogprobs"),
        Common.getValueByPath(fromObject, arrayOf("responseLogprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseMimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseMimeType"),
        Common.getValueByPath(fromObject, arrayOf("responseMimeType")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseModalities"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseModalities"),
        Common.getValueByPath(fromObject, arrayOf("responseModalities")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseSchema"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseSchema"),
        Common.getValueByPath(fromObject, arrayOf("responseSchema")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("routingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("routingConfig"),
        Common.getValueByPath(fromObject, arrayOf("routingConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("seed"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("seed"),
        Common.getValueByPath(fromObject, arrayOf("seed")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("speechConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("speechConfig"),
        speechConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("speechConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("stopSequences"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("stopSequences"),
        Common.getValueByPath(fromObject, arrayOf("stopSequences")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("temperature"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("temperature"),
        Common.getValueByPath(fromObject, arrayOf("temperature")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thinkingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thinkingConfig"),
        Common.getValueByPath(fromObject, arrayOf("thinkingConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topK"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("topK"),
        Common.getValueByPath(fromObject, arrayOf("topK")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topP"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("topP"),
        Common.getValueByPath(fromObject, arrayOf("topP")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("enableEnhancedCivicAnswers")))) {
      throw IllegalArgumentException(
        "enableEnhancedCivicAnswers parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseFormat"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseFormat"),
        Common.getValueByPath(fromObject, arrayOf("responseFormat")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("translationConfig")))) {
      throw IllegalArgumentException(
        "translationConfig parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun googleMapsToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("authConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("authConfig"),
        authConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("authConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("enableWidget"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("enableWidget"),
        Common.getValueByPath(fromObject, arrayOf("enableWidget")),
      )
    }

    return toObject
  }

  internal fun googleSearchToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("searchTypes"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("searchTypes"),
        Common.getValueByPath(fromObject, arrayOf("searchTypes")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("blockingConfidence")))) {
      throw IllegalArgumentException("blockingConfidence parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("excludeDomains")))) {
      throw IllegalArgumentException("excludeDomains parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("timeRangeFilter"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("timeRangeFilter"),
        Common.getValueByPath(fromObject, arrayOf("timeRangeFilter")),
      )
    }

    return toObject
  }

  internal fun imageConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("aspectRatio"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("aspectRatio"),
        Common.getValueByPath(fromObject, arrayOf("aspectRatio")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("imageSize"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("imageSize"),
        Common.getValueByPath(fromObject, arrayOf("imageSize")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("personGeneration")))) {
      throw IllegalArgumentException("personGeneration parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("prominentPeople")))) {
      throw IllegalArgumentException("prominentPeople parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("outputMimeType")))) {
      throw IllegalArgumentException("outputMimeType parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("outputCompressionQuality")))) {
      throw IllegalArgumentException(
        "outputCompressionQuality parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("imageOutputOptions")))) {
      throw IllegalArgumentException("imageOutputOptions parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun imageConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("aspectRatio"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("aspectRatio"),
        Common.getValueByPath(fromObject, arrayOf("aspectRatio")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("imageSize"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("imageSize"),
        Common.getValueByPath(fromObject, arrayOf("imageSize")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("personGeneration"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("personGeneration"),
        Common.getValueByPath(fromObject, arrayOf("personGeneration")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("prominentPeople"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("prominentPeople"),
        Common.getValueByPath(fromObject, arrayOf("prominentPeople")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputMimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("imageOutputOptions", "mimeType"),
        Common.getValueByPath(fromObject, arrayOf("outputMimeType")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputCompressionQuality"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("imageOutputOptions", "compressionQuality"),
        Common.getValueByPath(fromObject, arrayOf("outputCompressionQuality")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("imageOutputOptions"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("imageOutputOptions"),
        Common.getValueByPath(fromObject, arrayOf("imageOutputOptions")),
      )
    }

    return toObject
  }

  internal fun mcpServerToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("name")))) {
      throw IllegalArgumentException(
        "name parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("streamableHttpTransport")))) {
      throw IllegalArgumentException(
        "streamableHttpTransport parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun multiSpeakerVoiceConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("speakerVoiceConfigs"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(speakerVoiceConfigToVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("speakerVoiceConfigs"), result)
    }

    return toObject
  }

  internal fun partToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("codeExecutionResult"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("codeExecutionResult"),
        Common.getValueByPath(fromObject, arrayOf("codeExecutionResult")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("executableCode"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("executableCode"),
        Common.getValueByPath(fromObject, arrayOf("executableCode")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("fileData"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fileData"),
        fileDataToMldev(
          Common.getValueByPath(fromObject, arrayOf("fileData")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionCall"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionCall"),
        functionCallToMldev(
          Common.getValueByPath(fromObject, arrayOf("functionCall")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionResponse"),
        Common.getValueByPath(fromObject, arrayOf("functionResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inlineData"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("inlineData"),
        blobToMldev(
          Common.getValueByPath(fromObject, arrayOf("inlineData")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("text"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("text"),
        Common.getValueByPath(fromObject, arrayOf("text")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thought"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thought"),
        Common.getValueByPath(fromObject, arrayOf("thought")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thoughtSignature"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thoughtSignature"),
        Common.getValueByPath(fromObject, arrayOf("thoughtSignature")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("videoMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("videoMetadata"),
        Common.getValueByPath(fromObject, arrayOf("videoMetadata")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolCall"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolCall"),
        Common.getValueByPath(fromObject, arrayOf("toolCall")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolResponse"),
        Common.getValueByPath(fromObject, arrayOf("toolResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("partMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("partMetadata"),
        Common.getValueByPath(fromObject, arrayOf("partMetadata")),
      )
    }

    return toObject
  }

  internal fun partToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("codeExecutionResult"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("codeExecutionResult"),
        codeExecutionResultToVertex(
          Common.getValueByPath(fromObject, arrayOf("codeExecutionResult")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("executableCode"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("executableCode"),
        executableCodeToVertex(
          Common.getValueByPath(fromObject, arrayOf("executableCode")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("fileData"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fileData"),
        Common.getValueByPath(fromObject, arrayOf("fileData")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionCall"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionCall"),
        Common.getValueByPath(fromObject, arrayOf("functionCall")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionResponse"),
        Common.getValueByPath(fromObject, arrayOf("functionResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inlineData"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("inlineData"),
        Common.getValueByPath(fromObject, arrayOf("inlineData")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("text"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("text"),
        Common.getValueByPath(fromObject, arrayOf("text")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thought"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thought"),
        Common.getValueByPath(fromObject, arrayOf("thought")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thoughtSignature"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thoughtSignature"),
        Common.getValueByPath(fromObject, arrayOf("thoughtSignature")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("videoMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("videoMetadata"),
        Common.getValueByPath(fromObject, arrayOf("videoMetadata")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("toolCall")))) {
      throw IllegalArgumentException(
        "toolCall parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("toolResponse")))) {
      throw IllegalArgumentException(
        "toolResponse parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("partMetadata")))) {
      throw IllegalArgumentException(
        "partMetadata parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun replicatedVoiceConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("mimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mimeType"),
        Common.getValueByPath(fromObject, arrayOf("mimeType")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("voiceSampleAudio"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("voiceSampleAudio"),
        Common.getValueByPath(fromObject, arrayOf("voiceSampleAudio")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("consentAudio")))) {
      throw IllegalArgumentException(
        "consentAudio parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("voiceConsentSignature")))) {
      throw IllegalArgumentException(
        "voiceConsentSignature parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun safetySettingToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("category"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("category"),
        Common.getValueByPath(fromObject, arrayOf("category")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("method")))) {
      throw IllegalArgumentException("method parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("threshold"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("threshold"),
        Common.getValueByPath(fromObject, arrayOf("threshold")),
      )
    }

    return toObject
  }

  internal fun speakerVoiceConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("speaker"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("speaker"),
        Common.getValueByPath(fromObject, arrayOf("speaker")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("voiceConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("voiceConfig"),
        voiceConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("voiceConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    return toObject
  }

  internal fun speechConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("voiceConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("voiceConfig"),
        voiceConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("voiceConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("languageCode"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("languageCode"),
        Common.getValueByPath(fromObject, arrayOf("languageCode")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("multiSpeakerVoiceConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("multiSpeakerVoiceConfig"),
        multiSpeakerVoiceConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("multiSpeakerVoiceConfig"))
            as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    return toObject
  }

  internal fun toolConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("retrievalConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("retrievalConfig"),
        Common.getValueByPath(fromObject, arrayOf("retrievalConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionCallingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionCallingConfig"),
        functionCallingConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("functionCallingConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("includeServerSideToolInvocations"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("includeServerSideToolInvocations"),
        Common.getValueByPath(fromObject, arrayOf("includeServerSideToolInvocations")),
      )
    }

    return toObject
  }

  internal fun toolConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("retrievalConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("retrievalConfig"),
        Common.getValueByPath(fromObject, arrayOf("retrievalConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionCallingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionCallingConfig"),
        Common.getValueByPath(fromObject, arrayOf("functionCallingConfig")),
      )
    }

    if (
      !Common.isZero(Common.getValueByPath(fromObject, arrayOf("includeServerSideToolInvocations")))
    ) {
      throw IllegalArgumentException(
        "includeServerSideToolInvocations parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun toolToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("retrieval")))) {
      throw IllegalArgumentException("retrieval parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("computerUse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("computerUse"),
        Common.getValueByPath(fromObject, arrayOf("computerUse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("fileSearch"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fileSearch"),
        Common.getValueByPath(fromObject, arrayOf("fileSearch")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("googleSearch"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("googleSearch"),
        googleSearchToMldev(
          Common.getValueByPath(fromObject, arrayOf("googleSearch")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("googleMaps"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("googleMaps"),
        googleMapsToMldev(
          Common.getValueByPath(fromObject, arrayOf("googleMaps")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("codeExecution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("codeExecution"),
        Common.getValueByPath(fromObject, arrayOf("codeExecution")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("enterpriseWebSearch")))) {
      throw IllegalArgumentException(
        "enterpriseWebSearch parameter is not supported in Gemini API."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionDeclarations"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionDeclarations"),
        Common.getValueByPath(fromObject, arrayOf("functionDeclarations")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("googleSearchRetrieval"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("googleSearchRetrieval"),
        Common.getValueByPath(fromObject, arrayOf("googleSearchRetrieval")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("parallelAiSearch")))) {
      throw IllegalArgumentException("parallelAiSearch parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("urlContext"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("urlContext"),
        Common.getValueByPath(fromObject, arrayOf("urlContext")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mcpServers"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mcpServers"),
        Common.getValueByPath(fromObject, arrayOf("mcpServers")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("exaAiSearch")))) {
      throw IllegalArgumentException("exaAiSearch parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun toolToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("retrieval"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("retrieval"),
        Common.getValueByPath(fromObject, arrayOf("retrieval")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("computerUse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("computerUse"),
        computerUseToVertex(
          Common.getValueByPath(fromObject, arrayOf("computerUse")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("fileSearch")))) {
      throw IllegalArgumentException(
        "fileSearch parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("googleSearch"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("googleSearch"),
        Common.getValueByPath(fromObject, arrayOf("googleSearch")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("googleMaps"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("googleMaps"),
        Common.getValueByPath(fromObject, arrayOf("googleMaps")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("codeExecution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("codeExecution"),
        Common.getValueByPath(fromObject, arrayOf("codeExecution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("enterpriseWebSearch"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("enterpriseWebSearch"),
        Common.getValueByPath(fromObject, arrayOf("enterpriseWebSearch")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionDeclarations"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionDeclarations"),
        Common.getValueByPath(fromObject, arrayOf("functionDeclarations")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("googleSearchRetrieval"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("googleSearchRetrieval"),
        Common.getValueByPath(fromObject, arrayOf("googleSearchRetrieval")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("parallelAiSearch"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("parallelAiSearch"),
        Common.getValueByPath(fromObject, arrayOf("parallelAiSearch")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("urlContext"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("urlContext"),
        Common.getValueByPath(fromObject, arrayOf("urlContext")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mcpServers"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(mcpServerToVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("mcpServers"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("exaAiSearch"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("exaAiSearch"),
        Common.getValueByPath(fromObject, arrayOf("exaAiSearch")),
      )
    }

    return toObject
  }

  internal fun voiceConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("replicatedVoiceConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("replicatedVoiceConfig"),
        replicatedVoiceConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("replicatedVoiceConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("prebuiltVoiceConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("prebuiltVoiceConfig"),
        Common.getValueByPath(fromObject, arrayOf("prebuiltVoiceConfig")),
      )
    }

    return toObject
  }

  internal suspend fun privateGenerateContent(
    model: String,
    contents: List<Content>,
    config: GenerateContentConfig? = null,
  ): GenerateContentResponse {
    val parameters = GenerateContentParameters(model, contents, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = generateContentParametersToVertex(this.apiClient, parameterMap, null, parameterMap)

      path = Common.formatMap("{model}:generateContent", body["_url"] as? Map<String, Any?>)
    } else {

      body = generateContentParametersToMldev(this.apiClient, parameterMap, null, parameterMap)

      path = Common.formatMap("{model}:generateContent", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val response = apiClient.request("POST", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    if (config?.shouldReturnHttpResponse == true) {
      return GenerateContentResponse(
        sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
      )
    }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = generateContentResponseFromVertex(responseMap, null, parameterMap)
    } else {
      responseMap = generateContentResponseFromMldev(responseMap, null, parameterMap)
    }

    val sdkResponse = Common.mapToDataClass<GenerateContentResponse>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  internal fun privateGenerateContentStream(
    model: String,
    contents: List<Content>,
    config: GenerateContentConfig? = null,
  ): Flow<GenerateContentResponse> {
    val parameters = GenerateContentParameters(model, contents, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = generateContentParametersToVertex(this.apiClient, parameterMap, null, parameterMap)

      path =
        Common.formatMap(
          "{model}:streamGenerateContent?alt=sse",
          body["_url"] as? Map<String, Any?>,
        )
    } else {

      body = generateContentParametersToMldev(this.apiClient, parameterMap, null, parameterMap)

      path =
        Common.formatMap(
          "{model}:streamGenerateContent?alt=sse",
          body["_url"] as? Map<String, Any?>,
        )
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    return apiClient
      .requestStream("POST", path, finalBody, httpOptions = config?.httpOptions)
      .map { jsonString ->
        var responseMap = Common.jsonStringToMap(jsonString)
        if (apiClient.enterprise) {
          responseMap = generateContentResponseFromVertex(responseMap, null, parameterMap)
        } else {
          responseMap = generateContentResponseFromMldev(responseMap, null, parameterMap)
        }
        Common.mapToDataClass<GenerateContentResponse>(responseMap)
      }
  }

  internal suspend fun privateEmbedContent(
    model: String,
    contents: List<Content>? = null,
    content: Content? = null,
    embeddingApiType: EmbeddingApiType? = null,
    config: EmbedContentConfig? = null,
  ): EmbedContentResponse {
    val parameters =
      EmbedContentParametersPrivate(model, contents, content, embeddingApiType, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = embedContentParametersPrivateToVertex(this.apiClient, parameterMap, null, parameterMap)

      val modelName = parameterMap["model"] as? String ?: ""
      val endpointUrl =
        if (Transformers.tIsVertexEmbedContentModel(modelName)) "{model}:embedContent"
        else "{model}:predict"
      path = Common.formatMap(endpointUrl, body["_url"] as? Map<String, Any?>)
    } else {

      body = embedContentParametersPrivateToMldev(this.apiClient, parameterMap, null, parameterMap)

      path = Common.formatMap("{model}:batchEmbedContents", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val response = apiClient.request("POST", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = embedContentResponseFromVertex(responseMap, null, parameterMap)
    } else {
      responseMap = embedContentResponseFromMldev(responseMap, null, parameterMap)
    }

    val sdkResponse = Common.mapToDataClass<EmbedContentResponse>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  /**
   * Counts the number of tokens in the given List<Content> object.
   *
   * @param model The model to use for counting tokens.
   * @param contents The List<Content> to count tokens for.
   * @param config Optional configuration for counting tokens.
   * @return A [CountTokensResponse] object containing the token count.
   */
  suspend fun countTokens(
    model: String,
    contents: List<Content>,
    config: CountTokensConfig? = null,
  ): CountTokensResponse {
    val parameters = CountTokensParameters(model, contents, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = countTokensParametersToVertex(this.apiClient, parameterMap, null, parameterMap)

      path = Common.formatMap("{model}:countTokens", body["_url"] as? Map<String, Any?>)
    } else {

      body = countTokensParametersToMldev(this.apiClient, parameterMap, null, parameterMap)

      path = Common.formatMap("{model}:countTokens", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val response = apiClient.request("POST", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = countTokensResponseFromVertex(responseMap, null, parameterMap)
    } else {
      responseMap = countTokensResponseFromMldev(responseMap, null, parameterMap)
    }

    val sdkResponse = Common.mapToDataClass<CountTokensResponse>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  /**
   * Computes the number of tokens for the given List<Content> object.
   *
   * This method is not supported by the Gemini Developer API.
   *
   * @param model The model to use for computing tokens.
   * @param contents The List<Content> to compute tokens for.
   * @param config Optional configuration for computing tokens.
   * @return A [ComputeTokensResponse] object containing the token information.
   * @throws UnsupportedOperationException if called with a non-Gemini Enterprise Agent Platform
   *   (previously known as Vertex AI) client.
   */
  suspend fun computeTokens(
    model: String,
    contents: List<Content>,
    config: ComputeTokensConfig? = null,
  ): ComputeTokensResponse {
    val parameters = ComputeTokensParameters(model, contents, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = computeTokensParametersToVertex(this.apiClient, parameterMap, null, parameterMap)

      path = Common.formatMap("{model}:computeTokens", body["_url"] as? Map<String, Any?>)
    } else {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini Developer API mode."
      )
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val response = apiClient.request("POST", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = computeTokensResponseFromVertex(responseMap, null, parameterMap)
    } else {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini Developer API mode."
      )
    }

    val sdkResponse = Common.mapToDataClass<ComputeTokensResponse>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  /**
   * Generates content given a GenAI model and a list of content.
   *
   * @param model the name of the GenAI model to use for generation
   * @param contents a list of [Content] to send to the generative model
   * @param config a [GenerateContentConfig] instance that specifies the optional configurations
   * @return a [GenerateContentResponse] instance that contains response contents and other metadata
   */
  suspend fun generateContent(
    model: String,
    contents: List<Content>,
    config: GenerateContentConfig? = null,
  ): GenerateContentResponse {
    return privateGenerateContent(model = model, contents = contents, config = config)
  }

  /**
   * Generates content given a GenAI model and a content object.
   *
   * @param model the name of the GenAI model to use for generation
   * @param content a [Content] to send to the generative model
   * @param config a [GenerateContentConfig] instance that specifies the optional configurations
   * @return a [GenerateContentResponse] instance that contains response contents and other metadata
   */
  suspend fun generateContent(
    model: String,
    content: Content,
    config: GenerateContentConfig? = null,
  ): GenerateContentResponse {
    return privateGenerateContent(model = model, contents = listOf(content), config = config)
  }

  /**
   * Generates content given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use for generation
   * @param text the text string to send to the generative model
   * @param config a [GenerateContentConfig] instance that specifies the optional configurations
   * @return a [GenerateContentResponse] instance that contains response contents and other metadata
   */
  suspend fun generateContent(
    model: String,
    text: String,
    config: GenerateContentConfig? = null,
  ): GenerateContentResponse {
    return privateGenerateContent(
      model = model,
      contents = listOf(Content(parts = listOf(Part(text = text)), role = "user")),
      config = config,
    )
  }

  /**
   * Generates content with streaming support given a GenAI model and a list of content.
   *
   * @param model the name of the GenAI model to use for generation
   * @param contents a list of [Content] to send to the generative model
   * @param config a [GenerateContentConfig] instance that specifies the optional configurations
   * @return a [GenerateContentResponse] instance that contains response contents and other metadata
   */
  fun generateContentStream(
    model: String,
    contents: List<Content>,
    config: GenerateContentConfig? = null,
  ): Flow<GenerateContentResponse> {
    return privateGenerateContentStream(model = model, contents = contents, config = config)
  }

  /**
   * Generates content with streaming support given a GenAI model and a content object.
   *
   * @param model the name of the GenAI model to use for generation
   * @param content a [Content] to send to the generative model
   * @param config a [GenerateContentConfig] instance that specifies the optional configurations
   * @return a [GenerateContentResponse] instance that contains response contents and other metadata
   */
  fun generateContentStream(
    model: String,
    content: Content,
    config: GenerateContentConfig? = null,
  ): Flow<GenerateContentResponse> {
    return privateGenerateContentStream(model = model, contents = listOf(content), config = config)
  }

  /**
   * Generates content with streaming support given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use for generation
   * @param text the text string to send to the generative model
   * @param config a [GenerateContentConfig] instance that specifies the optional configurations
   * @return a [GenerateContentResponse] instance that contains response contents and other metadata
   */
  fun generateContentStream(
    model: String,
    text: String,
    config: GenerateContentConfig? = null,
  ): Flow<GenerateContentResponse> {
    return privateGenerateContentStream(
      model = model,
      contents = listOf(Content(parts = listOf(Part(text = text)), role = "user")),
      config = config,
    )
  }

  /**
   * Calculates embeddings for the given content.
   *
   * @param model the name of the GenAI model to use for embedding
   * @param contents a list of [Content] to embed
   * @param config an [EmbedContentConfig] instance that specifies the optional configurations
   * @return an [EmbedContentResponse] instance that contains response contents and other metadata
   */
  suspend fun embedContent(
    model: String,
    contents: List<Content>,
    config: EmbedContentConfig? = null,
  ): EmbedContentResponse {
    val response =
      if (!apiClient.enterprise) {
        privateEmbedContent(
          model = model,
          contents = contents,
          content = null,
          embeddingApiType = null,
          config = config,
        )
      } else if (Transformers.tIsVertexEmbedContentModel(model)) {
        if (contents.size > 1) {
          throw IllegalArgumentException(
            "The embedContent API for this model only supports one content at a time."
          )
        }
        privateEmbedContent(
          model = model,
          contents = null,
          content = contents.firstOrNull(),
          embeddingApiType = EmbeddingApiType.EMBED_CONTENT,
          config = config,
        )
      } else {
        privateEmbedContent(
          model = model,
          contents = contents,
          content = null,
          embeddingApiType = EmbeddingApiType.PREDICT,
          config = config,
        )
      }

    return response
  }

  /**
   * Calculates embeddings for the given content.
   *
   * @param model the name of the GenAI model to use for embedding
   * @param content a [Content] to embed
   * @param config an [EmbedContentConfig] instance that specifies the optional configurations
   * @return an [EmbedContentResponse] instance that contains response contents and other metadata
   */
  suspend fun embedContent(
    model: String,
    content: Content,
    config: EmbedContentConfig? = null,
  ): EmbedContentResponse {
    return embedContent(model = model, contents = listOf(content), config = config)
  }

  /**
   * Calculates embeddings for the given text string.
   *
   * @param model the name of the GenAI model to use for embedding
   * @param text the text string to embed
   * @param config an [EmbedContentConfig] instance that specifies the optional configurations
   * @return an [EmbedContentResponse] instance that contains response contents and other metadata
   */
  suspend fun embedContent(
    model: String,
    text: String,
    config: EmbedContentConfig? = null,
  ): EmbedContentResponse {
    return embedContent(
      model = model,
      contents = listOf(Content(parts = listOf(Part(text = text)), role = "user")),
      config = config,
    )
  }

  /**
   * Counts the number of tokens in the provided Content object.
   *
   * @param model The model to use for counting tokens.
   * @param content The Content object to count tokens for.
   * @param config Optional configuration for counting tokens.
   * @return A [CountTokensResponse] object containing the token count.
   */
  suspend fun countTokens(
    model: String,
    content: Content,
    config: CountTokensConfig? = null,
  ): CountTokensResponse {
    return countTokens(model = model, contents = listOf(content), config = config)
  }

  /**
   * Counts the number of tokens in the provided text string.
   *
   * @param model The model to use for counting tokens.
   * @param text The text string to count tokens for.
   * @param config Optional configuration for counting tokens.
   * @return A [CountTokensResponse] object containing the token count.
   */
  suspend fun countTokens(
    model: String,
    text: String,
    config: CountTokensConfig? = null,
  ): CountTokensResponse {
    return countTokens(
      model = model,
      contents = listOf(Content(role = "user", parts = listOf(Part(text = text)))),
      config = config,
    )
  }

  /**
   * Computes the number of tokens for the provided Content object.
   *
   * This method is not supported by the Gemini Developer API.
   *
   * @param model The model to use for computing tokens.
   * @param content The Content object to compute tokens for.
   * @param config Optional configuration for computing tokens.
   * @return A [ComputeTokensResponse] object containing the token information.
   * @throws UnsupportedOperationException if called with a non-Gemini Enterprise Agent Platform
   *   (previously known as Vertex AI) client.
   */
  suspend fun computeTokens(
    model: String,
    content: Content,
    config: ComputeTokensConfig? = null,
  ): ComputeTokensResponse {
    return computeTokens(model = model, contents = listOf(content), config = config)
  }

  /**
   * Computes the number of tokens for the provided text string.
   *
   * This method is not supported by the Gemini Developer API.
   *
   * @param model The model to use for computing tokens.
   * @param text The text string to compute tokens for.
   * @param config Optional configuration for computing tokens.
   * @return A [ComputeTokensResponse] object containing the token information.
   * @throws UnsupportedOperationException if called with a non-Gemini Enterprise Agent Platform
   *   (previously known as Vertex AI) client.
   */
  suspend fun computeTokens(
    model: String,
    text: String,
    config: ComputeTokensConfig? = null,
  ): ComputeTokensResponse {
    return computeTokens(
      model = model,
      contents = listOf(Content(role = "user", parts = listOf(Part(text = text)))),
      config = config,
    )
  }
}
