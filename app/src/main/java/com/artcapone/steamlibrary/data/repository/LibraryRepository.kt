package com.artcapone.steamlibrary.data.repository

import com.artcapone.steamlibrary.core.config.AppConfig
import com.artcapone.steamlibrary.data.model.Game
import com.artcapone.steamlibrary.data.model.SteamProfile
import com.artcapone.steamlibrary.data.model.UserGameEntry
import com.artcapone.steamlibrary.data.remote.SteamImporter
import com.artcapone.steamlibrary.data.remote.SteamProfileResolver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LibraryRepository {
    private var steamProfile: SteamProfile? = null
    private val steamImporter = SteamImporter()
    private val steamProfileResolver = SteamProfileResolver()

    private var games: List<Game> = emptyList()
    private val entries = mutableMapOf<Long, UserGameEntry>()

    suspend fun bindSteamProfile(input: String): SteamProfile = withContext(Dispatchers.IO) {
        val normalized = input.trim()
        val resolvedSteamId = steamProfileResolver.resolveSteamId(normalized).getOrElse {
            throw IllegalArgumentException(it.message ?: "Steam ID çözülemedi")
        }

        steamProfile = SteamProfile(
            steamId = resolvedSteamId,
            profileUrl = normalized.takeIf { it.startsWith("http://") || it.startsWith("https://") },
            personaName = "Steam User",
            avatarUrl = null,
            isPublic = true,
            lastSyncedAt = null
        )
        requireNotNull(steamProfile)
    }

    fun getBoundProfile(): SteamProfile? = steamProfile

    fun getGames(): List<Pair<Game, UserGameEntry?>> {
        return games.map { game -> game to entries[game.appId] }
    }

    fun getGameDetail(appId: Long): Pair<Game?, UserGameEntry?> {
        return games.firstOrNull { it.appId == appId } to entries[appId]
    }

    suspend fun syncLibrary(): Int = withContext(Dispatchers.IO) {
        val profile = steamProfile ?: return@withContext 0
        val apiKey = AppConfig.steamApiKey
        if (apiKey.isBlank()) {
            throw IllegalStateException("STEAM_API_KEY eksik. Workflow secret veya local env tanımla.")
        }

        val importedGames = steamImporter.importOwnedGames(profile.steamId, apiKey)
            .getOrElse { throw IllegalStateException(it.message ?: "Steam import başarısız") }

        games = importedGames
        importedGames.forEach { game ->
            entries.putIfAbsent(game.appId, UserGameEntry(gameId = game.appId.toString(), status = "backlog"))
        }
        steamProfile = profile.copy(lastSyncedAt = "just now")
        importedGames.size
    }
}
