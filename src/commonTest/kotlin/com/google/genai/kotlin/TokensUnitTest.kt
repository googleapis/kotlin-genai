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
import com.google.genai.kotlin.types.LiveConnectConstraints
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalGenAiApi::class)
class TokensUnitTest {

  private val apiClient = ApiClient(apiKey = "test-api-key")
  private val tokens = Tokens(apiClient)

  @Test
  fun testGetFieldMasks_flatMap() {
    val setup = mapOf<String, Any?>("model" to "gemini-2.5-flash")
    val mask = tokens.getFieldMasks(setup)
    assertEquals("model", mask)
  }

  @Test
  fun testGetFieldMasks_nestedMap() {
    val setup =
      mapOf<String, Any?>(
        "model" to "models/gemini-live-2.5-flash-preview",
        "generationConfig" to
          mapOf<String, Any?>("responseModalities" to listOf("TEXT"), "temperature" to 0.7),
      )
    val mask = tokens.getFieldMasks(setup)
    assertEquals("model,generationConfig.responseModalities,generationConfig.temperature", mask)
  }

  @Test
  fun testConvertBidiSetupToTokenSetup_emptyLockAdditionalFields() {
    val config =
      CreateAuthTokenConfig(
        uses = 2,
        liveConnectConstraints = LiveConnectConstraints(model = "gemini-live-2.5-flash-preview"),
        lockAdditionalFields = emptyList(),
      )
    val body =
      mutableMapOf<String, Any?>(
        "uses" to 2,
        "bidiGenerateContentSetup" to
          mapOf(
            "setup" to
              mapOf(
                "model" to "models/gemini-live-2.5-flash-preview",
                "generationConfig" to
                  mapOf("responseModalities" to listOf("TEXT"), "temperature" to 0.7),
              )
          ),
        "fieldMask" to emptyList<String>(),
      )

    val transformed = tokens.convertBidiSetupToTokenSetup(body, config)

    val innerSetup = transformed["bidiGenerateContentSetup"] as? Map<*, *>
    assertEquals("models/gemini-live-2.5-flash-preview", innerSetup?.get("model"))
    assertEquals(
      "model,generationConfig.responseModalities,generationConfig.temperature",
      transformed["fieldMask"],
    )
  }

  @Test
  fun testConvertBidiSetupToTokenSetup_lockAdditionalFields() {
    val config =
      CreateAuthTokenConfig(
        uses = 2,
        liveConnectConstraints = LiveConnectConstraints(model = "gemini-live-2.5-flash-preview"),
        lockAdditionalFields = listOf("top_k", "tools"),
      )
    val body =
      mutableMapOf<String, Any?>(
        "uses" to 2,
        "bidiGenerateContentSetup" to
          mapOf(
            "setup" to
              mapOf(
                "model" to "models/gemini-live-2.5-flash-preview",
                "generationConfig" to
                  mapOf("responseModalities" to listOf("TEXT"), "temperature" to 0.7),
              )
          ),
        "fieldMask" to listOf("top_k", "tools"),
      )

    val transformed = tokens.convertBidiSetupToTokenSetup(body, config)

    assertEquals(
      "model,generationConfig.responseModalities,generationConfig.temperature,generationConfig.top_k,tools",
      transformed["fieldMask"],
    )
  }

  @Test
  fun testConvertBidiSetupToTokenSetup_dynamicGenerationConfigFields() {
    val config =
      CreateAuthTokenConfig(
        uses = 1,
        liveConnectConstraints = LiveConnectConstraints(model = "gemini-live-2.5-flash-preview"),
        lockAdditionalFields =
          listOf(
            "thinkingConfig",
            "candidate_count",
            "frequencyPenalty",
            "presence_penalty",
            "custom_non_generation_field",
          ),
      )
    val body =
      mutableMapOf<String, Any?>(
        "bidiGenerateContentSetup" to
          mapOf("setup" to mapOf("model" to "models/gemini-live-2.5-flash-preview")),
        "fieldMask" to
          listOf(
            "thinkingConfig",
            "candidate_count",
            "frequencyPenalty",
            "presence_penalty",
            "custom_non_generation_field",
          ),
      )

    val transformed = tokens.convertBidiSetupToTokenSetup(body, config)

    assertEquals(
      "model,generationConfig.thinkingConfig,generationConfig.candidate_count,generationConfig.frequencyPenalty,generationConfig.presence_penalty,custom_non_generation_field",
      transformed["fieldMask"],
    )
  }

