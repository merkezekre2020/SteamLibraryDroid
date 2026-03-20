package com.artcapone.steamlibrary.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PocketBaseAuthRequest(
    val identity: String,
    val password: String
)

@Serializable
data class PocketBaseAuthResponse(
    val token: String = "",
    val record: PocketBaseUserRecord = PocketBaseUserRecord()
)

@Serializable
data class PocketBaseUserRecord(
    val id: String = "",
    val email: String = ""
)

@Serializable
data class PocketBaseSteamProfileRecord(
    val id: String? = null,
    @SerialName("userId") val userId: String,
    @SerialName("steamId") val steamId: String,
    @SerialName("profileUrl") val profileUrl: String? = null,
    @SerialName("personaName") val personaName: String? = null,
    @SerialName("avatarUrl") val avatarUrl: String? = null,
    @SerialName("isPublic") val isPublic: Boolean = true,
    @SerialName("lastSyncedAt") val lastSyncedAt: String? = null
)
