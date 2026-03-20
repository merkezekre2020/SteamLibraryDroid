package com.artcapone.steamlibrary.feature.detail

data class GameDetailUiState(
    val appId: Long = 0,
    val title: String = "",
    val imageUrl: String? = null,
    val description: String? = null,
    val status: String = "backlog",
    val rating: Int? = null,
    val note: String = "",
    val favorite: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
