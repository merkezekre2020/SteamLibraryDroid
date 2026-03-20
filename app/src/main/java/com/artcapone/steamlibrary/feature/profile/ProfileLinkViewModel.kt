package com.artcapone.steamlibrary.feature.profile

import com.artcapone.steamlibrary.data.model.SteamProfile
import com.artcapone.steamlibrary.data.repository.LibraryRepository

class ProfileLinkViewModel(
    private val repository: LibraryRepository
) {
    fun linkProfile(input: String): Result<SteamProfile> {
        val trimmed = input.trim()
        if (trimmed.isBlank()) {
            return Result.failure(IllegalArgumentException("Steam ID veya profil URL gir."))
        }

        val digitsOnly = trimmed.filter { it.isDigit() }
        val looksLikeSteamId = digitsOnly.length >= 8
        val looksLikeUrl = trimmed.startsWith("http://") || trimmed.startsWith("https://")

        if (!looksLikeSteamId && !looksLikeUrl) {
            return Result.failure(IllegalArgumentException("Geçerli bir Steam ID veya URL gir."))
        }

        return runCatching { repository.bindSteamProfile(trimmed) }
    }
}
