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

import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.Part
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable

private const val MODEL_NAME = "gemini-3.6-flash"

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 60.seconds, testBody = testBody)

class ChatsTest : BaseTestServer() {

  @Test
  fun testSendMessageSimple() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client = createClient(enterprise, "ChatsTest.testSendMessageSimple.$suffix")

      val chat = client.chats.create(model = MODEL_NAME)
      val response = chat.sendMessage("What is the capital of France?")

      assertContains(response.text ?: "", "Paris")
      assertEquals(2, chat.getHistory().size)
      assertEquals("user", chat.getHistory()[0].role)

      // A history this session produced has to be one that create() accepts back, and has to come
      // back unchanged; create() throws on a turn whose role was left unset.
      val resumed = client.chats.create(model = MODEL_NAME, history = chat.getHistory())
      assertEquals(chat.getHistory(), resumed.getHistory())
      assertEquals("model", chat.getHistory()[1].role)
    }
  }

  @Test
  fun testMultiTurnChatSendsPriorTurns() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client = createClient(enterprise, "ChatsTest.testMultiTurnChatSendsPriorTurns.$suffix")

      val chat = client.chats.create(model = MODEL_NAME)
      chat.sendMessage("My favourite colour is blue. Remember it.")
      assertEquals(2, chat.getHistory().size)

      // Answering this requires the first turn to have been sent along with the second.
      val response = chat.sendMessage("What is my favourite colour? Answer with one word.")

      assertContains((response.text ?: "").lowercase(), "blue")
      assertEquals(4, chat.getHistory().size)
    }
  }

  @Test
  fun testSendMessageContent() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client = createClient(enterprise, "ChatsTest.testSendMessageContent.$suffix")

      val chat = client.chats.create(model = MODEL_NAME)
      // The role is left unset here on purpose: it is filled in as "user" before the request goes
      // out, so the wire format matches the role-carrying overloads.
      val response =
        chat.sendMessage(Content(parts = listOf(Part(text = "What is the capital of France?"))))

      assertContains(response.text ?: "", "Paris")
      assertEquals(2, chat.getHistory().size)
      assertEquals("user", chat.getHistory()[0].role)

      // A history this session produced has to be one that create() accepts back, and has to come
      // back unchanged; create() throws on a turn whose role was left unset.
      val resumed = client.chats.create(model = MODEL_NAME, history = chat.getHistory())
      assertEquals(chat.getHistory(), resumed.getHistory())
    }
  }

  @Test
  fun testSendMessageContentList() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client = createClient(enterprise, "ChatsTest.testSendMessageContentList.$suffix")

      val chat = client.chats.create(model = MODEL_NAME)
      val response =
        chat.sendMessage(
          listOf(
            Content(role = "user", parts = listOf(Part(text = "The capital of France is:"))),
            Content(role = "user", parts = listOf(Part(text = "Answer with one word."))),
          )
        )

      assertContains(response.text ?: "", "Paris")
      // Both user contents are recorded, followed by the single model turn.
      assertEquals(3, chat.getHistory().size)
    }
  }

  @Test
  fun testPerCallConfigOverridesSessionConfig() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(enterprise, "ChatsTest.testPerCallConfigOverridesSessionConfig.$suffix")

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          config = GenerateContentConfig(systemInstruction = alwaysAnswer("blue")),
        )
      val response =
        chat.sendMessage(
          "What is your favourite colour?",
          config = GenerateContentConfig(systemInstruction = alwaysAnswer("red")),
        )

      assertContains((response.text ?: "").lowercase(), "red")
    }
  }

  @Test
  fun testSessionConfigAppliesWhenNoPerCallConfig() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(enterprise, "ChatsTest.testSessionConfigAppliesWhenNoPerCallConfig.$suffix")

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          config = GenerateContentConfig(systemInstruction = alwaysAnswer("blue")),
        )
      val response = chat.sendMessage("What is your favourite colour?")

      assertContains((response.text ?: "").lowercase(), "blue")
    }
  }

  @Test
  fun testSeededHistoryIsSentToTheModel() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client = createClient(enterprise, "ChatsTest.testSeededHistoryIsSentToTheModel.$suffix")

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          history =
            listOf(
              Content(role = "user", parts = listOf(Part(text = "My favourite colour is blue."))),
              Content(role = "model", parts = listOf(Part(text = "Noted, blue it is."))),
            ),
        )
      val response = chat.sendMessage("What is my favourite colour? Answer with one word.")

      assertContains((response.text ?: "").lowercase(), "blue")
      assertEquals(4, chat.getHistory().size)
    }
  }

  @Test
  fun testSendMessageStream() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client = createClient(enterprise, "ChatsTest.testSendMessageStream.$suffix")

      val chat = client.chats.create(model = MODEL_NAME)
      val chunks = chat.sendMessageStream("What is the capital of France?").toList()

      assertTrue(chunks.isNotEmpty(), "Expected at least one chunk")
      assertContains(chunks.joinToString("") { it.text ?: "" }, "Paris")

      // Each chunk is recorded as its own turn, so the history is the user turn plus one turn per
      // chunk that carried content.
      val history = chat.getHistory()
      assertEquals("user", history[0].role)
      assertEquals(chunks.size + 1, history.size)
      assertTrue(history.drop(1).all { it.role == "model" })
    }
  }

  @Test
  fun testStreamNotCollectedDoesNotRecordHistory() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(enterprise, "ChatsTest.testStreamNotCollectedDoesNotRecordHistory.$suffix")

      val chat = client.chats.create(model = MODEL_NAME)
      // Abandoning the flow partway must leave the session usable rather than wedged.
      chat.sendMessageStream("What is the capital of France?").take(1).toList()

      assertEquals(emptyList(), chat.getHistory())

      val response = chat.sendMessage("What is the capital of Germany?")

      assertContains(response.text ?: "", "Berlin")
      assertEquals(2, chat.getHistory().size)
    }
  }

  @Test
  fun testStreamAfterNonStreamingTurnKeepsHistoryOrdered() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(
          enterprise,
          "ChatsTest.testStreamAfterNonStreamingTurnKeepsHistoryOrdered.$suffix",
        )

      val chat = client.chats.create(model = MODEL_NAME)
      chat.sendMessage("My favourite colour is blue. Remember it.")
      val chunks =
        chat.sendMessageStream("What is my favourite colour? Answer with one word.").toList()

      assertContains(chunks.joinToString("") { it.text ?: "" }.lowercase(), "blue")
      val history = chat.getHistory()
      assertEquals("user", history[0].role)
      assertEquals("model", history[1].role)
      assertEquals("user", history[2].role)
      assertTrue(history.drop(3).all { it.role == "model" })
    }
  }

  @Test
  fun testCollectingACompletedStreamTwiceThrows() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      // Reuses testSendMessageStream's recording: the request is identical, and the second
      // collection throws before reaching the network.
      val client = createClient(enterprise, "ChatsTest.testSendMessageStream.$suffix")

      val chat = client.chats.create(model = MODEL_NAME)
      val flow = chat.sendMessageStream("What is the capital of France?")
      val chunks = flow.toList()
      val historyAfterFirst = chat.getHistory()

      // Re-collecting would otherwise send the message again and duplicate the turn.
      assertFailsWith<IllegalStateException> { flow.toList() }

      // The turn is untouched, and the chunks already collected stay readable.
      assertEquals(historyAfterFirst, chat.getHistory())
      assertContains(chunks.joinToString("") { it.text ?: "" }, "Paris")
    }
  }

  @Test
  fun testAutomaticFunctionCalling() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client = createClient(enterprise, "ChatsTest.testAutomaticFunctionCalling.$suffix")

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          automaticFunctionCalling = AutomaticFunctionCalling(weatherFunction()),
        )
      val response = chat.sendMessage("What is the weather in Zurich, Switzerland?")

      // The model answered rather than handing back a call, so the loop ran to completion.
      assertTrue(response.functionCalls.isNullOrEmpty())
      assertContains(response.text ?: "", "17")

      // One turn, four contents, in the order the exchange happened.
      val history = chat.getHistory()
      assertEquals(listOf("user", "model", "user", "model"), history.map { it.role })
      val call = history[1].parts!!.first().functionCall!!
      assertEquals("get_weather", call.name)
      // The model filled in the nested object, which is the part of the schema reached by $ref.
      assertContains(call.args!!.keys, "location")
      val sent = history[2].parts!!.first().functionResponse!!
      assertEquals("get_weather", sent.name)
      assertContains(sent.response!!.keys, "result")
      // The model issues an id, and it has to come back on the response that answers that call.
      assertNotNull(call.id)
      assertEquals(call.id, sent.id)
      // A completed turn is worth sending again, so curated and comprehensive agree.
      assertEquals(history, chat.getHistory(curated = true))
    }
  }

  @Test
  fun testAutomaticFunctionCallingStream() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client = createClient(enterprise, "ChatsTest.testAutomaticFunctionCallingStream.$suffix")

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          automaticFunctionCalling = AutomaticFunctionCalling(weatherFunction()),
        )
      val chunks = chat.sendMessageStream("What is the weather in Zurich, Switzerland?").toList()

      // Everything the model sends is emitted, the function call included.
      assertTrue(chunks.any { !it.functionCalls.isNullOrEmpty() }, "no functionCall was emitted")
      // What this side sends back is not the model speaking, so it never reaches the collector.
      assertTrue(
        chunks.none { chunk ->
          chunk.candidates.orEmpty().any { candidate ->
            candidate.content?.parts.orEmpty().any { it.functionResponse != null }
          }
        },
        "a functionResponse leaked into the stream",
      )
      assertContains(chunks.mapNotNull { it.text }.joinToString(""), "17")

      val history = chat.getHistory()
      assertTrue(history.any { c -> c.parts.orEmpty().any { it.functionCall != null } })
      assertTrue(history.any { c -> c.parts.orEmpty().any { it.functionResponse != null } })
      assertEquals(history, chat.getHistory(curated = true))
    }
  }

  @Test
  fun testAutomaticFunctionCallingStopsAtMaximumRemoteCalls() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(
          enterprise,
          "ChatsTest.testAutomaticFunctionCallingStopsAtMaximumRemoteCalls.$suffix",
        )

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          automaticFunctionCalling =
            AutomaticFunctionCalling(listOf(weatherFunction()), maximumRemoteCalls = 1),
        )
      val response = chat.sendMessage("What is the weather in Zurich, Switzerland?")

      // The single request the budget allows is spent being asked for the weather, leaving nothing
      // to deliver a result with. The function is not run, and the turn ends on the model's call
      // so the caller can answer it themselves.
      assertNull(response.text)
      assertFalse(response.functionCalls.isNullOrEmpty())
      assertEquals(listOf("user", "model"), chat.getHistory().map { it.role })
    }
  }

  @Test
  fun testFailingFunctionIsReportedToTheModel() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(enterprise, "ChatsTest.testFailingFunctionIsReportedToTheModel.$suffix")

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          automaticFunctionCalling = AutomaticFunctionCalling(failingWeatherFunction()),
        )
      val response = chat.sendMessage("What is the weather in Zurich, Switzerland?")

      // The failure reached the model, which answered; it never reached the caller.
      assertNotNull(response.text)
      val sent = chat.getHistory()[2].parts!!.first().functionResponse!!
      assertContains(sent.response!!["error"].toString(), "the weather service is unavailable")
    }
  }

  @Test
  fun testAutomaticFunctionCallingWithNoParameterAndNamedParameterFunctions() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(
          enterprise,
          "ChatsTest.testAutomaticFunctionCallingWithNoParameterAndNamedParameterFunctions.$suffix",
        )

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          automaticFunctionCalling = AutomaticFunctionCalling(timeFunction(), populationFunction()),
        )
      val response =
        chat.sendMessage("What time is it, and how many people live in Zurich? Use your tools.")

      assertNotNull(response.text)
      // Both shapes were accepted and dispatched: an empty properties object, and a named
      // parameter whose schema sits directly under that name.
      val answered =
        chat
          .getHistory()
          .flatMap { it.parts.orEmpty() }
          .mapNotNull { it.functionResponse?.name }
          .toSet()
      assertEquals(setOf("current_time", "get_population"), answered)
    }
  }

  @Test
  fun testAutomaticFunctionCallingStreamStopsAtMaximumRemoteCalls() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(
          enterprise,
          "ChatsTest.testAutomaticFunctionCallingStreamStopsAtMaximumRemoteCalls.$suffix",
        )

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          automaticFunctionCalling =
            AutomaticFunctionCalling(listOf(weatherFunction()), maximumRemoteCalls = 1),
        )
      val chunks = chat.sendMessageStream("What is the weather in Zurich, Switzerland?").toList()

      // The budget cuts the turn off the same way over a stream: the call is emitted and the turn
      // ends on it, with nothing run and nothing sent back.
      assertTrue(chunks.any { !it.functionCalls.isNullOrEmpty() })
      assertEquals("model", chat.getHistory().last().role)
      assertTrue(
        chat.getHistory().flatMap { it.parts.orEmpty() }.none { it.functionResponse != null },
        "a function response reached the history",
      )
    }
  }

  @Test
  fun testFailingFunctionInStreamIsReportedToTheModel() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(
          enterprise,
          "ChatsTest.testFailingFunctionInStreamIsReportedToTheModel.$suffix",
        )

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          automaticFunctionCalling = AutomaticFunctionCalling(failingWeatherFunction()),
        )
      val chunks = chat.sendMessageStream("What is the weather in Zurich, Switzerland?").toList()

      // The failure went to the model, which carried on and answered over the stream.
      assertTrue(chunks.mapNotNull { it.text }.joinToString("").isNotEmpty())
      val sent =
        chat.getHistory().flatMap { it.parts.orEmpty() }.first { it.functionResponse != null }
      assertContains(sent.functionResponse!!.response!!["error"].toString(), "unavailable")
    }
  }

  @Test
  fun testAutomaticFunctionCallingWithAListOfNestedClasses() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(
          enterprise,
          "ChatsTest.testAutomaticFunctionCallingWithAListOfNestedClasses.$suffix",
        )

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          automaticFunctionCalling = AutomaticFunctionCalling(registerFamily()),
        )
      val response =
        chat.sendMessage(
          "Register a family: the adult is Ada, born 1815-12-10, and the children are " +
            "Byron, born 1836-05-12, and Anne, born 1837-09-22."
        )

      assertNotNull(response.text)
      val call = chat.getHistory()[1].parts!!.first().functionCall!!
      assertEquals("register_booking", call.name)
      // The class is the argument list, so its fields are the arguments: one referenced object
      // and one array of referenced objects, both filled in by the model.
      assertContains(call.args!!.keys, "adult")
      assertContains(call.args!!.keys, "children")
      assertContains(
        chat.getHistory()[2].parts!!.first().functionResponse!!.response!!["result"].toString(),
        "2 children",
      )
    }
  }

  @Test
  fun testAutomaticFunctionCallingRunsSeveralFunctionsInParallel() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(
          enterprise,
          "ChatsTest.testAutomaticFunctionCallingRunsSeveralFunctionsInParallel.$suffix",
        )

      val chat =
        client.chats.create(
          model = MODEL_NAME,
          automaticFunctionCalling =
            AutomaticFunctionCalling(
              listOf(timeFunction(), populationFunction()),
              runFunctionsInParallel = true,
            ),
        )
      val response =
        chat.sendMessage("What time is it, and how many people live in Zurich? Use your tools.")

      assertNotNull(response.text)
      // The model asked for both in one response, and both were answered. Their order matching the
      // calls is asserted under controlled timing in ChatsUnitTest; here both handlers are instant,
      // so this checks the end-to-end path rather than the ordering.
      val calls = chat.getHistory()[1].parts!!.mapNotNull { it.functionCall?.name }
      assertEquals(2, calls.size)
      val answers = chat.getHistory()[2].parts!!.mapNotNull { it.functionResponse?.name }
      assertEquals(calls, answers)
    }
  }
}

