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
import com.google.genai.kotlin.types.HttpOptions
import com.google.testserver.TestServer
import com.google.testserver.TestServerOptions
import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

/**
 * Base class for tests that utilize the `test-server` to mock Gemini API responses.
 *
 * Concrete test classes should extend this class to automatically benefit from the test server
 * setup.
 */
open class BaseTestServer {

  protected lateinit var testServer: TestServer
  protected lateinit var testMode: String
  protected lateinit var project: String
  protected lateinit var location: String
  protected lateinit var apiKey: String

  @BeforeTest
  fun setUp() {
    testMode = System.getenv("TEST_MODE")?.takeIf { it.isNotEmpty() } ?: "replay"
    project = System.getenv("GOOGLE_CLOUD_PROJECT")?.takeIf { it.isNotEmpty() } ?: "test-project"
    location = System.getenv("GOOGLE_CLOUD_LOCATION")?.takeIf { it.isNotEmpty() } ?: "global"
    apiKey = System.getenv("GOOGLE_API_KEY")?.takeIf { it.isNotEmpty() } ?: "test-api-key"

    val options =
      TestServerOptions(
        configPath = "src/commonTest/resources/test-server.yml",
        recordingDir = "src/commonTest/resources/recordings",
        mode = testMode,
        testServerSecrets = "$project,$apiKey",
        outDir = File(System.getProperty("java.io.tmpdir"), "google_genai_test_server"),
      )
    testServer = TestServer(options)
    testServer.start()
  }

  @AfterTest
  fun tearDown() {
    testServer.stop()
  }

  /** Creates a [Client] configured to use the test server. */
  fun createClient(
    enterprise: Boolean,
    testName: String,
    locationOverride: String? = null,
    credentials: GoogleCredentials? = null,
  ): Client {
    val effectiveLocation = locationOverride ?: location
    val port =
      if (enterprise) {
        when (effectiveLocation) {
          "global" -> 1455
          "us" -> 1456
          "us-central1" -> 1454
          else -> throw IllegalArgumentException("Unsupported location for tests: $effectiveLocation")
        }
      } else {
        1453
      }
    val httpOptions =
      HttpOptions(baseUrl = "http://localhost:$port", headers = mapOf("Test-Name" to testName))
    if (enterprise) {
      val creds =
        credentials ?: if (testMode == "replay") {
          GoogleCredentials.create(AccessToken("test-token", null))
        } else {
          null
        }
      return Client(
        project = project,
        location = effectiveLocation,
        credentials = creds,
        enterprise = enterprise,
        httpOptions = httpOptions,
      )
    } else {
      return Client(apiKey = apiKey, httpOptions = httpOptions)
    }
  }
}
