package com.artcapone.steamlibrary.feature.profile

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileLinkScreen(
    onProfileLinked: (String) -> Unit,
    boundProfileText: String?
) {
    var input by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Steam Profil Bağla", style = MaterialTheme.typography.headlineMedium)
        Text("Steam ID veya profil URL gir.")
        if (boundProfileText != null) {
            Text("Bağlı profil: $boundProfileText")
        }
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Steam ID / URL") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { onProfileLinked(input) }) {
            Text("Profili bağla")
        }
    }
}
