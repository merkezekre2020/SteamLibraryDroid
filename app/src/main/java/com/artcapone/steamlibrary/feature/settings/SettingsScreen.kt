package com.artcapone.steamlibrary.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("⚙️ Ayarlar", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Senkron modu: ${state.syncModeLabel}")
                Text("Backend: ${state.backendLabel}")
                Text("Cache: ${state.cacheLabel}")
                Text("Bağlı Steam profili: ${state.linkedSteamProfile ?: "Henüz yok"}")
            }
        }

        Button(onClick = onLogout, modifier = Modifier.fillMaxWidth()) {
            Text("Çıkış yap")
        }
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Geri")
        }
    }
}
