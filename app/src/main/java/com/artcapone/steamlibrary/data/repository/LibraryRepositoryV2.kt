package com.artcapone.steamlibrary.data.repository

import com.artcapone.steamlibrary.data.local.dao.GameDao
import com.artcapone.steamlibrary.data.local.dao.UserGameEntryDao
import com.artcapone.steamlibrary.data.mapper.toEntity
import com.artcapone.steamlibrary.data.mapper.toModel
import com.artcapone.steamlibrary.data.model.Game
import com.artcapone.steamlibrary.data.model.UserGameEntry
import com.artcapone.steamlibrary.data.remote.SteamImporter

class LibraryRepositoryV2(
    private val gameDao: GameDao?,
    private val userGameEntryDao: UserGameEntryDao?,
    private val steamImporter: SteamImporter
) {
    suspend fun importGames(steamId: String, apiKey: String): Result<List<Game>> {
        return steamImporter.importOwnedGames(steamId, apiKey).onSuccess { games ->
            gameDao?.upsertAll(games.map { it.toEntity() })
        }
    }

    suspend fun getCachedGames(): List<Game> {
        return gameDao?.getAll()?.map { it.toModel() }.orEmpty()
    }

    suspend fun getCachedEntry(gameId: Long): UserGameEntry? {
        return userGameEntryDao?.getByGameId(gameId)?.toModel()
    }

    suspend fun saveEntry(entry: UserGameEntry) {
        userGameEntryDao?.upsert(entry.toEntity())
    }
}
