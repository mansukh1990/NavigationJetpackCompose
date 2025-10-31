package com.example.typesafenavigation.loginAndSignupWithFirebase.data

data class LoginUiState(
    var email: String = "",
    var password: String = "",
    val emailError: Boolean = false,
    val passwordError: Boolean = false,
)