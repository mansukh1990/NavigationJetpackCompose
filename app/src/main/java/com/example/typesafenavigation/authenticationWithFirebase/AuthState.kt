package com.example.typesafenavigation.authenticationWithFirebase

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isError: String? = null,
    val isSuccess: Boolean = false
)
