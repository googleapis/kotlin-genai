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

// Auto-generated code. Do not edit.

package com.google.genai.kotlin.types

import kotlinx.serialization.Serializable

/** Response message for PredictionService.GenerateContent. */
@Serializable
data class GenerateContentResponse(

  /** Used to retain the full HTTP response. */
  val sdkHttpResponse: HttpResponse? = null,

  /** Response variations returned by the model. */
  val candidates: List<Candidate>? = null,

  /** Timestamp when the request is made to the server. */
  @Serializable(with = InstantSerializer::class) val createTime: Instant? = null,

  /** Output only. The model version used to generate the response. */
  val modelVersion: String? = null,

  /**
   * Output only. Content filter results for a prompt sent in the request. Note: Sent only in the
   * first stream chunk. Only happens when no candidates were generated due to content violations.
   */
  val promptFeedback: GenerateContentResponsePromptFeedback? = null,

  /**
   * Output only. response_id is used to identify each response. It is the encoding of the event_id.
   */
  val responseId: String? = null,

  /** Usage metadata about the response(s). */
  val usageMetadata: GenerateContentResponseUsageMetadata? = null,

  /**
   * Output only. The current model status of this model. This field is not supported in Vertex AI.
   */
  val modelStatus: ModelStatus? = null,
) {

  /**
   * Returns the concatenation of all text parts in the response.
   *
   * If there are multiple candidates, returns the text from only the first one. If there are
   * non-text parts in the response, this returns only the text parts. Parts where [Part.thought] is
   * true are skipped.
   */
  val text: String?
    get() {
      val candidates = candidates ?: return null
      if (candidates.isEmpty()) return null

      val firstCandidate = candidates[0]
      val parts = firstCandidate.content?.parts ?: return null

      var concatenatedText = ""
      var anyTextPart = false

      for (part in parts) {
        if (part.thought == true) {
          continue
        }
        part.text?.let {
          anyTextPart = true
          concatenatedText += it
        }
      }

      return if (anyTextPart) concatenatedText else null
    }

  /**
   * Returns the list of function calls in the response.
   *
   * If there are multiple candidates, this returns the function calls from only the first one.
   */
  val functionCalls: List<FunctionCall>?
    get() {
      val candidates = candidates ?: return null
      if (candidates.isEmpty()) return null
      val parts = candidates[0].content?.parts ?: return null
      val calls = parts.mapNotNull { it.functionCall }
      return if (calls.isEmpty()) null else calls
    }
}
