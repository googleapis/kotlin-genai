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

import com.google.genai.kotlin.types.Candidate
import com.google.genai.kotlin.types.Citation
import com.google.genai.kotlin.types.CitationMetadata
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.FinishReason
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.GenerateContentResponse
import com.google.genai.kotlin.types.GenerateContentResponseUsageMetadata
import com.google.genai.kotlin.types.HarmCategory
import com.google.genai.kotlin.types.HarmProbability
import com.google.genai.kotlin.types.HttpResponse
import com.google.genai.kotlin.types.MediaModality
import com.google.genai.kotlin.types.ModalityTokenCount
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.SafetyRating
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest

private val TOKEN_1 = "token-1".encodeToByteArray()
private val TOKEN_2 = "token-2".encodeToByteArray()

private val CONFIG = GenerateContentConfig(automaticContinuation = true, temperature = 0.5)

private fun response(
  text: String,
  finishReason: FinishReason? = null,
  token: ByteArray? = null,
): GenerateContentResponse =
  GenerateContentResponse(
    candidates =
      listOf(
        Candidate(
          content = Content(role = "model", parts = listOf(Part(text = text))),
          finishReason = finishReason,
          continuationToken = token,
        )
      )
  )

/** Hands out [responses] in order and records the config of every request. */
private class FakeModel(vararg responses: GenerateContentResponse) {
  private val pending = ArrayDeque(responses.toList())
  val configs = mutableListOf<GenerateContentConfig?>()

  fun send(config: GenerateContentConfig?): GenerateContentResponse {
    configs += config
    return pending.removeFirst()
  }
}

/** Hands out one stream per request, in order, and records the config of every request. */
private class FakeStreamingModel(vararg streams: Flow<GenerateContentResponse>) {
  private val pending = ArrayDeque(streams.toList())
  val configs = mutableListOf<GenerateContentConfig?>()

  fun send(config: GenerateContentConfig?): Flow<GenerateContentResponse> {
    configs += config
    return pending.removeFirst()
  }
}

class AutomaticContinuationTest {

  @Test
  fun testContinuesWithEachTokenUntilTheModelFinishes() = runTest {
    val model =
      FakeModel(
        response("Hello ", FinishReason.CONTINUATION, TOKEN_1),
        response("big ", FinishReason.CONTINUATION, TOKEN_2),
        response("world", FinishReason.STOP),
      )

    val merged = generateWithAutomaticContinuation(CONFIG) { model.send(it) }

    assertEquals("Hello big world", merged.text)
    assertEquals(3, model.configs.size)
    assertEquals(CONFIG, model.configs[0])
    assertContentEquals(TOKEN_1, model.configs[1]?.continuationToken)
    assertContentEquals(TOKEN_2, model.configs[2]?.continuationToken)
    // Apart from the token, every request is the original one.
    assertEquals(CONFIG, model.configs[1]?.copy(continuationToken = null))
    assertEquals(CONFIG, model.configs[2]?.copy(continuationToken = null))
  }

  @Test
  fun testDoesNotContinueOnMaxTokens() = runTest {
    val only = response("Hello", FinishReason.MAX_TOKENS, TOKEN_1)
    val model = FakeModel(only)

    assertSame(only, generateWithAutomaticContinuation(CONFIG) { model.send(it) })
    assertEquals(1, model.configs.size)
  }

  @Test
  fun testStopsWhenTheResponseHasNoToken() = runTest {
    val model = FakeModel(response("Hello", FinishReason.CONTINUATION))

    generateWithAutomaticContinuation(CONFIG) { model.send(it) }

    assertEquals(1, model.configs.size)
  }

  @Test
  fun testStopsOnOtherFinishReasons() = runTest {
    val model = FakeModel(response("Hello", FinishReason.SAFETY, TOKEN_1))

    generateWithAutomaticContinuation(CONFIG) { model.send(it) }

    assertEquals(1, model.configs.size)
  }

  @Test
  fun testSendsTheFirstRequestWithoutAConfigWhenGivenNone() = runTest {
    val model =
      FakeModel(
        response("Hello ", FinishReason.CONTINUATION, TOKEN_1),
        response("world", FinishReason.STOP),
      )

    val merged = generateWithAutomaticContinuation(null) { model.send(it) }

    assertEquals("Hello world", merged.text)
    assertNull(model.configs[0])
    assertContentEquals(TOKEN_1, model.configs[1]?.continuationToken)
    assertEquals(GenerateContentConfig(), model.configs[1]?.copy(continuationToken = null))
  }

