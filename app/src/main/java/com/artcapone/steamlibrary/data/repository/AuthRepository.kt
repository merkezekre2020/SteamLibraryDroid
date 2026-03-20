package com.artcapone.steamlibrary.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthRepository {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private var currentEmail: String? = null

    fun login(email: String, password: String): Boolean {
        val success = email.isNotBlank() && password.length >= 6
        if (success) {
            currentEmail = email
        }
        _isLoggedIn.value = success
        return success
    }

    fun logout() {
        currentEmail = null
        _isLoggedIn.value = false
    }

    fun currentUserEmail(): String? = currentEmail
}
