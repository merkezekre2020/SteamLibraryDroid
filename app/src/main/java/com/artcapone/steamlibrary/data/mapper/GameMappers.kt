package com.artcapone.steamlibrary.data.mapper

import com.artcapone.steamlibrary.data.local.entity.GameEntity
import com.artcapone.steamlibrary.data.local.entity.UserGameEntryEntity
import com.artcapone.steamlibrary.data.model.Game
import com.artcapone.steamlibrary.data.model.UserGameEntry

fun Game.toEntity(): GameEntity = GameEntity(
    appId = appId,
    name = name,
    imageUrl = imageUrl,
    playtimeMinutes = playtimeMinutes,
    description = description
)

fun GameEntity.toModel(): Game = Game(
    appId = appId,
    name = name,
    imageUrl = imageUrl,
    playtimeMinutes = playtimeMinutes,
    description = description
)

fun UserGameEntry.toEntity(): UserGameEntryEntity = UserGameEntryEntity(
    gameId = gameId.toLongOrNull() ?: 0L,
    status = status,
    rating = rating,
    note = note,
    favorite = favorite,
    tags = tags.joinToString(",")
)

fun UserGameEntryEntity.toModel(): UserGameEntry = UserGameEntry(
    gameId = gameId.toString(),
    status = status,
    rating = rating,
    note = note,
    favorite = favorite,
    tags = tags.split(",").filter { it.isNotBlank() }
)
