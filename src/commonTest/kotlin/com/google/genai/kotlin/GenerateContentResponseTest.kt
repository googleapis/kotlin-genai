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
import com.google.genai.kotlin.types.FunctionCall
import com.google.genai.kotlin.types.GenerateContentResponse
import com.google.genai.kotlin.types.Part
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GenerateContentResponseTest {

  @Test
  fun testTextReturnsConcatenatedTextPartsFromFirstCandidate() {
    val response =
      GenerateContentResponse(
        candidates =
          listOf(
            Candidate(
              content = Content(parts = listOf(Part(text = "Hello "), Part(text = "world!")))
            ),
            Candidate(content = Content(parts = listOf(Part(text = "Second candidate text")))),
          )
      )
    assertEquals("Hello world!", response.text)
  }

  @Test
  fun testTextSkipsThoughtParts() {
    val response =
      GenerateContentResponse(
        candidates =
          listOf(
            Candidate(
              content =
                Content(
                  parts =
                    listOf(Part(text = "Thinking...", thought = true), Part(text = "Hello world!"))
                )
            )
          )
      )
    assertEquals("Hello world!", response.text)
  }

  @Test
  fun testTextReturnsNullIfNoTextParts() {
    val response =
      GenerateContentResponse(
        candidates =
          listOf(
            Candidate(content = Content(parts = listOf(Part(thought = true, text = "Thinking..."))))
          )
      )
    assertNull(response.text)
  }

  @Test
  fun testTextReturnsNullIfCandidatesAreEmpty() {
    val response = GenerateContentResponse(candidates = emptyList())
    assertNull(response.text)
  }

  @Test
  fun testTextReturnsNullIfCandidatesIsNull() {
    val response = GenerateContentResponse(candidates = null)
    assertNull(response.text)
  }

  @Test
  fun testFunctionCallsReturnsCallsFromFirstCandidate() {
    val response =
      GenerateContentResponse(
        candidates =
          listOf(
            Candidate(
              content =
                Content(
                  parts =
                    listOf(
                      Part(functionCall = FunctionCall(name = "testFunc", args = mapOf())),
                      Part(text = "some text"),
                    )
                )
            )
          )
      )
    assertEquals(1, response.functionCalls?.size)
    assertEquals("testFunc", response.functionCalls?.get(0)?.name)
  }

  @Test
  fun testFunctionCallsReturnsNullIfNoCalls() {
    val response =
      GenerateContentResponse(
        candidates = listOf(Candidate(content = Content(parts = listOf(Part(text = "some text")))))
      )
    assertNull(response.functionCalls)
  }
}
