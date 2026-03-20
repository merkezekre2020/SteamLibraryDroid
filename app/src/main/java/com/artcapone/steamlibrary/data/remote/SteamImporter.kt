package com.artcapone.steamlibrary.data.remote

import com.artcapone.steamlibrary.core.config.AppConfig
import com.artcapone.steamlibrary.data.model.Game

class SteamImporter {
    suspend fun importOwnedGames(
        steamId: String,
        apiKey: String
    ): Result<List<Game>> {
        return runCatching {
            NetworkModule.steamApi.getOwnedGames(
                apiKey = apiKey,
                steamId = steamId
            ).response.games.map { dto ->
                Game(
                    appId = dto.appId,
                    name = dto.name ?: "Unknown Game",
                    imageUrl = dto.iconHash?.let { "${AppConfig.steamStoreCdnBaseUrl}/${dto.appId}/${it}.jpg" },
                    playtimeMinutes = dto.playtimeForever
                )
            }
        }
    }
}
