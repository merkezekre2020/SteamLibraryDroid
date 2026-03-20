package com.artcapone.steamlibrary.feature.settings

data class SettingsUiState(
    val linkedSteamProfile: String? = null,
    val syncModeLabel: String = "Manual sync",
    val backendLabel: String = "PocketBase scaffold",
    val cacheLabel: String = "Room cache enabled in architecture"
)
