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

import com.google.genai.kotlin.types.CreateAuthTokenConfig
import com.google.genai.kotlin.types.HttpOptions
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 60.seconds, testBody = testBody)

@OptIn(ExperimentalGenAiApi::class)
class TokensTest : BaseTestServer() {

  @Test
  fun testCreateAuthToken() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "TokensTest.testCreateAuthToken.$suffix"

      val client = createClient(enterprise = enterprise, testName = testName)

      if (enterprise) {
        val exception =
          assertFailsWith<UnsupportedOperationException> {
            client.authTokens.create(CreateAuthTokenConfig())
          }
        assertEquals(
          "This method is only supported in the Gemini Developer client.",
          exception.message,
        )
      } else {
        val config = CreateAuthTokenConfig(httpOptions = HttpOptions(apiVersion = "v1alpha"))
        val response = client.authTokens.create(config)
        assertNotNull(response)
        assertNotNull(response.name)
      }
    }
  }
}
