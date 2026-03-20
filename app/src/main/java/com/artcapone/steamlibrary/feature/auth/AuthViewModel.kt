package com.artcapone.steamlibrary.feature.auth

import com.artcapone.steamlibrary.data.repository.AuthRepository

class AuthViewModel(
    private val authRepository: AuthRepository
) {
    fun login(email: String, password: String): AuthUiState {
        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()

        if (trimmedEmail.isBlank()) {
            return AuthUiState(
                email = trimmedEmail,
                password = trimmedPassword,
                errorMessage = "E-posta boş olamaz."
            )
        }

        if (!trimmedEmail.contains("@") || !trimmedEmail.contains(".")) {
            return AuthUiState(
                email = trimmedEmail,
                password = trimmedPassword,
                errorMessage = "Geçerli bir e-posta gir."
            )
        }

        if (trimmedPassword.length < 6) {
            return AuthUiState(
                email = trimmedEmail,
                password = trimmedPassword,
                errorMessage = "Şifre en az 6 karakter olmalı."
            )
        }

        val success = authRepository.login(trimmedEmail, trimmedPassword)
        return AuthUiState(
            email = trimmedEmail,
            password = "",
            isLoggedIn = success,
            errorMessage = if (success) null else "Giriş başarısız."
        )
    }
}
