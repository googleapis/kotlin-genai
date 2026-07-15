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

import com.google.genai.kotlin.types.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

/** Represents a single immutable page of results. */
class Page<T>(val items: List<T>, val nextPageToken: String?, val sdkHttpResponse: HttpResponse?)

/**
 * A generic pagination class that implements [Flow] of [T] items.
 *
 * Exposes page-level properties via `byPage()` flow, or collects all items seamlessly via
 * `collect()`. This flow is cold, stateless, and thread-safe.
 */
class Pager<T>(
  val name: String,
  val pageSize: Int? = null,
  private val request: suspend (String?) -> Triple<List<T>?, String?, HttpResponse?>,
) : Flow<T> {

  /**
   * Collects and emits all items across all available pages.
   *
   * This method fetches subsequent pages until all results are exhausted.
   * It emits each item individually to the provided [collector].
   *
   * If you need access to page-level metadata (such as the HTTP response or next page token), use
   * [byPage] instead.
   *
   * @param collector The [FlowCollector] to emit individual items to.
   */
  override suspend fun collect(collector: FlowCollector<T>) {
    var token: String? = null
    var hasNextPage = true

    while (hasNextPage) {
      val (items, nextToken, _) = request(token)
      items?.forEach { collector.emit(it) }

      if (nextToken.isNullOrEmpty()) {
        hasNextPage = false
      } else {
        token = nextToken
      }
    }
  }

  /**
   * Returns a [Flow] that emits [Page] objects, allowing granular access to page-level metadata
   * such as [Page.nextPageToken] and [Page.sdkHttpResponse].
   */
  fun byPage(): Flow<Page<T>> = flow {
    var token: String? = null
    var hasNextPage = true

    while (hasNextPage) {
      val (items, nextToken, response) = request(token)
      emit(Page(items ?: emptyList(), nextToken, response))

      if (nextToken.isNullOrEmpty()) {
        hasNextPage = false
      } else {
        token = nextToken
      }
    }
  }
}
