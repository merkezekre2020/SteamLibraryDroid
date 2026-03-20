package com.artcapone.steamlibrary.data.model

data class Game(
    val appId: Long,
    val name: String,
    val imageUrl: String? = null,
    val playtimeMinutes: Int = 0,
    val description: String? = null
)
