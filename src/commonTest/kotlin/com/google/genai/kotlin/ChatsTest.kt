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
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

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
  fun testCuratedHistoryExcludesInvalidTurn() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val client =
        createClient(enterprise, "ChatsTest.testCuratedHistoryExcludesInvalidTurn.$suffix")

      val chat = client.chats.create(model = MODEL_NAME)
      chat.sendMessage("What is the capital of France?")
      assertEquals(2, chat.getHistory().size)
      assertEquals(2, chat.getHistory(curated = true).size)

      // The recording for this second turn was edited to return a response with no usable content,
      // which is what a safety block looks like to the SDK. The turn is remembered, but it is not
      // worth sending back to the model.
      chat.sendMessage("And what is the capital of Germany?")

      assertEquals(4, chat.getHistory().size)
      assertEquals(2, chat.getHistory(curated = true).size)
      assertTrue(chat.getHistory()[3].parts.isNullOrEmpty())
    }
  }
}

private fun alwaysAnswer(colour: String) =
  Content(
    parts = listOf(Part(text = "Whatever you are asked, answer with exactly one word: $colour."))
  )
