/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.kotlin.examples

import com.google.genai.kotlin.Client
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to delete a tuned model.
 *
 * Usage:
 *
 * 1. Setup ADC to get credentials:
 *    https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * 2. Set Project, Location, and GOOGLE_GENAI_USE_ENTERPRISE flag as environment variables:
 *
 * export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 *
 * export GOOGLE_CLOUD_LOCATION=YOUR_LOCATION
 *
 * export GOOGLE_GENAI_USE_ENTERPRISE=true
 *
 * Note: This method is not supported by the Gemini Developer API.
 *
 * 3. Run the example:
 * <pre>
 * ./gradlew :example:runExample -PmainClass=com.google.genai.kotlin.examples.DeleteModel --args="projects/your-project-id/locations/us-central1/models/your-model-id"
 * </pre>
 */
object DeleteModel {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      val modelId = args.firstOrNull()

      if (modelId == null) {
        System.err.println("Please provide a tuned model ID as the first argument to delete.")
        System.err.println(
          "Example: projects/your-project-id/locations/us-central1/models/your-model-id"
        )
        kotlin.system.exitProcess(1)
      }

      Client().use { client ->
        try {
          println("Deleting tuned model: $modelId")
          client.models.delete(model = modelId)
          println("Tuned model successfully deleted.")
        } catch (e: Exception) {
          System.err.println("Request failed: ${e.message}")
          e.printStackTrace()
        }
      }

      kotlin.system.exitProcess(0)
    }
}
