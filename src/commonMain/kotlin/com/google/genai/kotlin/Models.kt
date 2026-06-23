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

import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.GenerateContentParameters
import com.google.genai.kotlin.types.GenerateContentResponse
import com.google.genai.kotlin.types.HttpResponse
import com.google.genai.kotlin.types.Part
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
      throw IllegalArgumentException(
        "apiKeyConfig parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("authType")))) {
      throw IllegalArgumentException(
        "authType parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("googleServiceAccountConfig")))) {
      throw IllegalArgumentException(
        "googleServiceAccountConfig parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("httpBasicAuthConfig")))) {
      throw IllegalArgumentException(
        "httpBasicAuthConfig parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("oauthConfig")))) {
      throw IllegalArgumentException(
        "oauthConfig parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("oidcConfig")))) {
      throw IllegalArgumentException(
        "oidcConfig parameter is not supported in Gemini API.".toString()
      )
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
      throw IllegalArgumentException(
        "displayName parameter is not supported in Gemini API.".toString()
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
        "id parameter is not supported in Gemini Enterprise Agent Platform.".toString()
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
        "id parameter is not supported in Gemini Enterprise Agent Platform.".toString()
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
      throw IllegalArgumentException(
        "displayName parameter is not supported in Gemini API.".toString()
      )
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
      throw IllegalArgumentException(
        "partialArgs parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("willContinue")))) {
      throw IllegalArgumentException(
        "willContinue parameter is not supported in Gemini API.".toString()
      )
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
        "streamFunctionCallArguments parameter is not supported in Gemini API.".toString()
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
        "modelSelectionConfig parameter is not supported in Gemini API.".toString()
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
          this.apiClient,
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
      throw IllegalArgumentException(
        "audioTimestamp parameter is not supported in Gemini API.".toString()
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
      throw IllegalArgumentException(
        "modelArmorConfig parameter is not supported in Gemini API.".toString()
      )
    }

    Common.getValueByPath(fromObject, arrayOf("serviceTier"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("serviceTier"),
        Common.getValueByPath(fromObject, arrayOf("serviceTier")),
      )
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
          this.apiClient,
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
          .toString()
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
        Transformers.tModel(this.apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
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
        Transformers.tModel(this.apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
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
      throw IllegalArgumentException(
        "blockingConfidence parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("excludeDomains")))) {
      throw IllegalArgumentException(
        "excludeDomains parameter is not supported in Gemini API.".toString()
      )
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
      throw IllegalArgumentException(
        "personGeneration parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("prominentPeople")))) {
      throw IllegalArgumentException(
        "prominentPeople parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("outputMimeType")))) {
      throw IllegalArgumentException(
        "outputMimeType parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("outputCompressionQuality")))) {
      throw IllegalArgumentException(
        "outputCompressionQuality parameter is not supported in Gemini API.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("imageOutputOptions")))) {
      throw IllegalArgumentException(
        "imageOutputOptions parameter is not supported in Gemini API.".toString()
      )
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
        "name parameter is not supported in Gemini Enterprise Agent Platform.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("streamableHttpTransport")))) {
      throw IllegalArgumentException(
        "streamableHttpTransport parameter is not supported in Gemini Enterprise Agent Platform."
          .toString()
      )
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
        "toolCall parameter is not supported in Gemini Enterprise Agent Platform.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("toolResponse")))) {
      throw IllegalArgumentException(
        "toolResponse parameter is not supported in Gemini Enterprise Agent Platform.".toString()
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("partMetadata")))) {
      throw IllegalArgumentException(
        "partMetadata parameter is not supported in Gemini Enterprise Agent Platform.".toString()
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
      throw IllegalArgumentException("method parameter is not supported in Gemini API.".toString())
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
          .toString()
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
      throw IllegalArgumentException(
        "retrieval parameter is not supported in Gemini API.".toString()
      )
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
        "enterpriseWebSearch parameter is not supported in Gemini API.".toString()
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
      throw IllegalArgumentException(
        "parallelAiSearch parameter is not supported in Gemini API.".toString()
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
      Common.setValueByPath(
        toObject,
        arrayOf("mcpServers"),
        Common.getValueByPath(fromObject, arrayOf("mcpServers")),
      )
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
        Common.getValueByPath(fromObject, arrayOf("computerUse")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("fileSearch")))) {
      throw IllegalArgumentException(
        "fileSearch parameter is not supported in Gemini Enterprise Agent Platform.".toString()
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

      path = Common.formatMap("{model}:generateContent", body["_url"] as Map<String, Any?>)
    } else {

      body = generateContentParametersToMldev(this.apiClient, parameterMap, null, parameterMap)
      path = Common.formatMap("{model}:generateContent", body["_url"] as Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val finalBody = Common.mapToJsonObject(body.filterKeys { it != "_url" && it != "_query" })

    if (queryParams != null) {
      path = "$path?${queryParams}"
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

    return sdkResponse.copy(sdkHttpResponse = HttpResponse(headers = headersMap))
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
        Common.formatMap("{model}:streamGenerateContent?alt=sse", body["_url"] as Map<String, Any?>)
    } else {

      body = generateContentParametersToMldev(this.apiClient, parameterMap, null, parameterMap)
      path =
        Common.formatMap("{model}:streamGenerateContent?alt=sse", body["_url"] as Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val finalBody = Common.mapToJsonObject(body.filterKeys { it != "_url" && it != "_query" })

    if (queryParams != null) {
      path = "$path?${queryParams}"
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
}
