package com.artcapone.steamlibrary.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(state.title, style = MaterialTheme.typography.headlineMedium)
        Text("App ID: ${state.appId}")
        Text("Durum: ${state.status}")
        state.description?.let { Text(it) }
        OutlinedTextField(
            value = state.note,
            onValueChange = {},
            label = { Text("Not") },
            modifier = Modifier.fillMaxWidth()
        )
        Text(if (state.favorite) "Favorilerde" else "Favorilerde değil")
        Button(onClick = onBack) {
            Text("Geri")
        }
    }
}
