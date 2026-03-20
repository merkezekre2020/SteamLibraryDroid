package com.artcapone.steamlibrary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val SteamDarkColors = darkColorScheme(
    primary = SteamBlue,
    secondary = SteamBlueLight,
    tertiary = SteamAccent,
    background = SteamDark,
    surface = SteamSurface,
    surfaceVariant = SteamSurfaceAlt
)

private val SteamLightColors = lightColorScheme(
    primary = SteamBlue,
    secondary = SteamBlueLight,
    tertiary = SteamAccent,
    background = SteamLight,
    surface = SteamLightSurface,
    surfaceVariant = SteamLightSurfaceAlt
)

@Composable
fun SteamLibraryTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) SteamDarkColors else SteamLightColors
    MaterialTheme(
        colorScheme = colors,
        typography = SteamTypography,
        content = content
    )
}
