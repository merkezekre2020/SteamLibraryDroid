package com.artcapone.steamlibrary.data.repository

import com.artcapone.steamlibrary.data.model.Game
import com.artcapone.steamlibrary.data.model.SteamProfile
import com.artcapone.steamlibrary.data.model.UserGameEntry

class LibraryRepository {
    private var steamProfile: SteamProfile? = null

    private val games = listOf(
        Game(570, "Dota 2", playtimeMinutes = 1200, description = "MOBA kaosu."),
        Game(730, "Counter-Strike 2", playtimeMinutes = 3400, description = "Klasik rekabet."),
        Game(620, "Portal 2", playtimeMinutes = 480, description = "Bulmaca ve şahane yazım."),
        Game(1174180, "Red Dead Redemption 2", playtimeMinutes = 860, description = "Vahşi batı ama depresif kaliteli.")
    )

    private val entries = mutableMapOf(
        570L to UserGameEntry(gameId = "570", status = "playing"),
        730L to UserGameEntry(gameId = "730", status = "completed", favorite = true),
        620L to UserGameEntry(gameId = "620", status = "backlog", note = "Co-op için sakla."),
        1174180L to UserGameEntry(gameId = "1174180", status = "backlog")
    )

    fun bindSteamProfile(input: String): SteamProfile {
        val normalized = input.trim()
        steamProfile = SteamProfile(
            steamId = normalized.filter { it.isDigit() }.ifBlank { "76561198000000000" },
            profileUrl = normalized.takeIf { it.startsWith("http://") || it.startsWith("https://") },
            personaName = "Steam User",
            avatarUrl = null,
            isPublic = true,
            lastSyncedAt = "just now"
        )
        return requireNotNull(steamProfile)
    }

    fun getBoundProfile(): SteamProfile? = steamProfile

    fun getGames(): List<Pair<Game, UserGameEntry?>> = games.map { game -> game to entries[game.appId] }

    fun getGameDetail(appId: Long): Pair<Game?, UserGameEntry?> {
        return games.firstOrNull { it.appId == appId } to entries[appId]
    }

    fun syncLibrary(): Int {
        steamProfile = steamProfile?.copy(lastSyncedAt = "just now")
        return games.size
    }
}