  @Test
  fun testSendsMaxOutputTokensWithEveryRequest() = runTest {
    val config = CONFIG.copy(maxOutputTokens = 100)
    val model =
      FakeModel(
        response("Hello ", FinishReason.CONTINUATION, TOKEN_1),
        response("world", FinishReason.STOP),
      )

    generateWithAutomaticContinuation(config) { model.send(it) }

    assertEquals(listOf<Int?>(100, 100), model.configs.map { it?.maxOutputTokens })
  }

  @Test
  fun testStreamEmitsTheChunksOfEveryRequestInOrder() = runTest {
    val model =
      FakeStreamingModel(
        flowOf(
          response("a"),
          // A checkpoint in the middle of a long stream.
          response("b", token = TOKEN_1),
          response("c", FinishReason.CONTINUATION, TOKEN_2),
        ),
        flowOf(response("d", FinishReason.STOP)),
      )

    val chunks = streamWithAutomaticContinuation(CONFIG) { model.send(it) }.toList()

    assertEquals(listOf<String?>("a", "b", "c", "d"), chunks.map { it.text })
    assertEquals(2, model.configs.size)
    assertContentEquals(TOKEN_2, model.configs[1]?.continuationToken)
    assertEquals(CONFIG, model.configs[1]?.copy(continuationToken = null))
  }

  @Test
  fun testStreamResumesFromATokenSentBeforeTheFinishReason() = runTest {
    val model =
      FakeStreamingModel(
        flowOf(response("a", token = TOKEN_1), response("b", FinishReason.CONTINUATION)),
        flowOf(response("c", FinishReason.STOP)),
      )

    val chunks = streamWithAutomaticContinuation(CONFIG) { model.send(it) }.toList()

    assertEquals(listOf<String?>("a", "b", "c"), chunks.map { it.text })
    assertEquals(2, model.configs.size)
    assertContentEquals(TOKEN_1, model.configs[1]?.continuationToken)
  }

  @Test
  fun testStreamResumesFromCheckpointTokenOnMidStreamCutoffOrError() = runTest {
    val model =
      FakeStreamingModel(
        // Hop 1: intermediate checkpoint chunk (finishReason == null), then stream cuts off early
        flowOf(response("a", token = TOKEN_1)),
        // Hop 2: intermediate checkpoint chunk, then mid-stream exception
        flow {
          emit(response("b", token = TOKEN_2))
          throw IllegalStateException("stream dropped")
        },
        // Hop 3: completes with STOP
        flowOf(response("c", FinishReason.STOP)),
      )

    val chunks = streamWithAutomaticContinuation(CONFIG) { model.send(it) }.toList()

    assertEquals(listOf<String?>("a", "b", "c"), chunks.map { it.text })
    assertEquals(3, model.configs.size)
    assertContentEquals(TOKEN_1, model.configs[1]?.continuationToken)
    assertContentEquals(TOKEN_2, model.configs[2]?.continuationToken)
  }

  @Test
  fun testStreamRethrowsMidStreamErrorWhenNoCheckpointTokenWasReceived() = runTest {
    val model =
      FakeStreamingModel(
        flow {
          emit(response("a"))
          throw IllegalStateException("unrecoverable error")
        }
      )

    assertFailsWith<IllegalStateException> {
      streamWithAutomaticContinuation(CONFIG) { model.send(it) }.toList()
    }
    assertEquals(1, model.configs.size)
  }

  @Test
  fun testStreamStopsAtACheckpointWhenTheModelFinishes() = runTest {
    val model =
      FakeStreamingModel(flowOf(response("a", token = TOKEN_1), response("b", FinishReason.STOP)))

    val chunks = streamWithAutomaticContinuation(CONFIG) { model.send(it) }.toList()

    assertEquals(2, chunks.size)
    assertEquals(1, model.configs.size)
  }

  @Test
  fun testStreamSendsTheFirstRequestWithoutAConfigWhenGivenNone() = runTest {
    val model =
      FakeStreamingModel(
        flowOf(response("a", FinishReason.CONTINUATION, TOKEN_1)),
        flowOf(response("b", FinishReason.STOP)),
      )

    val chunks = streamWithAutomaticContinuation(null) { model.send(it) }.toList()

    assertEquals(listOf<String?>("a", "b"), chunks.map { it.text })
    assertNull(model.configs[0])
    assertContentEquals(TOKEN_1, model.configs[1]?.continuationToken)
  }

