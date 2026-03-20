package com.artcapone.steamlibrary.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.artcapone.steamlibrary.data.AppContainer

@Composable
fun GameDetailScreen(
    appId: Long,
    onBack: () -> Unit
) {
    val viewModel = remember { GameDetailViewModel(AppContainer.libraryRepository) }
    val state = remember(appId) { viewModel.load(appId) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(state.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("App ID: ${state.appId}", color = MaterialTheme.colorScheme.onSurfaceVariant)

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Durum: ${state.status}")
                state.description?.let { Text(it, color = MaterialTheme.colorScheme.onSurfaceVariant) }
                Text(if (state.favorite) "★ Favorilerde" else "Favorilerde değil")
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Kişisel not", fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = state.note,
                    onValueChange = {},
                    label = { Text("Not") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4
                )
            }
        }

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Geri")
        }
    }
}
