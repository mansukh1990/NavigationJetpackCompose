package com.example.typesafenavigation.app.data

sealed class LoginUiEvent {
    data class EmailChange(val email: String) : LoginUiEvent()
    data class PasswordChange(val password: String) : LoginUiEvent()

    object OnLoginButtonButtonClick : LoginUiEvent()

}