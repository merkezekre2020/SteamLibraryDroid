package com.artcapone.steamlibrary.feature.profile

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.artcapone.steamlibrary.data.AppContainer

@Composable
fun ProfileLinkScreen(
    onProfileLinked: (String) -> Unit,
    boundProfileText: String?
) {
    var state by remember { mutableStateOf(ProfileLinkUiState(linkedProfileText = boundProfileText)) }
    val viewModel = remember { ProfileLinkViewModel(AppContainer.libraryRepository) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("🔗 Steam profilini bağla", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(
            "Steam ID veya profil URL gir. Profil public değilse veri gelmeyebilir.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                state.linkedProfileText?.let {
                    Text("Bağlı profil: $it", color = MaterialTheme.colorScheme.primary)
                }
                OutlinedTextField(
                    value = state.input,
                    onValueChange = { state = state.copy(input = it, errorMessage = null) },
                    label = { Text("Steam ID / URL") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                state.errorMessage?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
                Button(
                    onClick = {
                        viewModel.linkProfile(state.input)
                            .onSuccess { profile ->
                                state = state.copy(
                                    linkedProfileText = profile.profileUrl ?: profile.steamId,
                                    errorMessage = null
                                )
                                onProfileLinked(state.input)
                            }
                            .onFailure {
                                state = state.copy(errorMessage = it.message ?: "Profil bağlanamadı")
                            }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Profili bağla")
                }
            }
        }

        Spacer(Modifier.height(4.dp))
        Text("İpucu: vanity URL kullansan da çözmeye çalışıyorum. Çalışmazsa direkt Steam ID gir. 🧐")
    }
}
