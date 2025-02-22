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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fault.e.sprayer.R
import com.fault.e.sprayer.model.Board

/**
 * A screen with a list of boards available for a user
 * @param modifier Modifier applied to the screen layout
 * @param boardsViewModel Provides a list of available boards
 */
@Composable
fun BoardScreen(modifier: Modifier = Modifier, boardsViewModel: BoardsViewModel = viewModel()) {
    // TODO: The state will be passed instead of the view model when the navigation is implemented
    val boardsState by boardsViewModel.boardsState.collectAsStateWithLifecycle()

    when (val state = boardsState) {
        is BoardsState.Loading -> {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Loading boards...", style = MaterialTheme.typography.bodyLarge)
            }
        }

        is BoardsState.Success -> {
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
            ) {
                items(state.boards) {
                    BoardCard(it)
                }
            }
        }
    }
}

/**
 * A card with a brief board description
 * @param board Board to display
 * @param modifier Modifier applied to the card layout
 */
@Composable
fun BoardCard(board: Board, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(4.dp),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.board),
                contentDescription = null,
                modifier = Modifier.size(54.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.padding(4.dp))
            Column {
                Text(board.gym, style = MaterialTheme.typography.labelMedium)
                Text(board.name, style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(Modifier.padding(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Icon(painter = painterResource(R.drawable.arrow_right), contentDescription = null)
            }
        }
    }

}

@Preview
@Composable
fun BoardCardPreview() {
    BoardCard(
        Board(
            gym = "My Gym",
            name = "My Board",
            image = ""
        ),
        Modifier.width(290.dp)
    )
}

//TODO: Implement previews for each state when the navigation is implemented
@Preview
@Composable
fun BoardsScreenPreview() {
    BoardScreen()
}
