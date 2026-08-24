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
import com.google.genai.kotlin.types.ClientOptions
import com.google.genai.kotlin.types.HttpOptions
import com.google.genai.kotlin.types.ProxyOptions
import com.google.genai.kotlin.types.ProxyType
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull

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
  fun testGeminiClientInitialization_withProjectLocation_throwsException() {
    clearEnv()
    val exception =
      assertFailsWith<IllegalArgumentException> {
        Client(project = PROJECT, location = LOCATION, environment = mockEnvironment)
      }
    assertEquals(
      "Project and location are not supported for the Gemini API backend.",
      exception.message,
    )
  }

  @Test
  fun testVertexClientInitialization_throwsException() {
    clearEnv()
    val exception =
      assertFailsWith<IllegalArgumentException> {
        Client(enterprise = true, environment = mockEnvironment)
      }
    assertEquals(
      "For Gemini Enterprise Agent Platform APIs, either project or API key must be set.",
      exception.message,
    )
  }

  @Test
  fun testGeminiClientInitialization_withApiKeyAndProjectOrLocation_throwsException() {
    clearEnv()
    var exception =
      assertFailsWith<IllegalArgumentException> {
        Client(project = PROJECT, apiKey = API_KEY, environment = mockEnvironment)
      }
    assertEquals(
      "Project and location are not supported for the Gemini API backend.",
      exception.message,
    )

    exception =
      assertFailsWith<IllegalArgumentException> {
        Client(location = LOCATION, apiKey = API_KEY, environment = mockEnvironment)
      }
    assertEquals(
      "Project and location are not supported for the Gemini API backend.",
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

  @Test
  fun testVertexClientInitialization_withApiKeyAndProjectLocation() {
    clearEnv()
    val client =
      Client(
        apiKey = API_KEY,
        project = PROJECT,
        location = LOCATION,
        enterprise = true,
        environment = mockEnvironment,
      )
    assertEquals(API_KEY, client.apiKey)
    assertEquals(PROJECT, client.project)
    assertEquals(LOCATION, client.location)
    assertEquals(true, client.enterprise)
    assertEquals(null, client.httpClient.credentials)
  }

  @Test
  fun testVertexClientInitialization_withExplicitApiKeyAndEnvProjectLocation() {
    clearEnv()
    setEnv("GOOGLE_CLOUD_PROJECT", PROJECT)
    setEnv("GOOGLE_CLOUD_LOCATION", LOCATION)
    setEnv("GOOGLE_GENAI_USE_ENTERPRISE", "true")

    val client = Client(apiKey = API_KEY, environment = mockEnvironment)

    assertEquals(API_KEY, client.apiKey)
    assertEquals(null, client.project)
    assertEquals("global", client.location)
    assertEquals(true, client.enterprise)
  }

  @Test
  fun testVertexClientInitialization_withExplicitProjectLocationAndEnvApiKey() {
    clearEnv()
    setEnv("GOOGLE_API_KEY", API_KEY)
    setEnv("GOOGLE_GENAI_USE_ENTERPRISE", "true")

    val client =
      Client(
        project = PROJECT,
        location = LOCATION,
        credentials = MOCK_CREDENTIALS,
        environment = mockEnvironment,
      )

    assertEquals(null, client.apiKey)
    assertEquals(PROJECT, client.project)
    assertEquals(LOCATION, client.location)
    assertEquals(true, client.enterprise)
  }

  @Test
  fun testGeminiClientInitialization_withEnvApiKeyAndEnvProjectLocation() {
    clearEnv()
    setEnv("GEMINI_API_KEY", API_KEY)
    setEnv("GOOGLE_CLOUD_PROJECT", PROJECT)
    setEnv("GOOGLE_CLOUD_LOCATION", LOCATION)

    val client = Client(environment = mockEnvironment)

    assertEquals(API_KEY, client.apiKey)
    assertEquals(null, client.project)
    assertEquals(false, client.enterprise)
  }

  @Test
  fun testClientInitialization_withClientOptionsProxy() {
    clearEnv()
    val proxyOptions =
      ProxyOptions(
        host = "proxy.example.com",
        port = 8080,
        type = ProxyType.HTTP,
        username = "test-user",
        password = "test-password",
      )
    val clientOptions = ClientOptions(proxyOptions = proxyOptions)

    val client =
      Client(apiKey = API_KEY, clientOptions = clientOptions, environment = mockEnvironment)

    assertNotNull(client)
    assertEquals(API_KEY, client.apiKey)
    assertEquals(false, client.enterprise)
    assertEquals("proxy.example.com", client.clientOptions?.proxyOptions?.host)
    assertEquals(8080, client.clientOptions?.proxyOptions?.port)
    assertEquals(ProxyType.HTTP, client.clientOptions?.proxyOptions?.type)
    assertEquals("test-user", client.clientOptions?.proxyOptions?.username)
    assertEquals("test-password", client.clientOptions?.proxyOptions?.password)
  }

  @Test
  fun testClientInitialization_withInvalidProxyOptions_throws() {
    clearEnv()
    val invalidProxyOptions = ProxyOptions(port = 8080, type = ProxyType.HTTP)
    val clientOptions = ClientOptions(proxyOptions = invalidProxyOptions)

    assertFailsWith<IllegalArgumentException> {
      Client(apiKey = API_KEY, clientOptions = clientOptions, environment = mockEnvironment)
    }
  }

  @Test
  fun testClientInitialization_withCustomHttpClient() {
    clearEnv()
    val customEngine = MockEngine { respond("") }
    val clientOptions = ClientOptions(customHttpClient = customEngine)

    val client =
      Client(apiKey = API_KEY, clientOptions = clientOptions, environment = mockEnvironment)

    assertNotNull(client)
    assertEquals(API_KEY, client.apiKey)
    assertEquals(customEngine, client.httpClient.httpEngine)
    assertEquals(customEngine, client.clientOptions?.customHttpClient)
  }

  @Test
  fun testClientInitialization_customHttpClientOverridesProxyOptions() {
    clearEnv()
    val customEngine = MockEngine { respond("") }
    // Invalid proxy options that would throw IllegalArgumentException if getDefaultEngine were
    // called
    val invalidProxyOptions = ProxyOptions(port = 8080, type = ProxyType.HTTP)
    val clientOptions =
      ClientOptions(proxyOptions = invalidProxyOptions, customHttpClient = customEngine)

    val client =
      Client(apiKey = API_KEY, clientOptions = clientOptions, environment = mockEnvironment)

    assertNotNull(client)
    assertEquals(customEngine, client.httpClient.httpEngine)
  }

  // An exported-but-empty variable is routine in CI templates and Docker env_files. It used to
  // resolve to "", which is non-null, so it suppressed both the GEMINI_API_KEY fallback and the
  // ADC lookup and sent an empty key instead.
  @Test
  fun testEmptyGoogleApiKeyFallsBackToGeminiApiKey() {
    clearEnv()
    setEnv("GOOGLE_API_KEY", "")
    setEnv("GEMINI_API_KEY", API_KEY)

    val client = Client(environment = mockEnvironment)

    assertEquals(API_KEY, client.apiKey)
  }

  @Test
  fun testEmptyApiKeyDoesNotSuppressCredentials() {
    clearEnv()
    setEnv("GOOGLE_API_KEY", "")
    setEnv("GOOGLE_CLOUD_PROJECT", PROJECT)
    setEnv("GOOGLE_GENAI_USE_ENTERPRISE", "true")

    val client = Client(credentials = MOCK_CREDENTIALS, environment = mockEnvironment)

    assertNull(client.apiKey, "An empty GOOGLE_API_KEY must not resolve to a key")
    assertEquals(PROJECT, client.project)
  }

  // Sweeps the remaining reads, so a call site that goes back to environment.get() directly is
  // caught -- two paths disagreeing on what empty means is what caused this in the first place.
  @Test
  fun testEmptyEnvVarsAreTreatedAsUnset() {
    clearEnv()
    setEnv("GOOGLE_API_KEY", API_KEY)
    setEnv("GOOGLE_CLOUD_PROJECT", "")
    setEnv("GOOGLE_CLOUD_LOCATION", "")
    setEnv("GOOGLE_GENAI_USE_ENTERPRISE", "")

    val client = Client(environment = mockEnvironment)

    assertNull(client.project)
    assertNull(client.location)
    assertFalse(client.enterprise)
  }

  @Test
  fun testClientInitialization_withApiVersion() {
    clearEnv()
    val client = Client(apiKey = API_KEY, apiVersion = "v1beta", environment = mockEnvironment)
    assertNotNull(client)
    assertEquals(API_KEY, client.apiKey)
    assertEquals("v1beta", client.httpClient.httpOptions?.apiVersion)
  }

  @Test
  fun testGeminiClientInitialization_withApiVersionEnvVar() {
    clearEnv()
    setEnv("GOOGLE_GENAI_API_VERSION", "v1alpha")
    val client = Client(apiKey = API_KEY, environment = mockEnvironment)
    assertNotNull(client)
    assertEquals(false, client.enterprise)
    assertEquals("v1alpha", client.httpClient.httpOptions?.apiVersion)
  }

  @Test
  fun testVertexClientInitialization_withApiVersionEnvVar() {
    clearEnv()
    setEnv("GOOGLE_GENAI_API_VERSION", "v1")
    val client =
      Client(
        project = PROJECT,
        location = LOCATION,
        credentials = MOCK_CREDENTIALS,
        enterprise = true,
        environment = mockEnvironment,
      )
    assertNotNull(client)
    assertEquals(true, client.enterprise)
    assertEquals("v1", client.httpClient.httpOptions?.apiVersion)
  }

  @Test
  fun testClientInitialization_explicitApiVersionOverridesEnvVar() {
    clearEnv()
    setEnv("GOOGLE_GENAI_API_VERSION", "v1alpha")
    val client = Client(apiKey = API_KEY, apiVersion = "v1", environment = mockEnvironment)
    assertNotNull(client)
    assertEquals("v1", client.httpClient.httpOptions?.apiVersion)
  }

  @Test
  fun testClientInitialization_httpOptionsApiVersionOverridesEnvVar() {
    clearEnv()
    setEnv("GOOGLE_GENAI_API_VERSION", "v1alpha")
    val client =
      Client(
        apiKey = API_KEY,
        httpOptions = HttpOptions(apiVersion = "v1"),
        environment = mockEnvironment,
      )
    assertNotNull(client)
    assertEquals("v1", client.httpClient.httpOptions?.apiVersion)
  }

  @Test
  fun testClientInitialization_withApiVersionConflict_throws() {
    clearEnv()
    val exception =
      assertFailsWith<IllegalArgumentException> {
        Client(
          apiKey = API_KEY,
          apiVersion = "v1beta",
          httpOptions = HttpOptions(apiVersion = "v1"),
          environment = mockEnvironment,
        )
      }
    assertEquals(
      "apiVersion is set in both client options and httpOptions. Please specify apiVersion in only one place.",
      exception.message,
    )
  }
}
