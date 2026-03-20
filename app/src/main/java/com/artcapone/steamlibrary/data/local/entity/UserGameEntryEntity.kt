package com.artcapone.steamlibrary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_game_entries")
data class UserGameEntryEntity(
    @PrimaryKey val gameId: Long,
    val status: String = "backlog",
    val rating: Int? = null,
    val note: String = "",
    val favorite: Boolean = false,
    val tags: String = ""
)
