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

package com.google.genai.kotlin.shared.models

import com.google.genai.kotlin.shared.SHARED_MODEL
import com.google.genai.kotlin.shared.SharedTestBase
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.Schema
import com.google.genai.kotlin.types.Type
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 120.seconds, testBody = testBody)

class GenerateContentTest : SharedTestBase() {
  private fun schemaConfig() =
    GenerateContentConfig(
      responseMimeType = "application/json",
      responseSchema =
        Schema(type = Type.OBJECT, properties = mapOf("summary" to Schema(type = Type.STRING))),
    )

  private fun jsonSchemaConfig() =
    GenerateContentConfig(
      responseMimeType = "application/json",
      responseJsonSchema =
        buildJsonObject {
          put("type", "object")
          put(
            "properties",
            buildJsonObject { put("summary", buildJsonObject { put("type", "string") }) },
          )
        },
    )

  /** Canonical models/generate_content: test_generate_content_with_config_schema. */
  @Test
  fun testGenerateContentWithConfigSchema() = runTest {
    listOf(false, true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.models.GenerateContentTest.testGenerateContentWithConfigSchema.$suffix"
      val client = createClient(enterprise, testName)

      runLive {
        val response =
          client.models.generateContent(
            model = SHARED_MODEL,
            text = "Return a summary of the passage.",
            config = schemaConfig(),
          )
        assertNotNull(response)
        assertTrue(!response.text.isNullOrBlank(), "Expected non-empty structured output")
      }
    }
  }

  /** Canonical models/generate_content: test_generate_content_with_config_json_schema. */
  @Test
  fun testGenerateContentWithConfigJsonSchema() = runTest {
    listOf(false, true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName =
        "shared.models.GenerateContentTest.testGenerateContentWithConfigJsonSchema.$suffix"
      val client = createClient(enterprise, testName)

      runLive {
        val response =
          client.models.generateContent(
            model = SHARED_MODEL,
            text = "Return a JSON summary.",
            config = jsonSchemaConfig(),
          )
        assertNotNull(response)
        assertTrue(!response.text.isNullOrBlank(), "Expected non-empty structured output")
      }
    }
  }
}
