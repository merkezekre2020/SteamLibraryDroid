package com.artcapone.steamlibrary.data.remote

import com.artcapone.steamlibrary.data.model.SteamProfile
import com.artcapone.steamlibrary.data.remote.dto.PocketBaseAuthRequest
import com.artcapone.steamlibrary.data.remote.dto.PocketBaseSteamProfileRecord

class PocketBaseClient {
    private var token: String? = null
    private var userId: String? = null

    suspend fun login(email: String, password: String): Result<Unit> {
        return runCatching {
            val response = NetworkModule.pocketBaseApi.authWithPassword(
                PocketBaseAuthRequest(identity = email, password = password)
            )
            token = response.token
            userId = response.record.id
        }
    }

    suspend fun saveSteamProfile(profile: SteamProfile): Result<Unit> {
        val safeToken = token ?: return Result.failure(IllegalStateException("Not authenticated"))
        val safeUserId = userId ?: return Result.failure(IllegalStateException("Missing user id"))

        return runCatching {
            NetworkModule.pocketBaseApi.createSteamProfile(
                authHeader = "Bearer $safeToken",
                request = PocketBaseSteamProfileRecord(
                    userId = safeUserId,
                    steamId = profile.steamId,
                    profileUrl = profile.profileUrl,
                    personaName = profile.personaName,
                    avatarUrl = profile.avatarUrl,
                    isPublic = profile.isPublic,
                    lastSyncedAt = profile.lastSyncedAt
                )
            )
        }.map { Unit }
    }
}
