package com.artcapone.steamlibrary.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.artcapone.steamlibrary.ui.navigation.SteamLibraryNavHost
import com.artcapone.steamlibrary.ui.theme.SteamLibraryTheme

@Composable
fun SteamLibraryApp() {
    SteamLibraryTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            SteamLibraryNavHost(navController = navController)
        }
    }
}
