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
package com.fault.e.sprayer.domain.usecase

import com.fault.e.sprayer.domain.repository.BoardRepository
import kotlinx.coroutines.flow.flow

/**
 * Use case to get boards
 * @param boardRepository Board repository
 */
class GetBoardsUseCase(
    private val boardRepository: BoardRepository
) {
    /**
     * Get boards for a specific gym
     * @param gym Gym name
     */
    operator fun invoke(gym: String) = flow {
        val boards = boardRepository.getBoards().filter {
            it.gym == gym
        }
        emit(boards)
    }

    /**
     * Get all boards
     */
    operator fun invoke() = flow {
        val boards = boardRepository.getBoards()
        emit(boards)
    }
}