private fun alwaysAnswer(colour: String) =
  Content(
    parts = listOf(Part(text = "Whatever you are asked, answer with exactly one word: $colour."))
  )

@Serializable private data class Location(val city: String, val country: String)

// Shaped to exercise the parts of the schema that are ours rather than the model's: a nested class
// becomes $defs plus $ref, a nullable field becomes anyOf, and a defaulted field drops out of
// required. These recordings are what prove the backend accepts those encodings.
@Serializable
private data class WeatherRequest(
  @Describe("The place to look up.") val location: Location,
  @Describe("Unit to report the temperature in, celsius or fahrenheit.")
  val unit: String = "celsius",
  @Describe("Days ahead of today, or null for today.") val dayOffset: Int? = null,
)

private fun serverTime(): String = "07:30 UTC"

private fun population(city: String): String = "$city has about 400,000 people"

// A function with no parameters at all, whose schema is an empty properties object, and one whose
// parameter is named rather than carried by a class. Neither shape reaches the wire through the
// argument-class tests.
private fun timeFunction(): CallableFunction =
  callableFunction("current_time", "The current server time.", handler = ::serverTime)

private fun populationFunction(): CallableFunction =
  callableFunction(
    "get_population",
    "How many people live in a city.",
    paramName = "city",
    handler = ::population,
  )

private fun failingWeatherFunction(): CallableFunction =
  callableFunction<WeatherRequest, String>("get_weather", "Looks up the weather.") {
    throw IllegalStateException("the weather service is unavailable")
  }

@Serializable private data class Guest(val name: String, @Describe("YYYY-MM-DD.") val born: String)

// A test for a parameter class held directly and again inside a list, so Person is
// referenced from two places and defined once. This recording is what shows the service accepts a
// $ref sitting under an array's items.
@Serializable private data class Booking(val adult: Guest, val children: List<Guest>)

private fun registerFamily(): CallableFunction =
  callableFunction("register_booking", "Records a booking for a family.") { booking: Booking ->
    "Registered ${booking.adult.name} with ${booking.children.size} children"
  }

private fun weatherFunction(): CallableFunction =
  callableFunction("get_weather", "Looks up the weather for a place.") { request: WeatherRequest ->
    "17 degrees ${request.unit} and raining in ${request.location.city}"
  }
