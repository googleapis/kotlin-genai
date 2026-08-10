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
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.FileData
import com.google.genai.kotlin.types.FunctionCall
import com.google.genai.kotlin.types.FunctionResponse
import com.google.genai.kotlin.types.GenerateContentResponse
import com.google.genai.kotlin.types.Part
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private fun userTurn(text: String) = Content(role = "user", parts = listOf(Part(text = text)))

private fun modelTurn(text: String) = Content(role = "model", parts = listOf(Part(text = text)))

private fun invalidUserTurn() = Content(role = "user", parts = emptyList())

private fun invalidModelTurn() = Content(role = "model", parts = emptyList())

private const val TEST_MODEL = "gemini-3.6-flash"

/** Shared across the session tests; none of them issue a request. */
private val testModels = Models(ApiClient(apiKey = "test-api-key"))

private fun testChats() = Chats(testModels)

private fun testChat(history: List<Content> = emptyList()) =
  testChats().create(model = TEST_MODEL, history = history)

class ChatsUnitTest {

  @Test
  fun testExtractCuratedHistoryReturnsEmptyForEmptyHistory() {
    assertEquals(emptyList(), extractCuratedHistory(emptyList()))
  }

  @Test
  fun testExtractCuratedHistoryThrowsForInvalidRole() {
    val history = listOf(Content(role = "assistant", parts = listOf(Part(text = "Hi"))))

    val exception = assertFailsWith<IllegalArgumentException> { extractCuratedHistory(history) }
    assertEquals("Role must be user or model, but got assistant.", exception.message)
  }

  @Test
  fun testExtractCuratedHistoryThrowsForMissingRole() {
    // Content.role defaults to null, so omitting it is an easy mistake and earns its own message.
    val history = listOf(Content(parts = listOf(Part(text = "Hi"))))

    val exception = assertFailsWith<IllegalArgumentException> { extractCuratedHistory(history) }
    assertEquals(
      "Every turn in the history must set a role of user or model, but one turn left it unset. " +
        "Content.role defaults to null, so it has to be passed explicitly.",
      exception.message,
    )
  }

  @Test
  fun testExtractCuratedHistoryAcceptsHistoryStartingWithModelTurn() {
    // Unlike the Java SDK, the first turn is not required to come from the user.
    val history = listOf(modelTurn("Hello!"), userTurn("Hi"))

    assertEquals(history, extractCuratedHistory(history))
  }

  @Test
  fun testExtractCuratedHistoryKeepsUserTurnsWithInvalidContent() {
    val history = listOf(invalidUserTurn(), modelTurn("Hello!"))

    assertEquals(history, extractCuratedHistory(history))
  }

  @Test
  fun testExtractCuratedHistoryDropsInvalidModelTurnAndItsUserTurn() {
    val history = listOf(userTurn("Hi"), invalidModelTurn())

    assertEquals(emptyList(), extractCuratedHistory(history))
  }

  @Test
  fun testExtractCuratedHistoryDropsEveryUserTurnOfAFailedTurn() {
    // sendMessage accepts several contents for one turn, and withholds all of them from the
    // curated history when the response is invalid. Re-deriving the curated history from the
    // comprehensive one has to drop the same contents, or a resumed session would send a user
    // turn that the session it came from never had.
    val history = listOf(userTurn("Part one"), userTurn("Part two"), invalidModelTurn())

    assertEquals(emptyList(), extractCuratedHistory(history))
  }

  @Test
  fun testExtractCuratedHistoryDropsEntireModelRunWhenOneTurnIsInvalid() {
    val history =
      listOf(
        userTurn("First question"),
        modelTurn("First answer"),
        userTurn("Second question"),
        modelTurn("Second answer, chunk one"),
        invalidModelTurn(),
        userTurn("Third question"),
        modelTurn("Third answer"),
      )

    assertEquals(
      listOf(
        userTurn("First question"),
        modelTurn("First answer"),
        userTurn("Third question"),
        modelTurn("Third answer"),
      ),
      extractCuratedHistory(history),
    )
  }

  @Test
  fun testExtractCuratedHistoryDoesNotThrowWhenLeadingModelTurnIsInvalid() {
    // There is no preceding user turn to drop, so the removal must be guarded.
    val history = listOf(invalidModelTurn(), userTurn("Hi"), modelTurn("Hello!"))

    assertEquals(listOf(userTurn("Hi"), modelTurn("Hello!")), extractCuratedHistory(history))
  }

  @Test
  fun testExtractCuratedHistoryKeepsTrailingUserTurn() {
    val history = listOf(userTurn("Hi"), modelTurn("Hello!"), userTurn("How are you?"))

    assertEquals(history, extractCuratedHistory(history))
  }

  @Test
  fun testExtractCuratedHistoryKeepsConsecutiveModelTurns() {
    // A streamed turn is recorded as one Content per chunk.
    val history =
      listOf(userTurn("Hi"), modelTurn("Hel"), modelTurn("lo"), modelTurn("!"), userTurn("Bye"))

    assertEquals(history, extractCuratedHistory(history))
  }

