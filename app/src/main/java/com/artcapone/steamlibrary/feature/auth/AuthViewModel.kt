package com.artcapone.steamlibrary.feature.auth

import com.artcapone.steamlibrary.data.repository.AuthRepository

class AuthViewModel(
    private val authRepository: AuthRepository
) {
    fun login(email: String, password: String): Boolean {
        return authRepository.login(email, password)
    }
}
