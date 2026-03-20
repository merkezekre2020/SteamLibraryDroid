package com.artcapone.steamlibrary.domain

import com.artcapone.steamlibrary.data.model.Game
import com.artcapone.steamlibrary.data.repository.LibraryRepositoryV2

class GetCachedLibraryUseCase(
    private val repository: LibraryRepositoryV2
) {
    suspend operator fun invoke(): List<Game> = repository.getCachedGames()
}
