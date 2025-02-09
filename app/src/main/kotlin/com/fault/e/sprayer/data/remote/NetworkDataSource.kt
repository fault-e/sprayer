/**
 * Copyright 2025 FAULT-E
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fault.e.sprayer.data.remote

import com.fault.e.sprayer.data.remote.model.BoardDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json

/**
 * Backend API
 * TODO: inject the client with Hilt
 */
class NetworkDataSource {
    private val client = HttpClient(OkHttp) {
        engine {
            config {
                followRedirects(true)
            }
        }
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTP
                host = "10.0.2.2:3000/api/v1"
            }
        }
    }

    /**
     * Retrieves a list of available boards
     * @return List of boards
     */
    suspend fun getBoards(): List<BoardDto> = client.get {
        url {
            path("/boards")
        }
    }.body()

    /**
     * Retrieves a board by its id
     * @param id Board id
     * @return Board info
     */
    suspend fun getBoard(id: Int): BoardDto = client.get {
        url {
            path("/boards/$id")
        }
    }.body()
}