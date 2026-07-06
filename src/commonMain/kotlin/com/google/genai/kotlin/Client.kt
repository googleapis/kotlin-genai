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

import com.google.genai.kotlin.types.HttpOptions

/**
 * The main entry point for the Google Gen AI SDK.
 *
 * **Note on Mobile Security:** While this SDK supports Android targets via Kotlin Multiplatform, it
 * is intended for server-side use or secure backend environments. We strongly discourage embedding
 * API keys or Google Cloud IAM credentials (such as Service Account JSON keys or OAuth tokens)
 * directly into public mobile client applications without protection. For public mobile apps
 * connecting directly from client devices, please use
 * [Firebase AI Logic](https://firebase.google.com/docs/ai-logic) with Firebase App Check enabled.
 *
 * Usage:
 * ```
 * val client = Client(apiKey = "YOUR_API_KEY")
 * val response = client.models.generateContent("gemini-3.5-flash", "Hello, world!")
 * println(response.text)
 * ```
 */
class Client
internal constructor(
  apiKey: String? = null,
  project: String? = null,
  location: String? = null,
  credentials: GoogleCredentials? = null,
  enterprise: Boolean? = null,
  httpOptions: HttpOptions? = null,
  private val environment: Environment = getDefaultEnvironment(),
) : AutoCloseable {

  /**
   * Constructs a Client instance for either Gemini API or Gemini Enterprise Agent Platform API.
   *
   * @param apiKey The API key to use. For Gemini API or Gemini Enterprise Agent Platform API.
   * @param project The Google Cloud project ID. For Gemini Enterprise Agent Platform API only.
   * @param location The Google Cloud location. For Gemini Enterprise Agent Platform API only.
   * @param credentials The Google credentials to use. For Gemini Enterprise Agent Platform API
   *   only.
   * @param enterprise Explicitly set whether to use Gemini Enterprise Agent Platform API. Default
   *   to false or from environment variable GOOGLE_GENAI_USE_ENTERPRISE.
   * @param httpOptions Custom HTTP options for the client.
   */
  constructor(
    apiKey: String? = null,
    project: String? = null,
    location: String? = null,
    credentials: GoogleCredentials? = null,
    enterprise: Boolean? = null,
    httpOptions: HttpOptions? = null,
  ) : this(
    apiKey = apiKey,
    project = project,
    location = location,
    credentials = credentials,
    enterprise = enterprise,
    httpOptions = httpOptions,
    environment = getDefaultEnvironment(),
  )

  /**
   * The API key used for authentication with Gemini API or Gemini Enterprise Agent Platform API.
   */
  val apiKey: String?

  /** The Google Cloud project ID used for Gemini Enterprise Agent Platform API. */
  val project: String?

  /** The Google Cloud location used for Gemini Enterprise Agent Platform API. */
  val location: String?

  /** Whether the client is configured to use Gemini Enterprise Agent Platform API. */
  val enterprise: Boolean

  internal val httpClient: ApiClient

  init {
    val envEnterprise = environment.get("GOOGLE_GENAI_USE_ENTERPRISE")
    val useEnterprise = enterprise ?: (envEnterprise?.equals("true", ignoreCase = true) ?: false)

    val envApiKey = environment.get("GOOGLE_API_KEY") ?: environment.get("GEMINI_API_KEY")
    val envProject = environment.get("GOOGLE_CLOUD_PROJECT")
    val envLocation = environment.get("GOOGLE_CLOUD_LOCATION")

    var resolvedApiKey: String? = apiKey ?: envApiKey
    var resolvedProject: String? = project ?: envProject
    var resolvedLocation: String? = location ?: envLocation

    val hasApiKey = apiKey != null
    val hasCredentials = credentials != null
    val hasProject = project != null
    val hasLocation = location != null

    val hasEnvApiKey =
      environment.get("GOOGLE_API_KEY")?.isNotEmpty() == true ||
        environment.get("GEMINI_API_KEY")?.isNotEmpty() == true
    val hasEnvProject = environment.get("GOOGLE_CLOUD_PROJECT")?.isNotEmpty() == true
    val hasEnvLocation = environment.get("GOOGLE_CLOUD_LOCATION")?.isNotEmpty() == true

    if (hasProject && hasApiKey) {
      throw IllegalArgumentException(
        "Project and API key are mutually exclusive in the client initializer. Please provide only one of them."
      )
    }

    if (hasLocation && hasApiKey) {
      throw IllegalArgumentException(
        "Location and API key are mutually exclusive in the client initializer. Please provide only one of them."
      )
    }

    if (hasCredentials && hasApiKey) {
      throw IllegalArgumentException(
        "Credentials and API key are mutually exclusive in the client initializer. Please provide only one of them."
      )
    }

    if (hasCredentials && hasEnvApiKey) {
      resolvedApiKey = null
    }

    if (hasApiKey && (hasEnvProject || hasEnvLocation)) {
      resolvedProject = null
      resolvedLocation = null
    } else if ((hasProject || hasLocation) && hasEnvApiKey) {
      resolvedApiKey = null
    } else if ((hasEnvProject || hasEnvLocation) && hasEnvApiKey) {
      if (useEnterprise) {
        resolvedApiKey = null
      } else {
        resolvedProject = null
        resolvedLocation = null
      }
    }

    if (resolvedLocation == null && resolvedApiKey == null) {
      resolvedLocation = "global"
    }

    if (!useEnterprise && resolvedApiKey == null) {
      throw IllegalArgumentException("For Gemini APIs, API key must be set.")
    }

    if (useEnterprise && resolvedProject == null && resolvedApiKey == null) {
      throw IllegalArgumentException(
        "For Gemini Enterprise Agent Platform APIs, either project or API key must be set."
      )
    }

    val resolvedCredentials =
      if (!useEnterprise || resolvedProject == null) {
        null
      } else {
        credentials ?: getDefaultCredentials()
      }

    httpClient =
      ApiClient(
        apiKey = resolvedApiKey,
        project = resolvedProject,
        location = resolvedLocation,
        credentials = resolvedCredentials,
        enterprise = useEnterprise,
        httpOptions = httpOptions,
      )

    this.apiKey = resolvedApiKey
    this.project = resolvedProject
    this.location = resolvedLocation
    this.enterprise = useEnterprise
  }

  /** Service for interacting with Models API. */
  val models = Models(httpClient)

  /** Closes the Client instance and its underlying HTTP client. */
  override fun close() {
    httpClient.close()
  }
}
