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
import com.google.genai.kotlin.types.FinishReason
import com.google.genai.kotlin.types.FunctionCall
import com.google.genai.kotlin.types.FunctionDeclaration
import com.google.genai.kotlin.types.FunctionResponse
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.GenerateContentResponse
import com.google.genai.kotlin.types.GoogleSearch
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.Tool
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonPrimitive

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

private fun callTurn(name: String = "get_weather") =
  Content(role = "model", parts = listOf(Part(functionCall = FunctionCall(name = name))))

private fun responseTurn(name: String = "get_weather") =
  Content(
    role = "user",
    parts =
      listOf(
        Part(
          functionResponse =
            FunctionResponse(name = name, response = mapOf("result" to JsonPrimitive("sunny")))
        )
      ),
  )

private fun weatherTool(): CallableFunction =
  callableFunction("get_weather", paramName = "city") { city: String -> "sunny in $city" }

private fun weatherAfc(): AutomaticFunctionCalling = AutomaticFunctionCalling(weatherTool())

private fun functionCallResponse(
  name: String,
  vararg args: Pair<String, String>,
  id: String? = null,
  finished: Boolean = false,
): GenerateContentResponse =
  GenerateContentResponse(
    candidates =
      listOf(
        Candidate(
          content =
            Content(
              role = "model",
              parts =
                listOf(
                  Part(
                    functionCall =
                      FunctionCall(
                        id = id,
                        name = name,
                        args = args.associate { it.first to JsonPrimitive(it.second) },
                      )
                  )
                ),
            ),
          finishReason = if (finished) FinishReason.STOP else null,
        )
      )
  )

private fun textResponse(text: String, finished: Boolean = false): GenerateContentResponse =
  GenerateContentResponse(
    candidates =
      listOf(
        Candidate(
          content = modelTurn(text),
          finishReason = if (finished) FinishReason.STOP else null,
        )
      )
  )