  @Test
  fun testIsValidContent() {
    assertFalse(isValidContent(null))
    assertFalse(isValidContent(Content(role = "user", parts = null)))
    assertFalse(isValidContent(Content(role = "user", parts = emptyList())))
    assertFalse(isValidContent(Content(role = "user", parts = listOf(Part()))))
    assertFalse(isValidContent(Content(role = "user", parts = listOf(Part(text = "Hi"), Part()))))

    assertTrue(isValidContent(Content(role = "user", parts = listOf(Part(text = "Hi")))))
    // An empty string is still a populated part.
    assertTrue(isValidContent(Content(role = "user", parts = listOf(Part(text = "")))))
  }

  @Test
  fun testIsValidContentAcceptsNonTextParts() {
    // Validity is "the part is not empty", so any populated field counts. Note this is looser than
    // the Go SDK, which enumerates the fields it accepts and would reject a thought-only part.
    assertTrue(
      isValidContent(
        Content(role = "model", parts = listOf(Part(functionCall = FunctionCall(name = "f"))))
      )
    )
    assertTrue(
      isValidContent(
        Content(
          role = "user",
          parts = listOf(Part(functionResponse = FunctionResponse(name = "f"))),
        )
      )
    )
    assertTrue(
      isValidContent(
        Content(
          role = "user",
          parts = listOf(Part(fileData = FileData(fileUri = "gs://bucket/file.txt"))),
        )
      )
    )
    assertTrue(
      isValidContent(Content(role = "model", parts = listOf(Part(thought = true, text = "Hmm"))))
    )
  }

  @Test
  fun testIsValidResponse() {
    assertFalse(isValidResponse(GenerateContentResponse()))
    assertFalse(isValidResponse(GenerateContentResponse(candidates = emptyList())))
    assertFalse(isValidResponse(GenerateContentResponse(candidates = listOf(Candidate()))))
    assertFalse(
      isValidResponse(
        GenerateContentResponse(candidates = listOf(Candidate(content = invalidModelTurn())))
      )
    )

    assertTrue(
      isValidResponse(
        GenerateContentResponse(candidates = listOf(Candidate(content = modelTurn("Hello!"))))
      )
    )
  }

  @Test
  fun testIsValidResponseOnlyConsidersTheFirstCandidate() {
    val response =
      GenerateContentResponse(
        candidates =
          listOf(Candidate(content = modelTurn("Hello!")), Candidate(content = invalidModelTurn()))
      )

    assertTrue(isValidResponse(response))
  }

  @Test
  fun testNewSessionHasEmptyHistory() {
    val chat = testChat()

    assertEquals(emptyList(), chat.getHistory())
    assertEquals(emptyList(), chat.getHistory(curated = true))
  }

  @Test
  fun testSeededHistoryIsReturned() {
    val seed = listOf(userTurn("Hi"), modelTurn("Hello!"))

    assertEquals(seed, testChat(seed).getHistory())
  }

  @Test
  fun testSeededHistoryIsCurated() {
    val chat = testChat(listOf(userTurn("Hi"), invalidModelTurn(), userTurn("Still there?")))

    assertEquals(3, chat.getHistory().size)
    assertEquals(listOf(userTurn("Still there?")), chat.getHistory(curated = true))
  }

  @Test
  fun testCreateRejectsSeededHistoryWithInvalidRole() {
    val seed = listOf(Content(role = "assistant", parts = listOf(Part(text = "Hi"))))

    val exception = assertFailsWith<IllegalArgumentException> { testChat(seed) }
    assertEquals("Role must be user or model, but got assistant.", exception.message)
  }

  @Test
  fun testSeededHistoryIsCopiedIn() {
    val seed = mutableListOf(userTurn("Hi"))
    val chat = testChat(seed)

    seed.add(modelTurn("Hello!"))

    assertEquals(listOf(userTurn("Hi")), chat.getHistory())
  }

  @Test
  fun testGetHistoryIsCopiedOut() {
    val seed = listOf(userTurn("Hi"), modelTurn("Hello!"))
    val chat = testChat(seed)

    // A read-only List can still be cast and mutated, so the snapshot has to be a copy of the
    // session's list rather than the list itself. Two turns are used because toList() returns an
    // immutable singleton for shorter lists, which would pass the assertion for the wrong reason.
    @Suppress("UNCHECKED_CAST") (chat.getHistory() as MutableList<Content>).clear()

    assertEquals(seed, chat.getHistory())
  }

  @Test
  fun testClientExposesChats() {
    val environment = mockk<Environment>()
    every { environment.get(any()) } returns null

    Client(apiKey = "test-api-key", environment = environment).use { client ->
      val chat = client.chats.create(model = TEST_MODEL, history = listOf(userTurn("Hi")))

      assertEquals(listOf(userTurn("Hi")), chat.getHistory())
    }
  }
}
