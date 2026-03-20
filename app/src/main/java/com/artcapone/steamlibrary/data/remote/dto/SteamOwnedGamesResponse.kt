package com.artcapone.steamlibrary.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SteamOwnedGamesEnvelope(
    @SerialName("response")
    val response: SteamOwnedGamesResponse = SteamOwnedGamesResponse()
)

@Serializable
data class SteamOwnedGamesResponse(
    @SerialName("game_count")
    val gameCount: Int = 0,
    @SerialName("games")
    val games: List<SteamOwnedGameDto> = emptyList()
)

@Serializable
data class SteamOwnedGameDto(
    @SerialName("appid")
    val appId: Long,
    val name: String? = null,
    @SerialName("playtime_forever")
    val playtimeForever: Int = 0,
    @SerialName("playtime_2weeks")
    val playtimeRecent: Int = 0,
    @SerialName("img_icon_url")
    val iconHash: String? = null
)
