package com.artcapone.steamlibrary.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artcapone.steamlibrary.data.AppContainer

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    val viewModel = remember { SettingsViewModel(AppContainer.libraryRepository) }
    val state = remember { viewModel.loadState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Ayarlar", style = MaterialTheme.typography.headlineMedium)
        Text("Senkron modu: ${state.syncModeLabel}")
        Text("Backend: ${state.backendLabel}")
        Text("Cache: ${state.cacheLabel}")
        Text("Bağlı Steam profili: ${state.linkedSteamProfile ?: "Henüz yok"}")
        Button(onClick = onLogout) {
            Text("Çıkış yap")
        }
        Button(onClick = onBack) {
            Text("Geri")
        }
    }
}
