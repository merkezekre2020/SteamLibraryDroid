package com.artcapone.steamlibrary.feature.detail

import com.artcapone.steamlibrary.data.repository.LibraryRepository

class GameDetailViewModel(
    private val repository: LibraryRepository
) {
    fun load(appId: Long): GameDetailUiState {
        val (game, entry) = repository.getGameDetail(appId)
        return GameDetailUiState(
            appId = game?.appId ?: 0,
            title = game?.name ?: "Unknown Game",
            description = game?.description,
            status = entry?.status ?: "backlog",
            rating = entry?.rating,
            note = entry?.note.orEmpty(),
            favorite = entry?.favorite ?: false
        )
    }
}
