package com.artcapone.steamlibrary.feature.library

import com.artcapone.steamlibrary.data.repository.LibraryRepository
import com.artcapone.steamlibrary.domain.GetCachedLibraryUseCase

class LibraryViewModel(
    private val repository: LibraryRepository,
    private val getCachedLibraryUseCase: GetCachedLibraryUseCase? = null
) {
    fun loadState(): LibraryUiState {
        val items = repository.getGames().map { (game, entry) ->
            GameListItem(
                appId = game.appId,
                name = game.name,
                imageUrl = game.imageUrl,
                playtimeMinutes = game.playtimeMinutes,
                status = entry?.status ?: "backlog",
                favorite = entry?.favorite ?: false
            )
        }
        return LibraryUiState(games = items)
    }

    suspend fun loadCachedState(): LibraryUiState {
        val cachedItems = getCachedLibraryUseCase?.invoke().orEmpty().map { game ->
            GameListItem(
                appId = game.appId,
                name = game.name,
                imageUrl = game.imageUrl,
                playtimeMinutes = game.playtimeMinutes,
                status = "cached",
                favorite = false
            )
        }
        return LibraryUiState(games = cachedItems)
    }

    fun sync(): Int = repository.syncLibrary()
}
