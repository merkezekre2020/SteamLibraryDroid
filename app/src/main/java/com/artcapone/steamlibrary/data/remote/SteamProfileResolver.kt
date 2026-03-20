package com.artcapone.steamlibrary.data.remote

import okhttp3.OkHttpClient
import okhttp3.Request

class SteamProfileResolver(
    private val client: OkHttpClient = OkHttpClient()
) {
    fun resolveSteamId(input: String): Result<String> {
        val trimmed = input.trim()
        val directDigits = trimmed.filter { it.isDigit() }
        if (directDigits.length >= 16) {
            return Result.success(directDigits)
        }

        if (!trimmed.startsWith("http://") && !trimmed.startsWith("https://")) {
            return Result.failure(IllegalArgumentException("Steam ID veya geçerli profil URL'si gir."))
        }

        val normalizedUrl = trimmed.removeSuffix("/")
        val xmlUrl = if (normalizedUrl.endsWith("?xml=1")) normalizedUrl else "$normalizedUrl/?xml=1"

        return runCatching {
            val request = Request.Builder().url(xmlUrl).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) error("Steam profil bilgisi alınamadı: ${response.code}")
                val body = response.body?.string().orEmpty()
                val match = Regex("<steamID64>(\\d+)</steamID64>").find(body)
                match?.groupValues?.get(1)
                    ?: error("Steam ID çözülemedi. Profil public olmayabilir veya URL desteklenmiyor olabilir.")
            }
        }
    }
}
