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
import com.google.genai.kotlin.types.Part
import kotlin.test.Test
import kotlin.test.assertEquals

class ContentTest {

  @Test
  fun testFromTextBuildsASingleTextPart() {
    assertEquals(
      Content(parts = listOf(Part(text = "Hello")), role = "user"),
      Content.fromText("Hello"),
    )
  }

  @Test
  fun testFromTextDefaultsToTheUserRole() {
    // Content.role defaults to null, which a chat session rejects when the history is handed back,
    // so the creator fills it in.
    assertEquals("user", Content.fromText("Hello").role)
  }

  @Test
  fun testFromTextAcceptsAnExplicitRole() {
    assertEquals("model", Content.fromText("Hello", role = "model").role)
  }

  @Test
  fun testFromTextKeepsAnEmptyStringAsAPopulatedPart() {
    // An empty string is a populated part, unlike a Part with nothing set at all.
    val content = Content.fromText("")

    assertEquals(listOf(Part(text = "")), content.parts)
  }

  @Test
  fun testFromTextRoundTripsThroughAChatSession() {
    // The reason the role default exists: a history built this way has to be one create() accepts.
    val history = listOf(Content.fromText("Hi"), Content.fromText("Hello!", role = "model"))
    val chat = Chats(Models(ApiClient(apiKey = "test-api-key"))).create("m", history = history)

    assertEquals(history, chat.getHistory())
  }
}
