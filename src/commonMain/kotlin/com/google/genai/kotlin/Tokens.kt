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
    /** Set of field names in [GenerationConfig] in both camelCase and snake_case format. */
    internal val generationConfigFields: Set<String> =
      setOf(
        "audioTimestamp",
        "audio_timestamp",
        "audioTranscriptionConfig",
        "audio_transcription_config",
        "candidateCount",
        "candidate_count",
        "enableAffectiveDialog",
        "enable_affective_dialog",
        "enableEnhancedCivicAnswers",
        "enable_enhanced_civic_answers",
        "frequencyPenalty",
        "frequency_penalty",
        "logprobs",
        "maxOutputTokens",
        "max_output_tokens",
        "mediaResolution",
        "media_resolution",
        "modelSelectionConfig",
        "model_selection_config",
        "presencePenalty",
        "presence_penalty",
        "responseFormat",
        "response_format",
        "responseJsonSchema",
        "response_json_schema",
        "responseLogprobs",
        "response_logprobs",
        "responseMimeType",
        "response_mime_type",
        "responseModalities",
        "response_modalities",
        "responseSchema",
        "response_schema",
        "routingConfig",
        "routing_config",
        "seed",
        "speechConfig",
        "speech_config",
        "stopSequences",
        "stop_sequences",
        "temperature",
        "thinkingConfig",
        "thinking_config",
        "topK",
        "top_k",
        "topP",
        "top_p",
        "translationConfig",
        "translation_config",
      )
  }
}
