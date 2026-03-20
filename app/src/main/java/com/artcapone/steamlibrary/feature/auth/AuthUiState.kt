package com.artcapone.steamlibrary.feature.auth

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val errorMessage: String? = null
)
