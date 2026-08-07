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
import com.google.genai.kotlin.types.GenerateContentResponse
import com.google.genai.kotlin.types.Part
import kotlin.concurrent.Volatile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** A factory for multi-turn [Chat] sessions. */
class Chats internal constructor(private val models: Models) {

  /**
   * Creates a chat session.
   *
   * @param model The model to talk to, for example "gemini-3.6-flash".
   * @param config Applied to every turn in the session. A config passed to an individual send
   *   replaces this one for that turn rather than merging with it.
   * @param history Turns to seed the session with. The turns are copied, so later changes to the
   *   list do not affect the session.
   * @throws IllegalArgumentException if a turn in [history] has a role other than "user" or
   *   "model".
   */
  fun create(
    model: String,
    config: GenerateContentConfig? = null,
    history: List<Content> = emptyList(),
  ): Chat = Chat(models, model, config, history)
}

/**
 * A multi-turn chat session with a generative model.
 *
 * Obtain a session from [Chats.create]:
 * ```
 * val chat = client.chats.create(model = "gemini-3.6-flash")
 * ```
 *
 * A session is not safe for concurrent turns: start a turn only once the previous one has finished,
 * or the turns will not see one another. [getHistory] is safe to call at any time.
 */
class Chat
internal constructor(
  private val models: Models,
  private val model: String,
  private val config: GenerateContentConfig?,
  history: List<Content>,
) {

  // Both lists are replaced wholesale rather than mutated in place: getHistory does not suspend,
  // so against a mutable list it could observe a turn mid-append and fail.
  @Volatile private var comprehensiveHistory: List<Content> = history.toList()

  // Kept alongside comprehensiveHistory rather than derived on read, so a turn can be committed
  // as a unit. Automatic function calling produces a tool call and its tool response in one turn;
  // re-deriving from a flat list loses those boundaries and would drop a failed turn's tool
  // response while leaving its tool call behind, which the API rejects on the next request.
  @Volatile private var curatedHistory: List<Content> = extractCuratedHistory(history)

  /**
   * Returns a snapshot of the turns in this session.
   *
   * The returned list is a copy, so it is unaffected by later turns. It is safe to call this while
   * a turn is in flight; the result then holds the turns completed so far.
   *
   * @param curated When true, returns only the turns that will be sent to the model on the next
   *   request. When false, the default, also includes turns whose response came back empty or
   *   blocked.
   */
  fun getHistory(curated: Boolean = false): List<Content> =
    if (curated) curatedHistory.toList() else comprehensiveHistory.toList()

  /**
   * Sends [text] as the next turn and returns the model's response.
   *
   * The turns so far are sent along with [text], so the model has the conversation for context. The
   * message and the response are added to the history once the response arrives.
   *
   * @param config Replaces the session config for this turn rather than merging with it.
   */
  suspend fun sendMessage(
    text: String,
    config: GenerateContentConfig? = null,
  ): GenerateContentResponse =
    sendMessage(listOf(Content(role = ROLE_USER, parts = listOf(Part(text = text)))), config)

  /**
   * Sends [content] as the next turn and returns the model's response.
   *
   * The turns so far are sent along with [content], so the model has the conversation for context.
   * The message and the response are added to the history once the response arrives.
   *
   * @param config Replaces the session config for this turn rather than merging with it.
   */
  suspend fun sendMessage(
    content: Content,
    config: GenerateContentConfig? = null,
  ): GenerateContentResponse = sendMessage(listOf(content), config)

  /**
   * Sends [contents] as the next turn and returns the model's response.
   *
   * The turns so far are sent along with [contents], so the model has the conversation for context.
   * The message and the response are added to the history once the response arrives.
   *
   * @param config Replaces the session config for this turn rather than merging with it.
   */
  suspend fun sendMessage(
    contents: List<Content>,
    config: GenerateContentConfig? = null,
  ): GenerateContentResponse {
    val userInput = withUserRole(contents)
    val response = models.generateContent(model, curatedHistory + userInput, config ?: this.config)
    recordHistory(
      userInput = userInput,
      modelOutput = listOfNotNull(response.candidates?.firstOrNull()?.content),
      isValid = isValidResponse(response),
    )
    return response
  }

  /**
   * Sends [text] as the next turn and returns the model's response in chunks.
   *
   * The turns so far are sent along with [text], so the model has the conversation for context. The
   * message is sent when the returned flow is collected, not when this method is called, and the
   * flow carries one turn and is collected once. The overload taking a list documents the rest.
   *
   * @param config Replaces the session config for this turn rather than merging with it.
   * @throws IllegalStateException if the returned flow is collected again after a completed turn.
   */
  fun sendMessageStream(
    text: String,
    config: GenerateContentConfig? = null,
  ): Flow<GenerateContentResponse> =
    sendMessageStream(listOf(Content(role = ROLE_USER, parts = listOf(Part(text = text)))), config)

  /**
   * Sends [content] as the next turn and returns the model's response in chunks.
   *
   * The turns so far are sent along with [content], so the model has the conversation for context.
   * The message is sent when the returned flow is collected, not when this method is called, and
   * the flow carries one turn and is collected once. The overload taking a list documents the rest.
   *
   * @param config Replaces the session config for this turn rather than merging with it.
   * @throws IllegalStateException if the returned flow is collected again after a completed turn.
   */
  fun sendMessageStream(
    content: Content,
    config: GenerateContentConfig? = null,
  ): Flow<GenerateContentResponse> = sendMessageStream(listOf(content), config)

  /**
   * Sends [contents] as the next turn and returns the model's response in chunks.
   *
   * The turns so far are sent along with [contents], so the model has the conversation for context.
   * The turn joins the history once the flow completes, and every chunk is kept, so a ten chunk
   * response adds ten model turns to the history rather than one. A stream that is cut off before
   * the model finishes is kept but is not sent again in later requests.
   *
   * The message is sent when the returned flow is collected, not when this method is called, so
   * collect one turn before starting the next. Starting a turn while an earlier flow is still
   * uncollected sends the two out of order:
   * ```
   * val flow = chat.sendMessageStream("first") // nothing sent yet
   * chat.sendMessage("second") // sent, and recorded, first
   * flow.collect {} // "first" is sent now, and the model sees it after "second"
   * ```
   *
   * Each flow carries one turn and is collected once. Collecting it again after the turn has
   * completed throws, rather than quietly sending the message a second time; collect into a list if
   * you need to read the response more than once. A collection that failed or was abandoned did not
   * complete a turn, so it can be retried.
   *
   * @param config Replaces the session config for this turn rather than merging with it.
   * @throws IllegalStateException if the returned flow is collected again after a completed turn.
   */
  fun sendMessageStream(
    contents: List<Content>,
    config: GenerateContentConfig? = null,
  ): Flow<GenerateContentResponse> {
    // Scoped to this call, not to the session, so that each returned flow tracks its own turn.
    var sent = false

    return flow {
      // Re-collecting a turn that already completed would send the message a second time and leave
      // a duplicate in the history. The flag is set only once the turn has been recorded, so a
      // collection that failed or was abandoned can still be retried.
      check(!sent) {
        "This turn has already been sent. Collect the flow into a list if you need to read the " +
          "response more than once, or call sendMessageStream again to send another message."
      }

      val userInput = withUserRole(contents)
      val outputContents = mutableListOf<Content>()
      var isValid = true
      var finished = false

      models
        .generateContentStream(model, curatedHistory + userInput, config ?: this@Chat.config)
        .collect { chunk ->
          if (!isValidResponse(chunk)) {
            isValid = false
          }
          val candidate = chunk.candidates?.firstOrNull()
          candidate?.content?.let { outputContents.add(it) }
          if (candidate?.finishReason != null) {
            finished = true
          }
          emit(chunk)
        }

      recordHistory(
        userInput = userInput,
        modelOutput = outputContents,
        isValid = isValid && finished,
      )
      sent = true
    }
  }

  // Resolves the role of an outgoing message. Content.role defaults to null and the service reads
  // an unset role as "user", but recording it as null would produce a history that Chats.create
  // rejects when it is handed back. Seeded history keeps requiring an explicit role, because a
  // seeded turn could equally be the model's.
  private fun withUserRole(contents: List<Content>): List<Content> = contents.map {
    if (it.role == null) it.copy(role = ROLE_USER) else it
  }

  // Appends one completed turn. The turn is committed as a unit: curatedHistory either gains every
  // content of the turn or none of it, so a multi-content turn is never split apart. An invalid
  // turn is still recorded in comprehensiveHistory.
  private fun recordHistory(
    userInput: List<Content>,
    modelOutput: List<Content>,
    isValid: Boolean,
  ) {
    // Stands in for a model that returned nothing, so the history keeps alternating.
    val outputContents = modelOutput.ifEmpty {
      listOf(Content(role = ROLE_MODEL, parts = emptyList()))
    }

    comprehensiveHistory = comprehensiveHistory + userInput + outputContents
    if (isValid) {
      curatedHistory = curatedHistory + userInput + outputContents
    }
  }
}

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
