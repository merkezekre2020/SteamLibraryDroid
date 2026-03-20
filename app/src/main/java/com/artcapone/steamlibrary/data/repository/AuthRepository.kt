package com.artcapone.steamlibrary.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthRepository {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    fun login(email: String, password: String): Boolean {
        val success = email.isNotBlank() && password.length >= 4
        _isLoggedIn.value = success
        return success
    }

    fun logout() {
        _isLoggedIn.value = false
    }
}
