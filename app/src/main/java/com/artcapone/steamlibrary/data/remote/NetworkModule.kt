package com.artcapone.steamlibrary.data.remote

import com.artcapone.steamlibrary.core.config.AppConfig
import com.artcapone.steamlibrary.data.remote.api.PocketBaseApiService
import com.artcapone.steamlibrary.data.remote.api.SteamApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val contentType = "application/json".toMediaType()

    val steamApi: SteamApiService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConfig.steamApiBaseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(SteamApiService::class.java)
    }

    val pocketBaseApi: PocketBaseApiService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConfig.pocketBaseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(PocketBaseApiService::class.java)
    }
}
