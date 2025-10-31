package com.example.typesafenavigation.loginAndSignupWithFirebase.data

sealed class LoginUiEvent {
    data class EmailChange(val email: String) : LoginUiEvent()
    data class PasswordChange(val password: String) : LoginUiEvent()

    object OnLoginButtonButtonClick : LoginUiEvent()

}