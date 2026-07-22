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

import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.GoogleCredentials
import io.mockk.every
import io.mockk.mockk
import com.google.genai.kotlin.types.HttpOptions
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ClientTest {

  private val API_KEY = "test-api-key"
  private val PROJECT = "test-project"
  private val LOCATION = "us-central1"
  private val MOCK_CREDENTIALS = GoogleCredentials.create(AccessToken("test-token", null))

  private val mockEnvironment = mockk<Environment>()

  private fun setEnv(key: String, value: String?) {
    every { mockEnvironment.get(key) } returns value
  }

  private fun clearEnv() {
    every { mockEnvironment.get(any()) } returns null
  }

  @Test
  fun testGeminiClientInitialization_withApiKey() {
    clearEnv()
    val client = Client(apiKey = API_KEY, environment = mockEnvironment)
    assertEquals(API_KEY, client.apiKey)
    assertEquals(false, client.enterprise)
  }

  @Test
  fun testVertexClientInitialization_withProjectLocation() {
    clearEnv()
    val client =
      Client(
        project = PROJECT,
        location = LOCATION,
        credentials = MOCK_CREDENTIALS,
        enterprise = true,
        environment = mockEnvironment,
      )
    assertEquals(PROJECT, client.project)
    assertEquals(LOCATION, client.location)
    assertEquals(true, client.enterprise)
  }

  @Test
  fun testVertexClientInitialization_withProjectAndDefaultLocation() {
    clearEnv()
    val client =
      Client(
        project = PROJECT,
        enterprise = true,
        credentials = MOCK_CREDENTIALS,
        environment = mockEnvironment,
      )
    assertEquals(PROJECT, client.project)
    assertEquals("global", client.location)
    assertEquals(true, client.enterprise)
  }

  @Test
  fun testGeminiClientInitialization_throwsException() {
    clearEnv()
    val exception =
      assertFailsWith<IllegalArgumentException> {
        Client(environment = mockEnvironment)
      }
    assertEquals("API key must either be provided or set in the environment variable GOOGLE_API_KEY or GEMINI_API_KEY.", exception.message)
  }

  @Test
  fun testGeminiClientInitialization_withProjectLocation_throwsException() {
    clearEnv()
    val exception =
      assertFailsWith<IllegalArgumentException> {
        Client(project = PROJECT, location = LOCATION, environment = mockEnvironment)
      }
    assertEquals("Gemini API do not support project/location.", exception.message)
  }

  @Test
  fun testVertexClientInitialization_throwsException() {
    clearEnv()
    val exception =
      assertFailsWith<IllegalArgumentException> {
        Client(enterprise = true, environment = mockEnvironment)
      }
    assertEquals(
      "Authentication is not set up. Please provide either a project and location, or an API key, or a custom base URL.",
      exception.message,
    )
  }

  @Test
  fun testGeminiClientInitialization_withCustomBaseUrl_success() {
    clearEnv()
    val client = Client(
        apiKey = "test-key",
        httpOptions = HttpOptions(baseUrl = "https://my-endpoint.com"),
        environment = mockEnvironment
    )
    assertFalse(client.enterprise)
    assertEquals("https://my-endpoint.com", client.httpClient.httpOptions?.baseUrl)
  }

  @Test
  fun testVertexClientInitialization_withCustomBaseUrl_success() {
    clearEnv()
    val client = Client(
        enterprise = true,
        httpOptions = HttpOptions(baseUrl = "https://my-endpoint.com"),
        environment = mockEnvironment
    )
    assertTrue(client.enterprise)
    assertEquals("https://my-endpoint.com", client.httpClient.httpOptions?.baseUrl)
  }

  @Test
  fun testClientInitialization_withApiKeyAndProject_throwsException() {
    clearEnv()
    var exception =
      assertFailsWith<IllegalArgumentException> {
        Client(project = PROJECT, apiKey = API_KEY, environment = mockEnvironment)
      }
    assertEquals(
      "Project/location and API key are mutually exclusive in the client initializer. Please provide only one of them.",
      exception.message,
    )

    exception =
      assertFailsWith<IllegalArgumentException> {
        Client(location = LOCATION, apiKey = API_KEY, environment = mockEnvironment)
      }
    assertEquals(
      "Project/location and API key are mutually exclusive in the client initializer. Please provide only one of them.",
      exception.message,
    )
  }

  @Test
  fun testGeminiClientInitialization_withProjectOrLocation_throwsException() {
    clearEnv()
    var exception =
      assertFailsWith<IllegalArgumentException> {
        Client(project = PROJECT, environment = mockEnvironment)
      }
    assertEquals(
      "Gemini API do not support project/location.",
      exception.message,
    )

    exception =
      assertFailsWith<IllegalArgumentException> {
        Client(location = LOCATION, environment = mockEnvironment)
      }
    assertEquals(
      "Gemini API do not support project/location.",
      exception.message,
    )
  }

  @Test
  fun testGeminiClientInitialization_withApiKeyEnvVar() {
    clearEnv()
    setEnv("GEMINI_API_KEY", API_KEY)

    val client = Client(environment = mockEnvironment)

    assertEquals(API_KEY, client.apiKey)
    assertEquals(false, client.enterprise)
  }

  @Test
  fun testVertexClientInitialization_withApiKeyEnvVar() {
    clearEnv()
    setEnv("GOOGLE_API_KEY", API_KEY)
    setEnv("GOOGLE_GENAI_USE_ENTERPRISE", "true")

    val client = Client(environment = mockEnvironment)

    assertEquals(API_KEY, client.apiKey)
    assertEquals(true, client.enterprise)
  }

  @Test
  fun testVertexClientInitialization_withProjectLocationEnvVars() {
    clearEnv()
    setEnv("GOOGLE_CLOUD_PROJECT", PROJECT)
    setEnv("GOOGLE_CLOUD_LOCATION", LOCATION)
    setEnv("GOOGLE_GENAI_USE_ENTERPRISE", "true")

    val client = Client(credentials = MOCK_CREDENTIALS, environment = mockEnvironment)

    assertEquals(PROJECT, client.project)
    assertEquals(LOCATION, client.location)
    assertEquals(true, client.enterprise)
  }

  @Test
  fun testVertexClientInitialization_withExplicitCredentials() {
    clearEnv()
    val mockCredentials = mockk<GoogleCredentials>(relaxed = true)
    val client =
      Client(
        project = PROJECT,
        location = LOCATION,
        credentials = mockCredentials,
        enterprise = true,
        environment = mockEnvironment,
      )
    assertEquals(PROJECT, client.project)
    assertEquals(LOCATION, client.location)
    assertEquals(true, client.enterprise)
    assertEquals(mockCredentials, client.httpClient.credentials)
  }

  @Test
  fun testClientInitialization_withApiKeyAndCredentials_throwsException() {
    clearEnv()
    val exception =
      assertFailsWith<IllegalArgumentException> {
        Client(apiKey = API_KEY, credentials = MOCK_CREDENTIALS, environment = mockEnvironment)
      }
    assertEquals(
      "Credentials and API key are mutually exclusive in the client initializer. Please provide only one of them.",
      exception.message,
    )
  }

  @Test
  fun testClientInitialization_withCredentialsAndApiKeyEnvVar() {
    clearEnv()
    setEnv("GOOGLE_API_KEY", API_KEY)
    setEnv("GOOGLE_GENAI_USE_ENTERPRISE", "true")
    val mockCredentials = mockk<GoogleCredentials>(relaxed = true)

    val client =
      Client(
        project = PROJECT,
        location = LOCATION,
        credentials = mockCredentials,
        environment = mockEnvironment,
      )

    assertEquals(null, client.apiKey)
    assertEquals(PROJECT, client.project)
    assertEquals(LOCATION, client.location)
    assertEquals(true, client.enterprise)
    assertEquals(mockCredentials, client.httpClient.credentials)
  }
}
