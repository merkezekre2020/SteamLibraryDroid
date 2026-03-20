package com.artcapone.steamlibrary.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Ayarlar", style = MaterialTheme.typography.headlineMedium)
        Text("- Tema seçimi daha sonra")
        Text("- Manuel sync butonu library ekranında hazır")
        Text("- PocketBase ayarları sonra bağlanacak")
        Button(onClick = onLogout) {
            Text("Çıkış yap")
        }
        Button(onClick = onBack) {
            Text("Geri")
        }
    }
}
