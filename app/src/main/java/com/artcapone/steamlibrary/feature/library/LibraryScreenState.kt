package com.artcapone.steamlibrary.feature.library

data class LibraryScreenState(
    val items: List<GameListItem> = emptyList(),
    val isLoading: Boolean = false,
    val syncMessage: String? = null,
    val errorMessage: String? = null
)
