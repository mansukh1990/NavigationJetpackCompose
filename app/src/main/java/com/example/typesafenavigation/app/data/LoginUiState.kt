package com.example.typesafenavigation.app.data

data class LoginUiState(
    var email: String = "",
    var password: String = "",

    val emailError: Boolean = false,
    val passwordError: Boolean = false,
)