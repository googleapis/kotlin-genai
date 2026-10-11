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

import com.google.genai.kotlin.types.FinishReason
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.GenerateContentResponse
import com.google.genai.kotlin.types.GenerateContentResponseUsageMetadata
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull

/**
 * Sends a generateContent request through [send], then sends the same request again with each
 * response's continuation token until the model finishes. Returns the responses merged into one.
 *
 * The caller has already decided that automatic continuation is on. The first request goes out with
 * [config] as given, null included, so it is the request the caller would have sent anyway.
 */
internal suspend fun generateWithAutomaticContinuation(
  config: GenerateContentConfig?,
  send: suspend (GenerateContentConfig?) -> GenerateContentResponse,
): GenerateContentResponse {
  val responses = mutableListOf<GenerateContentResponse>()
  var requestConfig = config
  while (true) {
    val response = send(requestConfig)
    responses += response
    val candidate = response.candidates?.firstOrNull()
    val token = candidate?.continuationToken?.takeIf { it.isNotEmpty() }
    if (token == null || !isContinuable(candidate.finishReason)) {
      break
    }
    requestConfig = withContinuationToken(config, token)
  }
  return mergeContinuationResponses(responses)
}

/**
 * The streaming counterpart of [generateWithAutomaticContinuation]. The chunks of every request are
 * emitted in the order they arrive, with usage metadata accumulated across requests.
 */
internal fun streamWithAutomaticContinuation(
  config: GenerateContentConfig?,
  send: (GenerateContentConfig?) -> Flow<GenerateContentResponse>,
): Flow<GenerateContentResponse> {
  return flow {
    var requestConfig = config
    var accumulatedUsage: GenerateContentResponseUsageMetadata? = null
    while (true) {
      var finishReason: FinishReason? = null
      var token: ByteArray? = null
      var hopUsage: GenerateContentResponseUsageMetadata? = null
      try {
        send(requestConfig).collect { chunk ->
          val candidate = chunk.candidates?.firstOrNull()
          candidate?.finishReason?.let { finishReason = it }
          // A long stream also puts checkpoint tokens on chunks before the last one, so the token
          // to resume from is the last one seen unless the hop ends with a non-resumable reason.
          val chunkToken = candidate?.continuationToken?.takeIf { it.isNotEmpty() }
          if (chunkToken != null) {
            token = chunkToken
          } else if (candidate?.finishReason != null && !isContinuable(candidate.finishReason)) {
            token = null
          }
          val chunkUsage = chunk.usageMetadata
          if (chunkUsage != null) {
            val mergedUsage =
              if (accumulatedUsage != null) {
                mergeUsageMetadata(accumulatedUsage!!, chunkUsage)
              } else {
                chunkUsage
              }
            hopUsage = mergedUsage
            emit(if (mergedUsage !== chunkUsage) chunk.copy(usageMetadata = mergedUsage) else chunk)
          } else {
            emit(chunk)
          }
        }
      } catch (e: Throwable) {
        // If a mid-stream error occurs after an intermediate checkpoint continuationToken was
        // received, resume from that checkpoint.
        if (e is CancellationException || token == null || !isContinuable(finishReason)) {
          throw e
        }
      }
      if (hopUsage != null) {
        accumulatedUsage = hopUsage
      }
      val next = token
      if (next == null || !isContinuable(finishReason)) {
        break
      }
      requestConfig = withContinuationToken(config, next)
    }
  }
}

/**
 * Merges the responses of one continued generation into a single response. Lists such as the parts
 * are concatenated in order, each candidate is merged with the one of the same index, token counts
 * are summed, and end of generation values such as the finish reason come from the last response.
 */
internal fun mergeContinuationResponses(
  responses: List<GenerateContentResponse>
): GenerateContentResponse {
  if (responses.size == 1) {
    return responses.single()
  }
  // sdkHttpResponse holds the raw body, continuation token and all, so it is kept out of the merge.
  val merged =
    responses
      .map { response ->
        Common.JSON.encodeToJsonElement(
            GenerateContentResponse.serializer(),
            response.copy(sdkHttpResponse = null),
          )
          .jsonObject
      }
      .reduce { prev, curr -> mergeObjects(prev, curr) }
  return Common.JSON.decodeFromJsonElement(GenerateContentResponse.serializer(), merged)
    .copy(sdkHttpResponse = responses.lastOrNull { it.sdkHttpResponse != null }?.sdkHttpResponse)
    .also {
      it.usageMetadata?.firstHopPromptTokenCount = responses.firstNotNullOfOrNull { response ->
        response.usageMetadata?.initialPromptTokenCount
      }
    }
}

internal fun mergeUsageMetadata(
  prev: GenerateContentResponseUsageMetadata,
  curr: GenerateContentResponseUsageMetadata,
): GenerateContentResponseUsageMetadata {
  val prevJson =
    Common.JSON.encodeToJsonElement(GenerateContentResponseUsageMetadata.serializer(), prev)
      .jsonObject
  val currJson =
    Common.JSON.encodeToJsonElement(GenerateContentResponseUsageMetadata.serializer(), curr)
      .jsonObject
  return Common.JSON.decodeFromJsonElement(
      GenerateContentResponseUsageMetadata.serializer(),
      mergeObjects(prevJson, currJson, sumAllNumbers = true),
    )
    .also {
      it.firstHopPromptTokenCount = prev.initialPromptTokenCount ?: curr.initialPromptTokenCount
    }
}

