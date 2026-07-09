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

import com.google.genai.kotlin.types.CachedContent
import com.google.genai.kotlin.types.CreateCachedContentConfig
import com.google.genai.kotlin.types.CreateCachedContentParameters
import com.google.genai.kotlin.types.DeleteCachedContentConfig
import com.google.genai.kotlin.types.DeleteCachedContentParameters
import com.google.genai.kotlin.types.DeleteCachedContentResponse
import com.google.genai.kotlin.types.GetCachedContentConfig
import com.google.genai.kotlin.types.GetCachedContentParameters
import com.google.genai.kotlin.types.HttpResponse
import com.google.genai.kotlin.types.ListCachedContentsConfig
import com.google.genai.kotlin.types.ListCachedContentsParameters
import com.google.genai.kotlin.types.ListCachedContentsResponse
import com.google.genai.kotlin.types.UpdateCachedContentConfig
import com.google.genai.kotlin.types.UpdateCachedContentParameters

/**
 * Provides methods for managing cached content. Instantiating this class is not required. After
 * instantiating a [Client], access methods through `client.caches` directly.
 */
class Caches internal constructor(internal val apiClient: ApiClient) {

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

  internal fun createCachedContentConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("ttl"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("ttl"),
        Common.getValueByPath(fromObject, arrayOf("ttl")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("expireTime"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("expireTime"),
        Common.getValueByPath(fromObject, arrayOf("expireTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("displayName"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("displayName"),
        Common.getValueByPath(fromObject, arrayOf("displayName")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      val keyArray = Transformers.tContents(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(parentObject, arrayOf("contents"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("systemInstruction"),
        contentToMldev(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tools"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(toolToMldev(item as Map<String, Any?>, toObject))
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
        ),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("kmsKeyName")))) {
      throw IllegalArgumentException("kmsKeyName parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun createCachedContentConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("ttl"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("ttl"),
        Common.getValueByPath(fromObject, arrayOf("ttl")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("expireTime"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("expireTime"),
        Common.getValueByPath(fromObject, arrayOf("expireTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("displayName"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("displayName"),
        Common.getValueByPath(fromObject, arrayOf("displayName")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      val keyArray = Transformers.tContents(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToVertex(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(parentObject, arrayOf("contents"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("systemInstruction"),
        contentToVertex(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tools"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(toolToVertex(item as Map<String, Any?>, toObject))
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
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("kmsKeyName"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("encryption_spec", "kmsKeyName"),
        Common.getValueByPath(fromObject, arrayOf("kmsKeyName")),
      )
    }

    return toObject
  }

  internal fun createCachedContentParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("model"),
        Transformers.tCachesModel(
          this.apiClient,
          Common.getValueByPath(fromObject, arrayOf("model")),
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        createCachedContentConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun createCachedContentParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("model"),
        Transformers.tCachesModel(
          this.apiClient,
          Common.getValueByPath(fromObject, arrayOf("model")),
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        createCachedContentConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun deleteCachedContentParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tCachedContentName(
          this.apiClient,
          Common.getValueByPath(fromObject, arrayOf("name")),
        ),
      )
    }

    return toObject
  }

  internal fun deleteCachedContentParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tCachedContentName(
          this.apiClient,
          Common.getValueByPath(fromObject, arrayOf("name")),
        ),
      )
    }

    return toObject
  }

  internal fun deleteCachedContentResponseFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    return toObject
  }

  internal fun deleteCachedContentResponseFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
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

  internal fun functionCallingConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

  internal fun getCachedContentParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tCachedContentName(
          this.apiClient,
          Common.getValueByPath(fromObject, arrayOf("name")),
        ),
      )
    }

    return toObject
  }

  internal fun getCachedContentParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tCachedContentName(
          this.apiClient,
          Common.getValueByPath(fromObject, arrayOf("name")),
        ),
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

  internal fun listCachedContentsConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("pageSize"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("_query", "pageSize"),
        Common.getValueByPath(fromObject, arrayOf("pageSize")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("pageToken"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("_query", "pageToken"),
        Common.getValueByPath(fromObject, arrayOf("pageToken")),
      )
    }

    return toObject
  }

  internal fun listCachedContentsConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("pageSize"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("_query", "pageSize"),
        Common.getValueByPath(fromObject, arrayOf("pageSize")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("pageToken"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("_query", "pageToken"),
        Common.getValueByPath(fromObject, arrayOf("pageToken")),
      )
    }

    return toObject
  }

  internal fun listCachedContentsParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        listCachedContentsConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun listCachedContentsParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        listCachedContentsConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun listCachedContentsResponseFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("nextPageToken"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("nextPageToken"),
        Common.getValueByPath(fromObject, arrayOf("nextPageToken")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("cachedContents"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("cachedContents"),
        Common.getValueByPath(fromObject, arrayOf("cachedContents")),
      )
    }

    return toObject
  }

  internal fun listCachedContentsResponseFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("nextPageToken"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("nextPageToken"),
        Common.getValueByPath(fromObject, arrayOf("nextPageToken")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("cachedContents"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("cachedContents"),
        Common.getValueByPath(fromObject, arrayOf("cachedContents")),
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

  internal fun toolConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

  internal fun updateCachedContentConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("ttl"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("ttl"),
        Common.getValueByPath(fromObject, arrayOf("ttl")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("expireTime"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("expireTime"),
        Common.getValueByPath(fromObject, arrayOf("expireTime")),
      )
    }

    return toObject
  }

  internal fun updateCachedContentConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("ttl"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("ttl"),
        Common.getValueByPath(fromObject, arrayOf("ttl")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("expireTime"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("expireTime"),
        Common.getValueByPath(fromObject, arrayOf("expireTime")),
      )
    }

    return toObject
  }

  internal fun updateCachedContentParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tCachedContentName(
          this.apiClient,
          Common.getValueByPath(fromObject, arrayOf("name")),
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        updateCachedContentConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun updateCachedContentParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tCachedContentName(
          this.apiClient,
          Common.getValueByPath(fromObject, arrayOf("name")),
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        updateCachedContentConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  /**
   * Creates a cached content resource.
   *
   * @param model The model to use.
   * @param config A [CreateCachedContentConfig] for configuring the create request.
   * @return A [CachedContent] object that contains the info of the created resource.
   */
  suspend fun create(model: String, config: CreateCachedContentConfig? = null): CachedContent {
    val parameters = CreateCachedContentParameters(model, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = createCachedContentParametersToVertex(this.apiClient, parameterMap, null)

      path = Common.formatMap("cachedContents", body["_url"] as? Map<String, Any?>)
    } else {

      body = createCachedContentParametersToMldev(this.apiClient, parameterMap, null)
      path = Common.formatMap("cachedContents", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val finalBody = Common.mapToJsonObject(body.filterKeys { it != "_url" && it != "_query" })

    if (queryParams != null) {
      path = "$path?${queryParams}"
    }

    val response = apiClient.request("POST", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)

    val sdkResponse = Common.mapToDataClass<CachedContent>(responseMap)
    return sdkResponse
  }

  /**
   * Gets a cached content resource.
   *
   * @param name The server-generated resource name.
   * @return A [CachedContent] object.
   */
  suspend fun get(name: String, config: GetCachedContentConfig? = null): CachedContent {
    val parameters = GetCachedContentParameters(name, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = getCachedContentParametersToVertex(this.apiClient, parameterMap, null)

      path = Common.formatMap("{name}", body["_url"] as? Map<String, Any?>)
    } else {

      body = getCachedContentParametersToMldev(this.apiClient, parameterMap, null)
      path = Common.formatMap("{name}", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val finalBody = Common.mapToJsonObject(body.filterKeys { it != "_url" && it != "_query" })

    if (queryParams != null) {
      path = "$path?${queryParams}"
    }

    val response = apiClient.request("GET", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)

    val sdkResponse = Common.mapToDataClass<CachedContent>(responseMap)
    return sdkResponse
  }

  /**
   * Deletes a cached content resource.
   *
   * @param name The server-generated resource name.
   */
  suspend fun delete(
    name: String,
    config: DeleteCachedContentConfig? = null,
  ): DeleteCachedContentResponse {
    val parameters = DeleteCachedContentParameters(name, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = deleteCachedContentParametersToVertex(this.apiClient, parameterMap, null)

      path = Common.formatMap("{name}", body["_url"] as? Map<String, Any?>)
    } else {

      body = deleteCachedContentParametersToMldev(this.apiClient, parameterMap, null)
      path = Common.formatMap("{name}", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val finalBody = Common.mapToJsonObject(body.filterKeys { it != "_url" && it != "_query" })

    if (queryParams != null) {
      path = "$path?${queryParams}"
    }

    val response = apiClient.request("DELETE", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = deleteCachedContentResponseFromVertex(responseMap, null)
    } else {
      responseMap = deleteCachedContentResponseFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<DeleteCachedContentResponse>(responseMap)

    return sdkResponse.copy(sdkHttpResponse = HttpResponse(headers = headersMap))
  }

  /**
   * Updates a cached content resource.
   *
   * @param name The server-generated resource name.
   * @param config An [UpdateCachedContentConfig] for configuring the update request.
   * @return A [CachedContent] object.
   */
  suspend fun update(name: String, config: UpdateCachedContentConfig? = null): CachedContent {
    val parameters = UpdateCachedContentParameters(name, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = updateCachedContentParametersToVertex(this.apiClient, parameterMap, null)

      path = Common.formatMap("{name}", body["_url"] as? Map<String, Any?>)
    } else {

      body = updateCachedContentParametersToMldev(this.apiClient, parameterMap, null)
      path = Common.formatMap("{name}", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val finalBody = Common.mapToJsonObject(body.filterKeys { it != "_url" && it != "_query" })

    if (queryParams != null) {
      path = "$path?${queryParams}"
    }

    val response = apiClient.request("PATCH", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)

    val sdkResponse = Common.mapToDataClass<CachedContent>(responseMap)
    return sdkResponse
  }

  internal suspend fun privateList(
    config: ListCachedContentsConfig? = null
  ): ListCachedContentsResponse {
    val parameters = ListCachedContentsParameters(config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = listCachedContentsParametersToVertex(parameterMap, null)

      path = Common.formatMap("cachedContents", body["_url"] as? Map<String, Any?>)
    } else {

      body = listCachedContentsParametersToMldev(parameterMap, null)
      path = Common.formatMap("cachedContents", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val finalBody = Common.mapToJsonObject(body.filterKeys { it != "_url" && it != "_query" })

    if (queryParams != null) {
      path = "$path?${queryParams}"
    }

    val response = apiClient.request("GET", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = listCachedContentsResponseFromVertex(responseMap, null)
    } else {
      responseMap = listCachedContentsResponseFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<ListCachedContentsResponse>(responseMap)

    return sdkResponse.copy(sdkHttpResponse = HttpResponse(headers = headersMap))
  }
}
