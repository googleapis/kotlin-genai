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

@file:Suppress(
  "UNCHECKED_CAST",
  "UNUSED_PARAMETER",
  "UNUSED_ANONYMOUS_PARAMETER",
  "UNUSED_VARIABLE",
)

package com.google.genai.kotlin

internal object TokensConverters {

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

  internal fun createAuthTokenConfigToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("expireTime"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("expireTime"),
        Common.getValueByPath(fromObject, arrayOf("expireTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("newSessionExpireTime"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("newSessionExpireTime"),
        Common.getValueByPath(fromObject, arrayOf("newSessionExpireTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("uses"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("uses"),
        Common.getValueByPath(fromObject, arrayOf("uses")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("liveConnectConstraints"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("bidiGenerateContentSetup"),
        liveConnectConstraintsToMldev(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("liveConnectConstraints")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("lockAdditionalFields"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("fieldMask"),
        Common.getValueByPath(fromObject, arrayOf("lockAdditionalFields")),
      )
    }

    return toObject
  }

  internal fun createAuthTokenParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("config"),
        createAuthTokenConfigToMldev(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    return toObject
  }

  internal fun createAuthTokenParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("config")))) {
      throw IllegalArgumentException(
        "config parameter is not supported in Gemini Enterprise Agent Platform."
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

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("groundingTypes")))) {
      throw IllegalArgumentException("groundingTypes parameter is not supported in Gemini API.")
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

  internal fun liveConnectConstraintsToMldev(
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

    Common.getValueByPath(fromObject, arrayOf("audioTranscription"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audioTranscription"),
        Common.getValueByPath(fromObject, arrayOf("audioTranscription")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mediaProcessing"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaProcessing"),
        Common.getValueByPath(fromObject, arrayOf("mediaProcessing")),
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
}
