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

import com.fault.e.sprayer.model.Board

/**
 * State of the boards screen
 */
sealed interface BoardsState {
    /**
     * Loading state
     */
    data object Loading : BoardsState
    /**
     * Success state
     * @param boards List of boards to show
     */
    data class Success(val boards: List<Board>) : BoardsState
}