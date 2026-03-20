package com.artcapone.steamlibrary.feature.library

import com.artcapone.steamlibrary.data.repository.LibraryRepository
import com.artcapone.steamlibrary.domain.GetCachedLibraryUseCase

class LibraryViewModel(
    private val repository: LibraryRepository,
    private val getCachedLibraryUseCase: GetCachedLibraryUseCase? = null
) {
    private fun allItems(): List<GameListItem> {
        return repository.getGames().map { (game, entry) ->
            GameListItem(
                appId = game.appId,
                name = game.name,
                imageUrl = game.imageUrl,
                playtimeMinutes = game.playtimeMinutes,
                status = entry?.status ?: "backlog",
                favorite = entry?.favorite ?: false
            )
        }
    }

    fun loadState(query: String = "", selectedFilter: String = "all"): LibraryUiState {
        val filtered = filterItems(
            items = allItems(),
            query = query,
            selectedFilter = selectedFilter
        )
        return LibraryUiState(
            query = query,
            selectedFilter = selectedFilter,
            games = filtered
        )
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

    fun updateQuery(currentFilter: String, query: String): LibraryUiState {
        return loadState(query = query, selectedFilter = currentFilter)
    }

    fun updateFilter(currentQuery: String, filter: String): LibraryUiState {
        return loadState(query = currentQuery, selectedFilter = filter)
    }

    suspend fun sync(): Int = repository.syncLibrary()

    private fun filterItems(
        items: List<GameListItem>,
        query: String,
        selectedFilter: String
    ): List<GameListItem> {
        val normalizedQuery = query.trim().lowercase()
        return items.filter { item ->
            val matchesQuery = normalizedQuery.isBlank() || item.name.lowercase().contains(normalizedQuery)
            val matchesFilter = when (selectedFilter) {
                "favorites" -> item.favorite
                "backlog", "playing", "completed", "dropped" -> item.status == selectedFilter
                else -> true
            }
            matchesQuery && matchesFilter
        }
    }
}
