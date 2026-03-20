package com.artcapone.steamlibrary.domain

import com.artcapone.steamlibrary.data.model.Game
import com.artcapone.steamlibrary.data.repository.LibraryRepositoryV2

class SyncLibraryUseCase(
    private val repository: LibraryRepositoryV2
) {
    suspend operator fun invoke(steamId: String, apiKey: String): Result<List<Game>> {
        return repository.importGames(steamId, apiKey)
    }
}
