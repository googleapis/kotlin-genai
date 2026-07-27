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

import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.LiveServerContent
import com.google.genai.kotlin.types.LiveServerMessage
import com.google.genai.kotlin.types.Part
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LiveServerMessageTest {

  @Test
  fun testTextHelperMethod() {
    // 1. Only text parts
    val msg1 =
      LiveServerMessage(
        serverContent =
          LiveServerContent(
            modelTurn = Content(parts = listOf(Part(text = "Hello "), Part(text = "World!")))
          )
      )
    assertEquals("Hello World!", msg1.text)

    // 2. Text parts and thought parts (thought should be skipped)
    val msg2 =
      LiveServerMessage(
        serverContent =
          LiveServerContent(
            modelTurn =
              Content(
                parts =
                  listOf(Part(text = "Let me think...", thought = true), Part(text = "Hello!"))
              )
          )
      )
    assertEquals("Hello!", msg2.text)

    // 3. No text parts
    val msg3 =
      LiveServerMessage(
        serverContent =
          LiveServerContent(
            modelTurn =
              Content(
                parts =
                  listOf(
                    Part(inlineData = Blob(mimeType = "image/jpeg", data = byteArrayOf(1, 2, 3)))
                  )
              )
          )
      )
    assertNull(msg3.text)

    // 4. Empty parts
    val msg4 =
      LiveServerMessage(serverContent = LiveServerContent(modelTurn = Content(parts = emptyList())))
    assertNull(msg4.text)
  }

  @Test
  fun testDataHelperMethod() {
    // 1. Only data parts
    val msg1 =
      LiveServerMessage(
        serverContent =
          LiveServerContent(
            modelTurn =
              Content(
                parts =
                  listOf(
                    Part(inlineData = Blob(mimeType = "audio/pcm", data = byteArrayOf(1, 2))),
                    Part(inlineData = Blob(mimeType = "audio/pcm", data = byteArrayOf(3, 4))),
                  )
              )
          )
      )
    assertContentEquals(byteArrayOf(1, 2, 3, 4), msg1.data)

    // 2. Mixed text and data parts (text should be ignored)
    val msg2 =
      LiveServerMessage(
        serverContent =
          LiveServerContent(
            modelTurn =
              Content(
                parts =
                  listOf(
                    Part(text = "Here is some audio"),
                    Part(inlineData = Blob(mimeType = "audio/pcm", data = byteArrayOf(5, 6))),
                  )
              )
          )
      )
    assertContentEquals(byteArrayOf(5, 6), msg2.data)

    // 3. No data parts
    val msg3 =
      LiveServerMessage(
        serverContent =
          LiveServerContent(modelTurn = Content(parts = listOf(Part(text = "Hello World!"))))
      )
    assertNull(msg3.data)

    // 4. Empty parts
    val msg4 =
      LiveServerMessage(serverContent = LiveServerContent(modelTurn = Content(parts = emptyList())))
    assertNull(msg4.data)
  }
}