private fun hasFunctionResponse(candidate: Candidate): Boolean =
  candidate.content?.parts.orEmpty().any { it.functionResponse != null }

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

  @Test
  fun testStreamWithNoFinishReasonIsNotCurated() = runTest {
    // A stream that ends without the model reporting a finish reason is what a cut-off response
    // looks like to the SDK. Mocked rather than recorded, because a live model always reports one.
    val models = mockk<Models>()
    every { models.generateContentStream(any<String>(), any<List<Content>>(), any()) } returns
      flowOf(GenerateContentResponse(candidates = listOf(Candidate(content = modelTurn("Paris")))))
    val chat = Chat(models, TEST_MODEL, null, emptyList())

    chat.sendMessageStream("What is the capital of France?").toList()

    // The turn is remembered, but it is not worth sending back to the model.
    assertEquals(2, chat.getHistory().size)
    assertEquals(emptyList(), chat.getHistory(curated = true))
  }

  @Test
  fun testBlockedResponseIsNotCurated() = runTest {
    // A candidate with no content is what a safety block looks like to the SDK. Mocked rather than
    // recorded, because a live model will not produce one on demand.
    val models = mockk<Models>()
    coEvery { models.generateContent(any<String>(), any<List<Content>>(), any()) } returnsMany
      listOf(
        GenerateContentResponse(candidates = listOf(Candidate(content = modelTurn("Paris")))),
        GenerateContentResponse(candidates = listOf(Candidate())),
      )
    val chat = Chat(models, TEST_MODEL, null, emptyList())

    chat.sendMessage("What is the capital of France?")
    assertEquals(2, chat.getHistory().size)
    assertEquals(2, chat.getHistory(curated = true).size)

    chat.sendMessage("And what is the capital of Germany?")

    // The blocked turn is remembered, but it is not worth sending back to the model.
    assertEquals(4, chat.getHistory().size)
    assertEquals(2, chat.getHistory(curated = true).size)
    assertTrue(chat.getHistory()[3].parts.isNullOrEmpty())
  }

  @Test
  fun testUnknownFunctionBecomesAnErrorForTheModel() = runTest {
    val models = mockk<Models>()
    coEvery { models.generateContent(any<String>(), any<List<Content>>(), any()) } returnsMany
      listOf(functionCallResponse("not_registered", "city" to "Zurich"), textResponse("I cannot."))
    val chat = Chat(models, TEST_MODEL, null, emptyList(), weatherAfc())

    chat.sendMessage("Weather?")

    val sent = chat.getHistory()[2].parts!!.first().functionResponse!!
    assertTrue(
      (sent.response!!["error"] as JsonPrimitive).content.contains("not_registered"),
      sent.response.toString(),
    )
  }

  @Test
  fun testCallerToolsSurviveAlongsideAfcFunctions() = runTest {
    val models = mockk<Models>()
    val configs = mutableListOf<GenerateContentConfig?>()
    coEvery { models.generateContent(any<String>(), any<List<Content>>(), any()) } answers
      {
        configs.add(arg(2))
        textResponse("Hi.")
      }
    val callerConfig = GenerateContentConfig(tools = listOf(Tool(googleSearch = GoogleSearch())))
    val chat = Chat(models, TEST_MODEL, callerConfig, emptyList(), weatherAfc())

    chat.sendMessage("Hello")

    val tools = configs.single()!!.tools!!
    // Ours is added to the caller's rather than replacing them.
    assertNotNull(tools.first().googleSearch)
    assertEquals(
      listOf("get_weather"),
      tools.flatMap { it.functionDeclarations.orEmpty() }.map { it.name },
    )
  }

  @Test
  fun testParallelFunctionsAnswerInCallOrder() = runTest {
    // The first call is made to finish last, so that answering in completion order would produce
    // the opposite list. With handlers of equal speed this assertion would hold either way.
    val staggered =
      callableFunction("get_weather", paramName = "city") { city: String ->
        delay(if (city == "Oslo") 100 else 10)
        "sunny in $city"
      }
    val models = mockk<Models>()
    coEvery { models.generateContent(any<String>(), any<List<Content>>(), any()) } returnsMany
      listOf(
        GenerateContentResponse(
          candidates =
            listOf(
              Candidate(
                content =
                  Content(
                    role = "model",
                    parts =
                      listOf(
                        Part(
                          functionCall =
                            FunctionCall(
                              id = "a",
                              name = "get_weather",
                              args = mapOf("city" to JsonPrimitive("Oslo")),
                            )
                        ),
                        Part(
                          functionCall =
                            FunctionCall(
                              id = "b",
                              name = "get_weather",
                              args = mapOf("city" to JsonPrimitive("Rome")),
                            )
                        ),
                      ),
                  )
              )
            )
        ),
        textResponse("Both done."),
      )
    val chat =
      Chat(
        models,
        TEST_MODEL,
        null,
        emptyList(),
        AutomaticFunctionCalling(listOf(staggered), runFunctionsInParallel = true),
      )

    chat.sendMessage("Weather in Oslo and Rome?")

    // Whichever handler finished first, the responses line up with the calls that asked for them.
    val responses = chat.getHistory()[2].parts!!.map { it.functionResponse!! }
    assertEquals(listOf("a", "b"), responses.map { it.id })
    assertEquals(
      listOf("sunny in Oslo", "sunny in Rome"),
      responses.map { (it.response!!["result"] as JsonPrimitive).content },
    )
  }

  @Test
  fun testRawFunctionDeclarationsRejectedOnATurn() = runTest {
    val models = mockk<Models>()
    val chat = Chat(models, TEST_MODEL, null, emptyList(), weatherAfc())
    val config =
      GenerateContentConfig(
        tools = listOf(Tool(functionDeclarations = listOf(FunctionDeclaration(name = "by_hand"))))
      )

    val error = assertFailsWith<IllegalArgumentException> { chat.sendMessage("Hi", config) }
    assertTrue(error.message!!.contains("by_hand"), error.message!!)
  }

  @Test
  fun testFailedFunctionCallingTurnUnwindsEntirely() {
    // The model's answer came back blocked, so the whole turn goes: leaving the call behind would
    // put a functionCall with no functionResponse into the next request.
    val history = listOf(userTurn("Weather?"), callTurn(), responseTurn(), invalidModelTurn())

    assertEquals(emptyList(), extractCuratedHistory(history))
  }

  @Test
  fun testCompletedFunctionCallingTurnSurvivesAFailedOneAfterIt() {
    val completed =
      listOf(userTurn("Weather?"), callTurn(), responseTurn(), modelTurn("It is sunny."))
    val failed = listOf(userTurn("And tomorrow?"), callTurn(), responseTurn(), invalidModelTurn())

    assertEquals(completed, extractCuratedHistory(completed + failed))
  }

  @Test
  fun testCancellationIsRethrownRatherThanReportedToTheModel() = runTest {
    // CancellationException is an Exception, so the broad catch around a handler would otherwise
    // swallow it, tell the model the function failed, and keep the loop running in a dead scope.
    val cancelling =
      callableFunction<String, String>("get_weather", paramName = "city") {
        throw CancellationException("the turn was cancelled")
      }
    val models = mockk<Models>()
    coEvery { models.generateContent(any<String>(), any<List<Content>>(), any()) } returns
      functionCallResponse("get_weather", "city" to "Zurich")
    val chat = Chat(models, TEST_MODEL, null, emptyList(), AutomaticFunctionCalling(cancelling))

    assertFailsWith<CancellationException> { chat.sendMessage("Weather?") }

    // Nothing was committed, so the session is left as it was rather than half a turn in.
    assertEquals(emptyList(), chat.getHistory())
  }

  @Test
  fun testPerTurnFunctionsReplaceTheSessionOnes() = runTest {
    val models = mockk<Models>()
    val configs = mutableListOf<GenerateContentConfig?>()
    coEvery { models.generateContent(any<String>(), any<List<Content>>(), any()) } answers
      {
        configs.add(arg(2))
        textResponse("Done.")
      }
    val chat =
      Chat(
        models,
        TEST_MODEL,
        null,
        emptyList(),
        AutomaticFunctionCalling(callableFunction("session_function") { -> "a" }),
      )

    chat.sendMessage(
      "Run",
      automaticFunctionCalling =
        AutomaticFunctionCalling(callableFunction("turn_function") { -> "b" }),
    )

    // The turn's functions replace the session's rather than adding to them.
    assertEquals(
      listOf("turn_function"),
      configs.single()!!.tools!!.flatMap { it.functionDeclarations.orEmpty() }.map { it.name },
    )
  }

  @Test
  fun testSessionFunctionsApplyWhenTheTurnPassesNone() = runTest {
    // The other half of the precedence rule: null on a turn inherits rather than turning AFC off.
    val models = mockk<Models>()
    val configs = mutableListOf<GenerateContentConfig?>()
    coEvery { models.generateContent(any<String>(), any<List<Content>>(), any()) } answers
      {
        configs.add(arg(2))
        textResponse("Done.")
      }
    val chat = Chat(models, TEST_MODEL, null, emptyList(), weatherAfc())

    chat.sendMessage("Run")

    assertEquals(
      listOf("get_weather"),
      configs.single()!!.tools!!.flatMap { it.functionDeclarations.orEmpty() }.map { it.name },
    )
  }

  @Test
  fun testAbandoningAStreamMidLoopRecordsNothing() = runTest {
    // Taking only the first chunk leaves the loop unfinished. Nothing is committed, so the tool
    // call that was already run does not reach the history on its own.
    val models = mockk<Models>()
    every { models.generateContentStream(any<String>(), any<List<Content>>(), any()) } returnsMany
      listOf(
        flowOf(functionCallResponse("get_weather", "city" to "Zurich", finished = true)),
        flowOf(textResponse("It is sunny.", finished = true)),
      )
    val chat = Chat(models, TEST_MODEL, null, emptyList(), weatherAfc())

    chat.sendMessageStream("Weather?").take(1).toList()

    assertEquals(emptyList(), chat.getHistory())
    assertEquals(emptyList(), chat.getHistory(curated = true))
  }

  @Test
  fun testTwoFunctionRoundsNeedABudgetOfThree() = runTest {
    // The budget counts requests, and the first one counts, so two rounds of function calling need
    // three: ask, deliver-and-ask-again, deliver-and-answer.
    val models = mockk<Models>()
    coEvery { models.generateContent(any<String>(), any<List<Content>>(), any()) } returnsMany
      listOf(
        functionCallResponse("get_weather", "city" to "Oslo"),
        functionCallResponse("get_weather", "city" to "Rome"),
        textResponse("Both looked up."),
      )
    val chat =
      Chat(
        models,
        TEST_MODEL,
        null,
        emptyList(),
        AutomaticFunctionCalling(listOf(weatherTool()), maximumRemoteCalls = 3),
      )

    val response = chat.sendMessage("Weather in Oslo and Rome?")

    assertEquals("Both looked up.", response.text)
    assertEquals(
      listOf("user", "model", "user", "model", "user", "model"),
      chat.getHistory().map { it.role },
    )
  }

  @Test
  fun testTheBudgetStopsTheTurnAfterTheLastAllowedRequest() = runTest {
    // The handler records that it ran, so this pins which round the budget cut off rather than
    // only the shape of the history.
    val invokedFor = mutableListOf<String>()
    val recording =
      callableFunction("get_weather", paramName = "city") { city: String ->
        invokedFor += city
        "sunny in $city"
      }
    val models = mockk<Models>()
    coEvery { models.generateContent(any<String>(), any<List<Content>>(), any()) } returnsMany
      listOf(
        functionCallResponse("get_weather", "city" to "Oslo"),
        functionCallResponse("get_weather", "city" to "Rome"),
        textResponse("Both looked up."),
      )
    val chat =
      Chat(
        models,
        TEST_MODEL,
        null,
        emptyList(),
        AutomaticFunctionCalling(listOf(recording), maximumRemoteCalls = 1),
      )

    val response = chat.sendMessage("Weather in Oslo and Rome?")

    // The one request was spent being asked for Oslo. Nothing was left to deliver a result with, so
    // the function never ran and the turn ends on the model's unanswered call.
    assertEquals(emptyList(), invokedFor)
    assertEquals(JsonPrimitive("Oslo"), response.functionCalls!!.single().args!!["city"])
    assertNull(response.text)
    assertEquals(listOf("user", "model"), chat.getHistory().map { it.role })
  }

  @Test
  fun testTheCallerCanAnswerTheCallLeftByASpentBudget() = runTest {
    // A spent budget hands the call back unanswered, so the caller's own response pairs with it
    // instead of arriving alongside one the session already answered on their behalf.
    val models = mockk<Models>()
    val sent = mutableListOf<List<Content>>()
    coEvery { models.generateContent(any<String>(), any<List<Content>>(), any()) } answers
      {
        sent += secondArg<List<Content>>()
        functionCallResponse("get_weather", "city" to "Oslo")
      }
    val chat =
      Chat(
        models,
        TEST_MODEL,
        null,
        emptyList(),
        AutomaticFunctionCalling(listOf(weatherTool()), maximumRemoteCalls = 1),
      )

    chat.sendMessage("Weather in Oslo?")
    chat.sendMessage(listOf(responseTurn()))

    assertEquals(listOf("user", "model", "user"), sent[1].map { it.role })
    assertEquals(1, sent[1].flatMap { it.parts.orEmpty() }.count { it.functionResponse != null })
  }
}
