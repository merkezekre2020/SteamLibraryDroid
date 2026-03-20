package com.artcapone.steamlibrary.feature.library

data class LibraryUiState(
    val isLoading: Boolean = false,
    val query: String = "",
    val selectedFilter: String = "all",
    val games: List<GameListItem> = emptyList(),
    val errorMessage: String? = null
)

data class GameListItem(
    val appId: Long,
    val name: String,
    val imageUrl: String? = null,
    val playtimeMinutes: Int = 0,
    val status: String = "backlog",
    val favorite: Boolean = false
)
