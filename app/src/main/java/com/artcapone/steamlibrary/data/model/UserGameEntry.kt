package com.artcapone.steamlibrary.data.model

data class UserGameEntry(
    val gameId: String,
    val status: String = "backlog",
    val rating: Int? = null,
    val note: String = "",
    val favorite: Boolean = false,
    val tags: List<String> = emptyList()
)
