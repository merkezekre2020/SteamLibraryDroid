package com.artcapone.steamlibrary.feature.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artcapone.steamlibrary.data.AppContainer

@Composable
fun LibraryScreen(
    onGameClick: (Long) -> Unit,
    onSettingsClick: () -> Unit
) {
    val viewModel = remember {
        LibraryViewModel(
            repository = AppContainer.libraryRepository,
            getCachedLibraryUseCase = AppContainer.getCachedLibraryUseCase
        )
    }
    var state by remember { mutableStateOf(viewModel.loadState()) }
    var syncedCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        val cached = viewModel.loadCachedState()
        if (cached.games.isNotEmpty()) state = cached
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Kütüphane", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = onSettingsClick) { Text("Ayarlar") }
        }

        if (syncedCount > 0) {
            Text("Son sync: $syncedCount oyun güncellendi", modifier = Modifier.padding(top = 8.dp))
        }
        syncError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        OutlinedTextField(
            value = state.query,
            onValueChange = { state = viewModel.updateQuery(state.selectedFilter, it) },
            label = { Text("Oyun ara") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("all", "favorites", "backlog", "playing", "completed").forEach { filter ->
                Button(onClick = { state = viewModel.updateFilter(state.query, filter) }) {
                    Text(filter)
                }
            }
        }

        Button(onClick = { syncedCount = viewModel.sync() }) {
            Text("Sync")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 12.dp)
        ) {
            items(state.games) { game ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onGameClick(game.appId) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(game.name, style = MaterialTheme.typography.titleMedium)
                        Text("Durum: ${game.status}")
                        Text("Süre: ${game.playtimeMinutes} dk")
                        if (game.favorite) Text("★ Favori")
                    }
                }
            }
        }
    }
}
                      if (game.favorite) Text("★ Favori")
                    }
                }
            }
        }
    }
}