  @Test
  fun testConvertBidiSetupToTokenSetup_globalLock() {
    val config =
      CreateAuthTokenConfig(
        uses = 2,
        liveConnectConstraints = LiveConnectConstraints(model = "gemini-live-2.5-flash-preview"),
      )
    val body =
      mutableMapOf<String, Any?>(
        "uses" to 2,
        "bidiGenerateContentSetup" to
          mapOf(
            "setup" to
              mapOf(
                "model" to "models/gemini-live-2.5-flash-preview",
                "generationConfig" to
                  mapOf("responseModalities" to listOf("TEXT"), "temperature" to 0.7),
              )
          ),
      )

    val transformed = tokens.convertBidiSetupToTokenSetup(body, config)

    assertNull(transformed["fieldMask"])
    val innerSetup = transformed["bidiGenerateContentSetup"] as? Map<*, *>
    assertEquals("models/gemini-live-2.5-flash-preview", innerSetup?.get("model"))
  }

  @Test
  fun testConvertBidiSetupToTokenSetup_noBidiSetupWithLockFields() {
    val config = CreateAuthTokenConfig(lockAdditionalFields = listOf("output_audio_transcription"))
    val body = mutableMapOf<String, Any?>("fieldMask" to listOf("output_audio_transcription"))

    val transformed = tokens.convertBidiSetupToTokenSetup(body, config)

    assertEquals("output_audio_transcription", transformed["fieldMask"])
    assertNull(transformed["bidiGenerateContentSetup"])
  }

  @Test
  fun testConvertBidiSetupToTokenSetup_noBidiSetupNoLockFields() {
    val config = CreateAuthTokenConfig()
    val body = mutableMapOf<String, Any?>()

    val transformed = tokens.convertBidiSetupToTokenSetup(body, config)

    assertNull(transformed["fieldMask"])
    assertNull(transformed["bidiGenerateContentSetup"])
  }

  @Test
  fun testConvertBidiSetupToTokenSetup_emptySetupRemoved() {
    val config =
      CreateAuthTokenConfig(
        lockAdditionalFields = listOf("output_audio_transcription"),
        liveConnectConstraints = LiveConnectConstraints(),
      )
    val body =
      mutableMapOf<String, Any?>(
        "bidiGenerateContentSetup" to mapOf("setup" to emptyMap<String, Any?>()),
        "fieldMask" to listOf("output_audio_transcription"),
      )

    val transformed = tokens.convertBidiSetupToTokenSetup(body, config)

    assertEquals("output_audio_transcription", transformed["fieldMask"])
    assertNull(transformed["bidiGenerateContentSetup"])
  }

  @Test
  fun testCreate_throwsOnVertex() = runTest {
    val enterpriseClient =
      Client(
        apiKey = "test-api-key",
        project = "test-project",
        location = "us-central1",
        enterprise = true,
      )
    val exception =
      assertFailsWith<UnsupportedOperationException> {
        enterpriseClient.authTokens.create(CreateAuthTokenConfig())
      }
    assertEquals("This method is only supported in the Gemini Developer client.", exception.message)
  }

  @Test
  fun testGenerationConfigFields_containsExpectedFields() {
    val fields = Tokens.generationConfigFields
    // Verify GenerationConfig properties are included in both camelCase and snake_case
    kotlin.test.assertTrue(fields.contains("temperature"))
    kotlin.test.assertTrue(fields.contains("topK"))
    kotlin.test.assertTrue(fields.contains("top_k"))
    kotlin.test.assertTrue(fields.contains("topP"))
    kotlin.test.assertTrue(fields.contains("top_p"))
    kotlin.test.assertTrue(fields.contains("maxOutputTokens"))
    kotlin.test.assertTrue(fields.contains("max_output_tokens"))
    kotlin.test.assertTrue(fields.contains("responseModalities"))
    kotlin.test.assertTrue(fields.contains("response_modalities"))
    kotlin.test.assertTrue(fields.contains("speechConfig"))
    kotlin.test.assertTrue(fields.contains("speech_config"))
    kotlin.test.assertTrue(fields.contains("audioTimestamp"))
    kotlin.test.assertTrue(fields.contains("audio_timestamp"))
    kotlin.test.assertTrue(fields.contains("audioTranscriptionConfig"))
    kotlin.test.assertTrue(fields.contains("audio_transcription_config"))
  }

  @Test
  fun testAuthToken_instantParsing() {
    val jsonString =
      """
      {
        "name": "auth_tokens/test-token-123",
        "expireTime": "2026-08-20T21:00:00.000Z",
        "newSessionExpireTime": "2026-08-20T20:31:00.000Z",
        "uses": 1
      }
      """
        .trimIndent()
    val map = Common.jsonStringToMap(jsonString)
    val authToken = Common.mapToDataClass<AuthToken>(map)

    assertEquals("auth_tokens/test-token-123", authToken.name)
    assertEquals(1, authToken.uses)
    assertEquals("2026-08-20T21:00:00Z", authToken.expireTime.toString())
    assertEquals("2026-08-20T20:31:00Z", authToken.newSessionExpireTime.toString())
  }
}
