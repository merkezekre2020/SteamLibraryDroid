package com.artcapone.steamlibrary.data.model

data class SteamProfile(
    val steamId: String,
    val profileUrl: String? = null,
    val personaName: String? = null,
    val avatarUrl: String? = null,
    val isPublic: Boolean = true,
    val lastSyncedAt: String? = null
)
