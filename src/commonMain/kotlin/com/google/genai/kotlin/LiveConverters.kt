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

internal object LiveConverters {

  internal fun authConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

  internal fun codeExecutionResultToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

  internal fun computerUseToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

  internal fun contentToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("parts"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(partToMldev(item as Map<String, Any?>, toObject))
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
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("parts"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(partToVertex(item as Map<String, Any?>, toObject))
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

  internal fun generationConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("authConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("authConfig"),
        authConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("authConfig")) as Map<String, Any?>,
          toObject,
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

  internal fun liveClientContentToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("turns"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("turns"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("turnComplete"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("turnComplete"),
        Common.getValueByPath(fromObject, arrayOf("turnComplete")),
      )
    }

    return toObject
  }

  internal fun liveClientContentToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("turns"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToVertex(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("turns"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("turnComplete"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("turnComplete"),
        Common.getValueByPath(fromObject, arrayOf("turnComplete")),
      )
    }

    return toObject
  }

  internal fun liveClientMessageToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("setup"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("setup"),
        liveClientSetupToMldev(
          Common.getValueByPath(fromObject, arrayOf("setup")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("clientContent"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("clientContent"),
        liveClientContentToMldev(
          Common.getValueByPath(fromObject, arrayOf("clientContent")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("realtimeInput"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("realtimeInput"),
        liveClientRealtimeInputToMldev(
          Common.getValueByPath(fromObject, arrayOf("realtimeInput")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolResponse"),
        Common.getValueByPath(fromObject, arrayOf("toolResponse")),
      )
    }

    return toObject
  }

  internal fun liveClientMessageToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("setup"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("setup"),
        liveClientSetupToVertex(
          Common.getValueByPath(fromObject, arrayOf("setup")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("clientContent"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("clientContent"),
        liveClientContentToVertex(
          Common.getValueByPath(fromObject, arrayOf("clientContent")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("realtimeInput"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("realtimeInput"),
        Common.getValueByPath(fromObject, arrayOf("realtimeInput")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolResponse"),
        Common.getValueByPath(fromObject, arrayOf("toolResponse")),
      )
    }

    return toObject
  }

  internal fun liveClientRealtimeInputToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("mediaChunks"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(blobToMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("mediaChunks"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("activityStart"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("activityStart"),
        Common.getValueByPath(fromObject, arrayOf("activityStart")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("activityEnd"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("activityEnd"),
        Common.getValueByPath(fromObject, arrayOf("activityEnd")),
      )
    }

    return toObject
  }

  internal fun liveClientSetupToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("model"),
        Common.getValueByPath(fromObject, arrayOf("model")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("generationConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("generationConfig"),
        Common.getValueByPath(fromObject, arrayOf("generationConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("systemInstruction"),
        contentToMldev(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tools"))?.let { node ->
      val keyArray = Transformers.tTools(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(toolToMldev(Transformers.tTool(item) as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("tools"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("sessionResumption"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sessionResumption"),
        sessionResumptionConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("sessionResumption")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contextWindowCompression"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("contextWindowCompression"),
        Common.getValueByPath(fromObject, arrayOf("contextWindowCompression")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inputAudioTranscription"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("inputAudioTranscription"),
        Common.getValueByPath(fromObject, arrayOf("inputAudioTranscription")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputAudioTranscription"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("outputAudioTranscription"),
        Common.getValueByPath(fromObject, arrayOf("outputAudioTranscription")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("proactivity"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("proactivity"),
        Common.getValueByPath(fromObject, arrayOf("proactivity")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("explicitVadSignal")))) {
      throw IllegalArgumentException("explicitVadSignal parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("avatarConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("avatarConfig"),
        Common.getValueByPath(fromObject, arrayOf("avatarConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("safetySettings"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(safetySettingToMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("safetySettings"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("historyConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("historyConfig"),
        Common.getValueByPath(fromObject, arrayOf("historyConfig")),
      )
    }

    return toObject
  }

  internal fun liveClientSetupToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("model"),
        Common.getValueByPath(fromObject, arrayOf("model")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("generationConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("generationConfig"),
        generationConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("generationConfig")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("systemInstruction"),
        contentToVertex(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tools"))?.let { node ->
      val keyArray = Transformers.tTools(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(toolToVertex(Transformers.tTool(item) as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("tools"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("sessionResumption"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sessionResumption"),
        Common.getValueByPath(fromObject, arrayOf("sessionResumption")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contextWindowCompression"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("contextWindowCompression"),
        Common.getValueByPath(fromObject, arrayOf("contextWindowCompression")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inputAudioTranscription"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("inputAudioTranscription"),
        Common.getValueByPath(fromObject, arrayOf("inputAudioTranscription")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputAudioTranscription"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("outputAudioTranscription"),
        Common.getValueByPath(fromObject, arrayOf("outputAudioTranscription")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("proactivity"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("proactivity"),
        Common.getValueByPath(fromObject, arrayOf("proactivity")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("explicitVadSignal"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("explicitVadSignal"),
        Common.getValueByPath(fromObject, arrayOf("explicitVadSignal")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("avatarConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("avatarConfig"),
        Common.getValueByPath(fromObject, arrayOf("avatarConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("safetySettings"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("safetySettings"),
        Common.getValueByPath(fromObject, arrayOf("safetySettings")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("historyConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("historyConfig"),
        Common.getValueByPath(fromObject, arrayOf("historyConfig")),
      )
    }

    return toObject
  }

  internal fun liveConnectConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("responseModalities"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "responseModalities"),
        Common.getValueByPath(fromObject, arrayOf("responseModalities")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("temperature"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "temperature"),
        Common.getValueByPath(fromObject, arrayOf("temperature")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topP"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "topP"),
        Common.getValueByPath(fromObject, arrayOf("topP")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topK"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "topK"),
        Common.getValueByPath(fromObject, arrayOf("topK")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("maxOutputTokens"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "maxOutputTokens"),
        Common.getValueByPath(fromObject, arrayOf("maxOutputTokens")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("seed"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "seed"),
        Common.getValueByPath(fromObject, arrayOf("seed")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("speechConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "speechConfig"),
        Transformers.tLiveSpeechConfig(Common.getValueByPath(fromObject, arrayOf("speechConfig"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thinkingConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "thinkingConfig"),
        Common.getValueByPath(fromObject, arrayOf("thinkingConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("enableAffectiveDialog"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "enableAffectiveDialog"),
        Common.getValueByPath(fromObject, arrayOf("enableAffectiveDialog")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "systemInstruction"),
        contentToMldev(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tools"))?.let { node ->
      val keyArray = Transformers.tTools(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(toolToMldev(Transformers.tTool(item) as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(parentObject, arrayOf("setup", "tools"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("sessionResumption"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "sessionResumption"),
        sessionResumptionConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("sessionResumption")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inputAudioTranscription"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "inputAudioTranscription"),
        Common.getValueByPath(fromObject, arrayOf("inputAudioTranscription")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputAudioTranscription"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "outputAudioTranscription"),
        Common.getValueByPath(fromObject, arrayOf("outputAudioTranscription")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("realtimeInputConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "realtimeInputConfig"),
        Common.getValueByPath(fromObject, arrayOf("realtimeInputConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contextWindowCompression"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "contextWindowCompression"),
        Common.getValueByPath(fromObject, arrayOf("contextWindowCompression")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("proactivity"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "proactivity"),
        Common.getValueByPath(fromObject, arrayOf("proactivity")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("explicitVadSignal")))) {
      throw IllegalArgumentException("explicitVadSignal parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("avatarConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "avatarConfig"),
        Common.getValueByPath(fromObject, arrayOf("avatarConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("safetySettings"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(safetySettingToMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(parentObject, arrayOf("setup", "safetySettings"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("translationConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "translationConfig"),
        Common.getValueByPath(fromObject, arrayOf("translationConfig")),
      )
    }

    return toObject
  }

  internal fun liveConnectConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("responseModalities"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "responseModalities"),
        Common.getValueByPath(fromObject, arrayOf("responseModalities")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("temperature"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "temperature"),
        Common.getValueByPath(fromObject, arrayOf("temperature")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topP"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "topP"),
        Common.getValueByPath(fromObject, arrayOf("topP")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topK"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "topK"),
        Common.getValueByPath(fromObject, arrayOf("topK")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("maxOutputTokens"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "maxOutputTokens"),
        Common.getValueByPath(fromObject, arrayOf("maxOutputTokens")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("seed"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "seed"),
        Common.getValueByPath(fromObject, arrayOf("seed")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("speechConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "speechConfig"),
        speechConfigToVertex(
          Transformers.tLiveSpeechConfig(Common.getValueByPath(fromObject, arrayOf("speechConfig")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thinkingConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "thinkingConfig"),
        Common.getValueByPath(fromObject, arrayOf("thinkingConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("enableAffectiveDialog"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "generationConfig", "enableAffectiveDialog"),
        Common.getValueByPath(fromObject, arrayOf("enableAffectiveDialog")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "systemInstruction"),
        contentToVertex(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tools"))?.let { node ->
      val keyArray = Transformers.tTools(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(toolToVertex(Transformers.tTool(item) as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(parentObject, arrayOf("setup", "tools"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("sessionResumption"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "sessionResumption"),
        Common.getValueByPath(fromObject, arrayOf("sessionResumption")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inputAudioTranscription"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "inputAudioTranscription"),
        Common.getValueByPath(fromObject, arrayOf("inputAudioTranscription")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputAudioTranscription"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "outputAudioTranscription"),
        Common.getValueByPath(fromObject, arrayOf("outputAudioTranscription")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("realtimeInputConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "realtimeInputConfig"),
        Common.getValueByPath(fromObject, arrayOf("realtimeInputConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contextWindowCompression"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "contextWindowCompression"),
        Common.getValueByPath(fromObject, arrayOf("contextWindowCompression")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("proactivity"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "proactivity"),
        Common.getValueByPath(fromObject, arrayOf("proactivity")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("explicitVadSignal"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "explicitVadSignal"),
        Common.getValueByPath(fromObject, arrayOf("explicitVadSignal")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("avatarConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "avatarConfig"),
        Common.getValueByPath(fromObject, arrayOf("avatarConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("safetySettings"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("setup", "safetySettings"),
        Common.getValueByPath(fromObject, arrayOf("safetySettings")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("translationConfig")))) {
      throw IllegalArgumentException(
        "translationConfig parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun liveConnectParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("setup", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("config"),
        liveConnectConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    return toObject
  }

  internal fun liveConnectParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("setup", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("config"),
        liveConnectConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    return toObject
  }

  internal fun liveMusicConnectParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    return toObject
  }

  internal fun liveMusicConnectParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    return toObject
  }

  internal fun liveMusicSetConfigParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    return toObject
  }

  internal fun liveMusicSetConfigParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    return toObject
  }

  internal fun liveMusicSetWeightedPromptsParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    return toObject
  }

  internal fun liveMusicSetWeightedPromptsParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    return toObject
  }

  internal fun liveSendRealtimeInputParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("media"))?.let { node ->
      val keyArray = Transformers.tBlobs(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(blobToMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("mediaChunks"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("audio"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audio"),
        blobToMldev(
          Transformers.tAudioBlob(Common.getValueByPath(fromObject, arrayOf("audio")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("audioStreamEnd"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audioStreamEnd"),
        Common.getValueByPath(fromObject, arrayOf("audioStreamEnd")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("video"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("video"),
        blobToMldev(
          Transformers.tImageBlob(Common.getValueByPath(fromObject, arrayOf("video")))
            as Map<String, Any?>,
          toObject,
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

    Common.getValueByPath(fromObject, arrayOf("activityStart"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("activityStart"),
        Common.getValueByPath(fromObject, arrayOf("activityStart")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("activityEnd"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("activityEnd"),
        Common.getValueByPath(fromObject, arrayOf("activityEnd")),
      )
    }

    return toObject
  }

  internal fun liveSendRealtimeInputParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("media"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaChunks"),
        Transformers.tBlobs(Common.getValueByPath(fromObject, arrayOf("media"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("audio"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audio"),
        Transformers.tAudioBlob(Common.getValueByPath(fromObject, arrayOf("audio"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("audioStreamEnd"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audioStreamEnd"),
        Common.getValueByPath(fromObject, arrayOf("audioStreamEnd")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("video"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("video"),
        Transformers.tImageBlob(Common.getValueByPath(fromObject, arrayOf("video"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("text"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("text"),
        Common.getValueByPath(fromObject, arrayOf("text")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("activityStart"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("activityStart"),
        Common.getValueByPath(fromObject, arrayOf("activityStart")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("activityEnd"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("activityEnd"),
        Common.getValueByPath(fromObject, arrayOf("activityEnd")),
      )
    }

    return toObject
  }

  internal fun liveServerMessageFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("setupComplete"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("setupComplete"),
        Common.getValueByPath(fromObject, arrayOf("setupComplete")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("serverContent"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("serverContent"),
        Common.getValueByPath(fromObject, arrayOf("serverContent")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolCall"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolCall"),
        Common.getValueByPath(fromObject, arrayOf("toolCall")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolCallCancellation"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolCallCancellation"),
        Common.getValueByPath(fromObject, arrayOf("toolCallCancellation")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("usageMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("usageMetadata"),
        Common.getValueByPath(fromObject, arrayOf("usageMetadata")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("goAway"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("goAway"),
        Common.getValueByPath(fromObject, arrayOf("goAway")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("sessionResumptionUpdate"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sessionResumptionUpdate"),
        Common.getValueByPath(fromObject, arrayOf("sessionResumptionUpdate")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("voiceActivityDetectionSignal"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("voiceActivityDetectionSignal"),
        Common.getValueByPath(fromObject, arrayOf("voiceActivityDetectionSignal")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("voiceActivity"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("voiceActivity"),
        voiceActivityFromMldev(
          Common.getValueByPath(fromObject, arrayOf("voiceActivity")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    return toObject
  }

  internal fun liveServerMessageFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("setupComplete"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("setupComplete"),
        Common.getValueByPath(fromObject, arrayOf("setupComplete")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("serverContent"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("serverContent"),
        Common.getValueByPath(fromObject, arrayOf("serverContent")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolCall"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolCall"),
        Common.getValueByPath(fromObject, arrayOf("toolCall")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolCallCancellation"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolCallCancellation"),
        Common.getValueByPath(fromObject, arrayOf("toolCallCancellation")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("usageMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("usageMetadata"),
        usageMetadataFromVertex(
          Common.getValueByPath(fromObject, arrayOf("usageMetadata")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("goAway"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("goAway"),
        Common.getValueByPath(fromObject, arrayOf("goAway")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("sessionResumptionUpdate"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sessionResumptionUpdate"),
        Common.getValueByPath(fromObject, arrayOf("sessionResumptionUpdate")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("voiceActivityDetectionSignal"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("voiceActivityDetectionSignal"),
        Common.getValueByPath(fromObject, arrayOf("voiceActivityDetectionSignal")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("voiceActivity"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("voiceActivity"),
        voiceActivityFromVertex(
          Common.getValueByPath(fromObject, arrayOf("voiceActivity")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    return toObject
  }

  internal fun mcpServerToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("speakerVoiceConfigs"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(speakerVoiceConfigToVertex(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("speakerVoiceConfigs"), result)
    }

    return toObject
  }

  internal fun partToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

  internal fun sessionResumptionConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("handle"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("handle"),
        Common.getValueByPath(fromObject, arrayOf("handle")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("transparent")))) {
      throw IllegalArgumentException("transparent parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun speakerVoiceConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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
        ),
      )
    }

    return toObject
  }

  internal fun speechConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("voiceConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("voiceConfig"),
        voiceConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("voiceConfig")) as Map<String, Any?>,
          toObject,
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
        ),
      )
    }

    return toObject
  }

  internal fun toolToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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
          result.add(mcpServerToVertex(item as Map<String, Any?>, toObject))
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

  internal fun usageMetadataFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("promptTokenCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("promptTokenCount"),
        Common.getValueByPath(fromObject, arrayOf("promptTokenCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("cachedContentTokenCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("cachedContentTokenCount"),
        Common.getValueByPath(fromObject, arrayOf("cachedContentTokenCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("candidatesTokenCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseTokenCount"),
        Common.getValueByPath(fromObject, arrayOf("candidatesTokenCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolUsePromptTokenCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolUsePromptTokenCount"),
        Common.getValueByPath(fromObject, arrayOf("toolUsePromptTokenCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thoughtsTokenCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thoughtsTokenCount"),
        Common.getValueByPath(fromObject, arrayOf("thoughtsTokenCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("totalTokenCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("totalTokenCount"),
        Common.getValueByPath(fromObject, arrayOf("totalTokenCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("promptTokensDetails"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("promptTokensDetails"),
        Common.getValueByPath(fromObject, arrayOf("promptTokensDetails")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("cacheTokensDetails"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("cacheTokensDetails"),
        Common.getValueByPath(fromObject, arrayOf("cacheTokensDetails")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("candidatesTokensDetails"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseTokensDetails"),
        Common.getValueByPath(fromObject, arrayOf("candidatesTokensDetails")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolUsePromptTokensDetails"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolUsePromptTokensDetails"),
        Common.getValueByPath(fromObject, arrayOf("toolUsePromptTokensDetails")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("trafficType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("trafficType"),
        Common.getValueByPath(fromObject, arrayOf("trafficType")),
      )
    }

    return toObject
  }

  internal fun voiceActivityFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("type"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("voiceActivityType"),
        Common.getValueByPath(fromObject, arrayOf("type")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("audioOffset"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audioOffset"),
        Common.getValueByPath(fromObject, arrayOf("audioOffset")),
      )
    }

    return toObject
  }

  internal fun voiceActivityFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("type"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("voiceActivityType"),
        Common.getValueByPath(fromObject, arrayOf("type")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("audioOffset"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audioOffset"),
        Common.getValueByPath(fromObject, arrayOf("audioOffset")),
      )
    }

    return toObject
  }

  internal fun voiceConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("replicatedVoiceConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("replicatedVoiceConfig"),
        replicatedVoiceConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("replicatedVoiceConfig")) as Map<String, Any?>,
          toObject,
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
}
