package com.artcapone.steamlibrary.core.config

import com.artcapone.steamlibrary.BuildConfig

object AppConfig {
    const val pocketBaseUrl = "http://10.0.2.2:8090"
    const val steamApiBaseUrl = "https://api.steampowered.com"
    const val steamStoreCdnBaseUrl = "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps"
    val steamApiKey: String = BuildConfig.STEAM_API_KEY
}
