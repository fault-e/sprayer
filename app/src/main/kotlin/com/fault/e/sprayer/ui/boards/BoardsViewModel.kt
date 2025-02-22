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
package com.fault.e.sprayer.ui.boards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fault.e.sprayer.data.remote.KtorNetwork
import com.fault.e.sprayer.domain.usecase.GetBoardsUseCase
import com.fault.e.sprayer.model.Board
import com.fault.e.sprayer.repository.BoardRepositoryImpl
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel for the boards screen
 */
class BoardsViewModel : ViewModel() {
    // TODO: Inject with Hilt
    private val getBoardsUseCase =
        GetBoardsUseCase(boardRepository = BoardRepositoryImpl(network = KtorNetwork()))

    val boardsState: StateFlow<BoardsState> = getBoardsUseCase()
        .map<List<Board>, BoardsState> {
            BoardsState.Success(it)
        }
        .onStart { emit(BoardsState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BoardsState.Loading
        )
}