package com.artcapone.steamlibrary.data.remote.api

import com.artcapone.steamlibrary.data.remote.dto.PocketBaseAuthRequest
import com.artcapone.steamlibrary.data.remote.dto.PocketBaseAuthResponse
import com.artcapone.steamlibrary.data.remote.dto.PocketBaseSteamProfileRecord
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PocketBaseApiService {
    @POST("/api/collections/users/auth-with-password")
    suspend fun authWithPassword(
        @Body request: PocketBaseAuthRequest
    ): PocketBaseAuthResponse

    @POST("/api/collections/steam_profiles/records")
    suspend fun createSteamProfile(
        @Header("Authorization") authHeader: String,
        @Body request: PocketBaseSteamProfileRecord
    ): PocketBaseSteamProfileRecord
}
