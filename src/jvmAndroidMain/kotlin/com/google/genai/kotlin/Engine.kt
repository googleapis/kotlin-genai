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

import com.google.genai.kotlin.types.ProxyOptions
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import java.net.InetSocketAddress
import java.net.Proxy

internal actual fun getDefaultEngine(proxyOptions: ProxyOptions?): HttpClientEngine {
  return OkHttp.create {
    proxyOptions?.let { opts ->
      if (opts.type?.value?.equals("DIRECT", ignoreCase = true) == true) {
        config {
          proxy(Proxy.NO_PROXY)
        }
        return@let
      }

      val proxyType =
        when {
          opts.type == null || opts.type.value.equals("HTTP", ignoreCase = true) -> Proxy.Type.HTTP
          opts.type.value.equals("SOCKS", ignoreCase = true) -> Proxy.Type.SOCKS
          else -> throw IllegalArgumentException("Unsupported proxy type: ${opts.type.value}")
        }

      val host =
        opts.host
          ?: throw IllegalArgumentException("Proxy host is required in the ProxyOptions.")
      val port =
        opts.port
          ?: throw IllegalArgumentException("Proxy port is required in the ProxyOptions.")

      val userPresent = opts.username != null
      val passPresent = opts.password != null
      if (userPresent != passPresent) {
        throw IllegalArgumentException(
          "Proxy username and password must both be provided or not at all."
        )
      }

      config {
        proxy(Proxy(proxyType, InetSocketAddress(host, port)))

        // Add basic proxy authentication if provided
        if (userPresent && passPresent) {
          proxyAuthenticator { _, response ->
            if (response.request.header("Proxy-Authorization") != null) {
              return@proxyAuthenticator null
            }
            val credential = okhttp3.Credentials.basic(opts.username!!, opts.password!!)
            response.request.newBuilder().header("Proxy-Authorization", credential).build()
          }
        }
      }
    }
  }
}
