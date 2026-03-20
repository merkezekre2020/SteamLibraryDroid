package com.artcapone.steamlibrary.feature.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.artcapone.steamlibrary.data.AppContainer
import kotlinx.coroutines.launch

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
    var syncError by remember { mutableStateOf<String?>(null) }
    val filters = listOf("all", "favorites", "backlog", "playing", "completed")

    LaunchedEffect(Unit) {
        val cached = viewModel.loadCachedState()
        if (cached.games.isNotEmpty()) state = cached
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("🎮 Kütüphane", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text(
                    "Steam oyunlarını filtrele, senkronla, kurcala.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Button(onClick = onSettingsClick) { Text("Ayarlar") }
        }

        if (syncedCount > 0) {
            Text("Son sync: $syncedCount oyun güncellendi", modifier = Modifier.padding(top = 10.dp))
        }
        syncError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.query,
            onValueChange = { state = viewModel.updateQuery(state.selectedFilter, it) },
            label = { Text("Oyun ara") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filters.forEach { filter ->
                val selected = state.selectedFilter == filter
                AssistChip(
                    onClick = { state = viewModel.updateFilter(state.query, filter) },
                    label = { Text(filter) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)
                        else MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                scope.launch {
                    runCatching { viewModel.sync() }
                        .onSuccess {
                            syncedCount = it
                            syncError = null
                            state = viewModel.loadState(state.query, state.selectedFilter)
                        }
                        .onFailure {
                            syncError = it.message ?: "Sync başarısız"
                        }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sync başlat")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(state.games) { game ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onGameClick(game.appId) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(game.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                        Text("Durum: ${game.status}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("Süre: ${game.playtimeMinutes} dk", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        if (game.favorite) {
                            Text("★ Favori", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}