  @Test
  fun testStreamDoesNotContinueOnMaxTokens() = runTest {
    val model = FakeStreamingModel(flowOf(response("a", FinishReason.MAX_TOKENS, TOKEN_1)))

    streamWithAutomaticContinuation(CONFIG) { model.send(it) }.toList()

    assertEquals(1, model.configs.size)
  }

  @Test
  fun testStreamAccumulatesUsageMetadataAcrossHops() = runTest {
    val model =
      FakeStreamingModel(
        // Hop 1: checkpoint chunk with usage, then stream drops mid-hop
        flow {
          emit(
            response("a", token = TOKEN_1)
              .copy(
                usageMetadata =
                  GenerateContentResponseUsageMetadata(
                    promptTokenCount = 10,
                    cachedContentTokenCount = 4,
                    candidatesTokenCount = 40,
                    thoughtsTokenCount = 5,
                    totalTokenCount = 55,
                    promptTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 10)),
                    candidatesTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 40)),
                  )
              )
          )
          throw IllegalStateException("stream dropped")
        },
        // Hop 2: finishes with CONTINUATION and TOKEN_2
        flowOf(
          response("b"),
          response("c", FinishReason.CONTINUATION, TOKEN_2)
            .copy(
              usageMetadata =
                GenerateContentResponseUsageMetadata(
                  promptTokenCount = 55,
                  cachedContentTokenCount = 50,
                  candidatesTokenCount = 60,
                  thoughtsTokenCount = 3,
                  totalTokenCount = 118,
                  promptTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 55)),
                  candidatesTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 60)),
                )
            ),
        ),
        // Hop 3: finishes with STOP
        flowOf(
          response("d", FinishReason.STOP)
            .copy(
              usageMetadata =
                GenerateContentResponseUsageMetadata(
                  promptTokenCount = 118,
                  cachedContentTokenCount = 110,
                  candidatesTokenCount = 25,
                  thoughtsTokenCount = 2,
                  totalTokenCount = 145,
                  promptTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 118)),
                  candidatesTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 25)),
                )
            )
        ),
      )

    val chunks = streamWithAutomaticContinuation(CONFIG) { model.send(it) }.toList()

    assertEquals(listOf<String?>("a", "b", "c", "d"), chunks.map { it.text })
    // Chunk 0 (Hop 1): unchanged initial hop usage
    assertEquals(10, chunks[0].usageMetadata?.promptTokenCount)
    assertEquals(10, chunks[0].usageMetadata?.initialPromptTokenCount)
    assertEquals(10, chunks[0].initialPromptTokenCount)
    assertEquals(4, chunks[0].usageMetadata?.cachedContentTokenCount)
    assertEquals(40, chunks[0].usageMetadata?.candidatesTokenCount)
    assertEquals(5, chunks[0].usageMetadata?.thoughtsTokenCount)
    assertEquals(55, chunks[0].usageMetadata?.totalTokenCount)
    // Chunk 1 (Hop 2 first chunk): no usageMetadata
    assertNull(chunks[1].usageMetadata)
    assertNull(chunks[1].initialPromptTokenCount)
    // Chunk 2 (Hop 2 final chunk): accumulated across Hop 1 + Hop 2
    assertEquals(65, chunks[2].usageMetadata?.promptTokenCount)
    assertEquals(10, chunks[2].usageMetadata?.initialPromptTokenCount)
    assertEquals(10, chunks[2].initialPromptTokenCount)
    assertEquals(54, chunks[2].usageMetadata?.cachedContentTokenCount)
    assertEquals(100, chunks[2].usageMetadata?.candidatesTokenCount)
    assertEquals(8, chunks[2].usageMetadata?.thoughtsTokenCount)
    assertEquals(173, chunks[2].usageMetadata?.totalTokenCount)
    // Chunk 3 (Hop 3 final chunk): accumulated across Hop 1 + Hop 2 + Hop 3
    val finalUsage = chunks[3].usageMetadata
    assertEquals(183, finalUsage?.promptTokenCount)
    assertEquals(10, finalUsage?.initialPromptTokenCount)
    assertEquals(10, chunks[3].initialPromptTokenCount)
    assertEquals(164, finalUsage?.cachedContentTokenCount)
    assertEquals(125, finalUsage?.candidatesTokenCount)
    assertEquals(10, finalUsage?.thoughtsTokenCount)
    assertEquals(318, finalUsage?.totalTokenCount)
    assertEquals(
      listOf(ModalityTokenCount(MediaModality.TEXT, 183)),
      finalUsage?.promptTokensDetails,
    )
    assertEquals(
      listOf(ModalityTokenCount(MediaModality.TEXT, 125)),
      finalUsage?.candidatesTokensDetails,
    )
  }

  @Test
  fun testMergeReturnsASingleResponseAsIs() {
    val only = response("Hello", FinishReason.STOP)

    assertSame(only, mergeContinuationResponses(listOf(only)))
  }

  @Test
  fun testMergeConcatenatesPartsAndOtherLists() {
    val first =
      response("Hello ", FinishReason.CONTINUATION, TOKEN_1).withFirstCandidate {
        copy(citationMetadata = CitationMetadata(citations = listOf(Citation(uri = "a"))))
      }
    val second =
      response("world", FinishReason.STOP).withFirstCandidate {
        copy(citationMetadata = CitationMetadata(citations = listOf(Citation(uri = "b"))))
      }

    val merged = mergeContinuationResponses(listOf(first, second))

    assertEquals(listOf<String?>("Hello ", "world"), merged.parts?.map { it.text })
    assertEquals(
      listOf<String?>("a", "b"),
      merged.candidates?.first()?.citationMetadata?.citations?.map { it.uri },
    )
  }

  @Test
  fun testKeepsAnEmptyTextPartFromARequestSpentThinking() = runTest {
    val signature = "signature".encodeToByteArray()
    val thinking =
      GenerateContentResponse(
        candidates =
          listOf(
            Candidate(
              content =
                Content(
                  role = "model",
                  parts = listOf(Part(text = "", thoughtSignature = signature)),
                ),
              finishReason = FinishReason.CONTINUATION,
              continuationToken = TOKEN_1,
            )
          )
      )
    val model = FakeModel(thinking, response("Final answer.", FinishReason.STOP))

    val merged = generateWithAutomaticContinuation(CONFIG) { model.send(it) }

    assertEquals("Final answer.", merged.text)
    val parts = merged.parts!!
    assertEquals(2, parts.size)
    assertEquals("", parts[0].text)
    assertContentEquals(signature, parts[0].thoughtSignature)
    assertEquals("Final answer.", parts[1].text)
  }

  @Test
  fun testMergeSumsTokenCounts() {
    val first =
      response("Hello ", FinishReason.CONTINUATION, TOKEN_1)
        .copy(
          usageMetadata =
            GenerateContentResponseUsageMetadata(
              promptTokenCount = 10,
              cachedContentTokenCount = 4,
              candidatesTokenCount = 100,
              thoughtsTokenCount = 5,
              totalTokenCount = 115,
              promptTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 10)),
              cacheTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 4)),
              candidatesTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 100)),
            )
        )
    val second =
      response("world", FinishReason.STOP)
        .copy(
          usageMetadata =
            GenerateContentResponseUsageMetadata(
              promptTokenCount = 110,
              cachedContentTokenCount = 100,
              candidatesTokenCount = 50,
              totalTokenCount = 160,
              promptTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 110)),
              cacheTokensDetails = listOf(ModalityTokenCount(MediaModality.TEXT, 100)),
              candidatesTokensDetails =
                listOf(
                  ModalityTokenCount(MediaModality.TEXT, 50),
                  ModalityTokenCount(MediaModality.IMAGE, 3),
                ),
            )
        )

    val merged = mergeContinuationResponses(listOf(first, second))
    val usage = merged.usageMetadata

    assertEquals(120, usage?.promptTokenCount)
    assertEquals(10, merged.initialPromptTokenCount)
    assertEquals(10, usage?.initialPromptTokenCount)
    assertEquals(104, usage?.cachedContentTokenCount)
    assertEquals(150, usage?.candidatesTokenCount)
    assertEquals(5, usage?.thoughtsTokenCount)
    assertEquals(275, usage?.totalTokenCount)
    assertEquals(listOf(ModalityTokenCount(MediaModality.TEXT, 120)), usage?.promptTokensDetails)
    assertEquals(listOf(ModalityTokenCount(MediaModality.TEXT, 104)), usage?.cacheTokensDetails)
    assertEquals(
      listOf(
        ModalityTokenCount(MediaModality.TEXT, 150),
        ModalityTokenCount(MediaModality.IMAGE, 3),
      ),
      usage?.candidatesTokensDetails,
    )
  }

  @Test
  fun testMergeTakesHowTheGenerationEndedFromTheLastResponse() {
    val first =
      response("Hello ", FinishReason.CONTINUATION, TOKEN_1).withFirstCandidate {
        copy(finishMessage = "Stopped early.")
      }
    val second = response("world", FinishReason.STOP)

    val candidate = mergeContinuationResponses(listOf(first, second)).candidates?.first()

    assertEquals(FinishReason.STOP, candidate?.finishReason)
    assertNull(candidate?.continuationToken)
    assertNull(candidate?.finishMessage)
  }

  @Test
  fun testMergeKeepsTheLatestSafetyRatingPerCategory() {
    val first =
      response("Hello ", FinishReason.CONTINUATION, TOKEN_1).withFirstCandidate {
        copy(
          safetyRatings =
            listOf(
              SafetyRating(
                category = HarmCategory.HARM_CATEGORY_HARASSMENT,
                probability = HarmProbability.LOW,
              ),
              SafetyRating(
                category = HarmCategory.HARM_CATEGORY_HATE_SPEECH,
                probability = HarmProbability.LOW,
              ),
            )
        )
      }
    val second =
      response("world", FinishReason.STOP).withFirstCandidate {
        copy(
          safetyRatings =
            listOf(
              SafetyRating(
                category = HarmCategory.HARM_CATEGORY_HARASSMENT,
                probability = HarmProbability.MEDIUM,
              )
            )
        )
      }

    val ratings =
      mergeContinuationResponses(listOf(first, second)).candidates?.first()?.safetyRatings

    assertEquals(
      listOf(
        SafetyRating(
          category = HarmCategory.HARM_CATEGORY_HARASSMENT,
          probability = HarmProbability.MEDIUM,
        ),
        SafetyRating(
          category = HarmCategory.HARM_CATEGORY_HATE_SPEECH,
          probability = HarmProbability.LOW,
        ),
      ),
      ratings,
    )
  }

  @Test
  fun testMergeTakesOtherValuesFromTheLastResponseUnlessUnset() {
    val first =
      response("Hello ", FinishReason.CONTINUATION, TOKEN_1)
        .copy(
          modelVersion = "model-1",
          responseId = "first",
          sdkHttpResponse = HttpResponse(body = "first"),
        )
    val second =
      response("world", FinishReason.STOP)
        .copy(responseId = "second", sdkHttpResponse = HttpResponse(body = "second"))

    val merged = mergeContinuationResponses(listOf(first, second))

    assertEquals("model-1", merged.modelVersion)
    assertEquals("second", merged.responseId)
    assertEquals(HttpResponse(body = "second"), merged.sdkHttpResponse)
  }

  @Test
  fun testMergeMergesEachCandidateWithTheOneOfTheSameIndex() {
    val first =
      GenerateContentResponse(
        candidates =
          listOf(
            candidate(0, "Hello ", FinishReason.CONTINUATION),
            candidate(1, "Bonjour ", FinishReason.CONTINUATION),
            candidate(2, "Hola", FinishReason.STOP),
          )
      )
    // Listed out of order, so the candidates can only be matched by index.
    val second =
      GenerateContentResponse(
        candidates =
          listOf(
            candidate(1, "le monde", FinishReason.STOP),
            candidate(0, "world", FinishReason.STOP),
          )
      )

    val candidates = mergeContinuationResponses(listOf(first, second)).candidates

    assertEquals(
      listOf("Hello world", "Bonjour le monde", "Hola"),
      candidates?.map { candidate -> candidate.content?.parts?.joinToString("") { it.text!! } },
    )
    assertEquals(FinishReason.STOP, candidates?.get(1)?.finishReason)
  }
}

private fun candidate(index: Int, text: String, finishReason: FinishReason): Candidate =
  Candidate(
    index = index,
    content = Content(role = "model", parts = listOf(Part(text = text))),
    finishReason = finishReason,
  )

private fun GenerateContentResponse.withFirstCandidate(
  change: Candidate.() -> Candidate
): GenerateContentResponse = copy(candidates = listOf(candidates!!.first().change()))
