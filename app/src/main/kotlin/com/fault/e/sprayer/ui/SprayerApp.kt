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
package com.fault.e.sprayer.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fault.e.sprayer.ui.boards.BoardScreen
import com.fault.e.sprayer.ui.theme.SprayerTheme

/**
 * A UI entry point to be called from MainActivity
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SprayerApp() {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Boards")
                },
            )
        },
        bottomBar = {
            BottomAppBar {}
        }
    ) { innerPadding ->
        BoardScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    SprayerTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        SprayerApp()
    }
}