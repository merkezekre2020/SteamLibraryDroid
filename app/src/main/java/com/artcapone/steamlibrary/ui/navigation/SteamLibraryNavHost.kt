package com.artcapone.steamlibrary.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artcapone.steamlibrary.data.AppContainer
import com.artcapone.steamlibrary.feature.auth.AuthScreen
import com.artcapone.steamlibrary.feature.detail.GameDetailScreen
import com.artcapone.steamlibrary.feature.library.LibraryScreen
import com.artcapone.steamlibrary.feature.profile.ProfileLinkScreen
import com.artcapone.steamlibrary.feature.settings.SettingsScreen

@Composable
fun SteamLibraryNavHost(navController: NavHostController) {
    var selectedGameId by remember { mutableStateOf(620L) }
    val authRepository = remember { AppContainer.authRepository }
    val libraryRepository = remember { AppContainer.libraryRepository }

    NavHost(
        navController = navController,
        startDestination = Routes.Auth
    ) {
        composable(Routes.Auth) {
            AuthScreen(
                onContinue = { navController.navigate(Routes.ProfileLink) }
            )
        }
        composable(Routes.ProfileLink) {
            val profile = libraryRepository.getBoundProfile()
            ProfileLinkScreen(
                boundProfileText = profile?.profileUrl ?: profile?.steamId,
                onProfileLinked = {
                    libraryRepository.bindSteamProfile(it)
                    navController.navigate(Routes.Library)
                }
            )
        }
        composable(Routes.Library) {
            LibraryScreen(
                onGameClick = {
                    selectedGameId = it
                    navController.navigate(Routes.Detail)
                },
                onSettingsClick = { navController.navigate(Routes.Settings) }
            )
        }
        composable(Routes.Detail) {
            GameDetailScreen(
                appId = selectedGameId,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.Settings) {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                onLogout = {
                    authRepository.logout()
                    navController.navigate(Routes.Auth)
                }
            )
        }
    }
}
