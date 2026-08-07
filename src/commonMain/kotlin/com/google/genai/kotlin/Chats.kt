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
import com.google.genai.kotlin.types.GenerateContentResponse
import com.google.genai.kotlin.types.Part

internal const val ROLE_USER = "user"

internal const val ROLE_MODEL = "model"

private val EMPTY_PART = Part()

// Content is valid when it has at least one part and none of its parts are empty. A part holding an
// empty string, such as Part(text = ""), counts as populated.
internal fun isValidContent(content: Content?): Boolean {
  val parts = content?.parts
  if (parts.isNullOrEmpty()) {
    return false
  }
  return parts.none { it == EMPTY_PART }
}

// A response is valid when its first candidate carries valid content. Later candidates are not
// considered, matching the other Gen AI SDKs.
internal fun isValidResponse(response: GenerateContentResponse): Boolean {
  val candidate = response.candidates?.firstOrNull() ?: return false
  return isValidContent(candidate.content)
}

// Derives the curated turns from every turn in the session, in their original order. User turns are
// always kept. A run of consecutive model turns is kept only when every turn in it is valid; if any
// is invalid the whole run is dropped, along with the user turn that prompted it, so the curated
// history keeps alternating. Throws IllegalArgumentException on a role other than user or model.
internal fun extractCuratedHistory(comprehensiveHistory: List<Content>): List<Content> {
  val curatedHistory = mutableListOf<Content>()
  var i = 0
  while (i < comprehensiveHistory.size) {
    val turn = comprehensiveHistory[i]
    val role = turn.role
    require(role == ROLE_USER || role == ROLE_MODEL) {
      if (role == null) {
        "Every turn in the history must set a role of $ROLE_USER or $ROLE_MODEL, but one turn " +
          "left it unset. Content.role defaults to null, so it has to be passed explicitly."
      } else {
        "Role must be $ROLE_USER or $ROLE_MODEL, but got $role."
      }
    }

    if (role == ROLE_USER) {
      curatedHistory.add(turn)
      i++
      continue
    }

    val modelOutput = mutableListOf<Content>()
    var isValid = true
    while (i < comprehensiveHistory.size) {
      val modelTurn = comprehensiveHistory[i]
      if (modelTurn.role != ROLE_MODEL) {
        break
      }
      modelOutput.add(modelTurn)
      if (isValid && !isValidContent(modelTurn)) {
        isValid = false
      }
      i++
    }

    if (isValid) {
      curatedHistory.addAll(modelOutput)
    } else if (curatedHistory.isNotEmpty() && curatedHistory.last().role == ROLE_USER) {
      curatedHistory.removeAt(curatedHistory.lastIndex)
    }
  }
  return curatedHistory
}
