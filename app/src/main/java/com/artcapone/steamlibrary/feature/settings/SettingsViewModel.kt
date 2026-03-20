package com.artcapone.steamlibrary.feature.settings

import com.artcapone.steamlibrary.data.repository.LibraryRepository

class SettingsViewModel(
    private val libraryRepository: LibraryRepository
) {
    fun loadState(): SettingsUiState {
        val profile = libraryRepository.getBoundProfile()
        return SettingsUiState(
            linkedSteamProfile = profile?.profileUrl ?: profile?.steamId
        )
    }
}
