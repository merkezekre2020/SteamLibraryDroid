package com.artcapone.steamlibrary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey val appId: Long,
    val name: String,
    val imageUrl: String? = null,
    val playtimeMinutes: Int = 0,
    val description: String? = null
)
