package com.artcapone.steamlibrary.data.remote.api

import com.artcapone.steamlibrary.data.remote.dto.SteamOwnedGamesEnvelope
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamApiService {
    @GET("IPlayerService/GetOwnedGames/v0001/")
    suspend fun getOwnedGames(
        @Query("key") apiKey: String,
        @Query("steamid") steamId: String,
        @Query("include_appinfo") includeAppInfo: Boolean = true,
        @Query("format") format: String = "json"
    ): SteamOwnedGamesEnvelope
}
