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

import com.google.genai.kotlin.types.AuthToken
import com.google.genai.kotlin.types.CreateAuthTokenConfig
import com.google.genai.kotlin.types.CreateAuthTokenParameters
import com.google.genai.kotlin.types.GenerationConfig
import io.ktor.http.encodeURLQueryComponent
import kotlinx.serialization.serializer

/** Provides methods for managing ephemeral auth tokens. The tokens module is experimental. */
@ExperimentalGenAiApi
class Tokens internal constructor(internal val apiClient: ApiClient) {

  /**
   * Returns a comma-separated list of field masks from a given setup map.
   *
   * @param setup The map to extract field masks from.
   * @return A comma-separated list of field masks.
   */
  internal fun getFieldMasks(setup: Map<String, Any?>): String {
    val fields = mutableListOf<String>()
    for ((key, value) in setup) {
      if (value is Map<*, *> && value.isNotEmpty()) {
        for (subKey in value.keys) {
          fields.add("$key.$subKey")
        }
      } else {
        fields.add(key)
      }
    }
    return fields.joinToString(",")
  }

  /**
   * Converts the bidi setup in the config to the token setup.
   *
   * @param body The request body map.
   * @param config The config of the create auth token request.
   * @return The modified request body map.
   */
  internal fun convertBidiSetupToTokenSetup(
    body: MutableMap<String, Any?>,
    config: CreateAuthTokenConfig?,
  ): MutableMap<String, Any?> {
    var setupForMaskGeneration: Map<String, Any?>? = null
    val bidiVal = body["bidiGenerateContentSetup"] as? Map<*, *>

    val setupMap = bidiVal?.get("setup") as? Map<*, *>
    if (setupMap != null && setupMap.isNotEmpty()) {
      @Suppress("UNCHECKED_CAST") val innerSetup = setupMap as Map<String, Any?>
      body["bidiGenerateContentSetup"] = innerSetup
      setupForMaskGeneration = innerSetup
    } else {
      body.remove("bidiGenerateContentSetup")
    }

    val preExistingFieldMask = body["fieldMask"]
    val preExistingFieldMaskList = mutableListOf<String>()
    if (preExistingFieldMask is List<*>) {
      for (element in preExistingFieldMask) {
        if (element is String) {
          preExistingFieldMaskList.add(element)
        }
      }
    }

    if (setupForMaskGeneration != null) {
      val generatedMaskFromBidi = getFieldMasks(setupForMaskGeneration)
      val lockAdditionalFields = config?.lockAdditionalFields

      if (lockAdditionalFields != null && lockAdditionalFields.isEmpty()) {
        // Case 1: lockAdditionalFields is an empty list. Lock only fields from bidi setup.
        if (generatedMaskFromBidi.isNotEmpty()) {
          body["fieldMask"] = generatedMaskFromBidi
        } else {
          body.remove("fieldMask")
        }
      } else if (
        lockAdditionalFields != null &&
          lockAdditionalFields.isNotEmpty() &&
          preExistingFieldMaskList.isNotEmpty()
      ) {
        // Case 2: Lock fields from bidi setup + additional fields.
        val mappedFieldsFromPreExisting = preExistingFieldMaskList.map { field ->
          if (generationConfigFields.contains(field)) {
            "generationConfig.$field"
          } else {
            field
          }
        }

        val finalMaskParts = mutableListOf<String>()
        if (generatedMaskFromBidi.isNotEmpty()) {
          finalMaskParts.add(generatedMaskFromBidi)
        }
        finalMaskParts.addAll(mappedFieldsFromPreExisting)

        if (finalMaskParts.isNotEmpty()) {
          body["fieldMask"] = finalMaskParts.joinToString(",")
        } else {
          body.remove("fieldMask")
        }
      } else {
        // Case 3: "Lock all fields" (lockAdditionalFields is null)
        body.remove("fieldMask")
      }
    } else {
      // No valid `bidiGenerateContentSetup` found.
      if (preExistingFieldMaskList.isNotEmpty()) {
        body["fieldMask"] = preExistingFieldMaskList.joinToString(",")
      } else {
        body.remove("fieldMask")
      }
    }

    return body
  }

  /**
   * Creates an ephemeral auth token resource.
   *
   * @param config A [CreateAuthTokenConfig] for configuring the create request.
   * @return An [AuthToken] object that contains the info of the created resource.
   */
  @Suppress("UNCHECKED_CAST")
  suspend fun create(config: CreateAuthTokenConfig? = null): AuthToken {
    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in the Gemini Developer client."
      )
    }

    val parameters = CreateAuthTokenParameters(config = config)
    val parameterMap = Common.dataClassToMap(parameters)

    val body = TokensConverters.createAuthTokenParametersToMldev(apiClient, parameterMap, null)

    var path =
      if (body["_url"] != null) {
        Common.formatMap("auth_tokens", body["_url"] as? Map<String, Any?>)
      } else {
        "auth_tokens"
      }
    body.remove("_url")

    val queryParams = body["_query"] as? Map<String, Any?>
    if (queryParams != null) {
      body.remove("_query")
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val transformedBody = convertBidiSetupToTokenSetup(body, config)
    transformedBody.remove("config")

    val finalBody = Common.mapToJsonObject(transformedBody)

    val response = apiClient.request("POST", path, finalBody, httpOptions = config?.httpOptions)
    val responseString = response.body()
    val responseMap = Common.jsonStringToMap(responseString)

    return Common.mapToDataClass<AuthToken>(responseMap)
  }

  companion object {
    private fun camelToSnakeCase(str: String): String {
      val builder = StringBuilder()
      for (i in str.indices) {
        val c = str[i]
        if (c.isUpperCase()) {
          if (i > 0) {
            builder.append('_')
          }
          builder.append(c.lowercaseChar())
        } else {
          builder.append(c)
        }
      }
      return builder.toString()
    }

    /** Set of field names in [GenerationConfig] in both camelCase and snake_case format. */
    internal val generationConfigFields: Set<String> = buildSet {
      val descriptor = serializer<GenerationConfig>().descriptor
      for (i in 0 until descriptor.elementsCount) {
        val name = descriptor.getElementName(i)
        add(name)
        add(camelToSnakeCase(name))
      }
    }
  }
}