// Per the continuation protocol, when a continuationToken is present the backend only sets
// finishReason to null (intermediate checkpoint chunk) or CONTINUATION (stream end). MAX_TOKENS
// means the caller's maxOutputTokens, which the server counts across all the requests, is spent.
private fun isContinuable(finishReason: FinishReason?): Boolean =
  finishReason == null || finishReason == FinishReason.CONTINUATION

private fun withContinuationToken(config: GenerateContentConfig?, token: ByteArray) =
  (config ?: GenerateContentConfig()).copy(continuationToken = token)

private val END_OF_GENERATION_FIELDS = setOf("continuationToken", "finishReason", "finishMessage")

private val MODALITY_TOKEN_COUNT_FIELDS = setOf("modality", "tokenCount")

private fun mergeObjects(
  prev: JsonObject,
  curr: JsonObject,
  sumAllNumbers: Boolean = false,
  latestOnly: Set<String> = emptySet(),
): JsonObject {
  val merged = LinkedHashMap<String, JsonElement>()
  for (key in prev.keys + curr.keys) {
    val value =
      if (key in latestOnly) curr[key] else mergeValues(key, prev[key], curr[key], sumAllNumbers)
    if (value != null && value !is JsonNull) {
      merged[key] = value
    }
  }
  return JsonObject(merged)
}

private fun mergeValues(
  key: String,
  prev: JsonElement?,
  curr: JsonElement?,
  sumAllNumbers: Boolean,
): JsonElement? {
  val previous = prev?.takeUnless { it is JsonNull }
  val current = curr?.takeUnless { it is JsonNull }
  return when {
    current == null -> previous
    previous == null -> current
    previous is JsonObject && current is JsonObject ->
      mergeObjects(previous, current, sumAllNumbers = sumAllNumbers || key == "usageMetadata")
    previous is JsonArray && current is JsonArray -> mergeArrays(key, previous, current)
    previous is JsonPrimitive &&
      current is JsonPrimitive &&
      (sumAllNumbers || key.endsWith("Count") || key.endsWith("Sum")) ->
      sumNumbers(previous, current) ?: current
    else -> current
  }
}

private fun mergeArrays(key: String, prev: JsonArray, curr: JsonArray): JsonArray {
  val first = prev.firstOrNull() ?: curr.firstOrNull()
  return when {
    key == "candidates" -> mergeCandidates(prev, curr)
    first != null && isModalityTokenCount(first) -> mergeModalityTokenCounts(prev, curr)
    key == "safetyRatings" -> latestPerCategory(prev, curr)
    else -> JsonArray(prev + curr)
  }
}

// Each candidate is merged with the one that has the same index in the next response. A candidate
// that only one response has is kept.
private fun mergeCandidates(prev: JsonArray, curr: JsonArray): JsonArray {
  val merged = LinkedHashMap<Int, JsonObject>()
  prev.forEachIndexed { position, candidate ->
    merged[candidateIndex(candidate.jsonObject, position)] = candidate.jsonObject
  }
  curr.forEachIndexed { position, candidate ->
    val index = candidateIndex(candidate.jsonObject, position)
    val previous = merged[index]
    merged[index] =
      if (previous == null) {
        candidate.jsonObject
      } else {
        mergeObjects(previous, candidate.jsonObject, latestOnly = END_OF_GENERATION_FIELDS)
      }
  }
  return JsonArray(merged.values.toList())
}

// A candidate without an index is matched by its position.
private fun candidateIndex(candidate: JsonObject, position: Int): Int =
  candidate["index"]?.jsonPrimitive?.intOrNull ?: position

private fun isModalityTokenCount(element: JsonElement): Boolean =
  element is JsonObject &&
    element.isNotEmpty() &&
    MODALITY_TOKEN_COUNT_FIELDS.containsAll(element.keys)

private fun mergeModalityTokenCounts(prev: JsonArray, curr: JsonArray): JsonArray {
  val totals = LinkedHashMap<JsonElement?, Long>()
  for (item in prev + curr) {
    val count = item.jsonObject["tokenCount"]?.jsonPrimitive?.longOrNull ?: 0L
    val modality = item.jsonObject["modality"]
    totals[modality] = (totals[modality] ?: 0L) + count
  }
  return JsonArray(
    totals.map { (modality, count) ->
      JsonObject(
        buildMap<String, JsonElement> {
          if (modality != null) {
            put("modality", modality)
          }
          put("tokenCount", JsonPrimitive(count))
        }
      )
    }
  )
}

private fun latestPerCategory(prev: JsonArray, curr: JsonArray): JsonArray {
  val latest = LinkedHashMap<JsonElement?, JsonElement>()
  for (rating in prev + curr) {
    latest[(rating as? JsonObject)?.get("category")] = rating
  }
  return JsonArray(latest.values.toList())
}

private fun sumNumbers(a: JsonPrimitive, b: JsonPrimitive): JsonPrimitive? {
  if (a.isString || b.isString) {
    return null
  }
  val longA = a.longOrNull
  val longB = b.longOrNull
  if (longA != null && longB != null) {
    return JsonPrimitive(longA + longB)
  }
  val doubleA = a.doubleOrNull ?: return null
  val doubleB = b.doubleOrNull ?: return null
  return JsonPrimitive(doubleA + doubleB)
}
