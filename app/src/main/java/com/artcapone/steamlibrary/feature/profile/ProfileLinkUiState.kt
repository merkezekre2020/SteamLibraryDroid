package com.artcapone.steamlibrary.feature.profile

data class ProfileLinkUiState(
    val input: String = "",
    val errorMessage: String? = null,
    val linkedProfileText: String? = null
)
