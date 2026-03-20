package com.artcapone.steamlibrary.feature.auth

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artcapone.steamlibrary.data.AppContainer

@Composable
fun AuthScreen(onContinue: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var authState by remember { mutableStateOf(AuthUiState()) }
    val viewModel = remember { AuthViewModel(AppContainer.authRepository) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Steam Library Manager", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Giriş akışı production-ready olacak şekilde toparlanıyor. Şimdilik local auth guard var.",
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                authState = authState.copy(errorMessage = null)
            },
            label = { Text("E-posta") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                authState = authState.copy(errorMessage = null)
            },
            label = { Text("Şifre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        )
        authState.errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        Button(
            onClick = {
                authState = viewModel.login(email, password)
                if (authState.isLoggedIn) onContinue()
            },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("Devam et")
        }
    }
}
