package com.example.typesafenavigation.loginAndSignupWithFirebase.data

sealed class SignupUiEvent {
    data class FirstNameChange(val firstName: String) : SignupUiEvent()
    data class LastNameChange(val lastName: String) : SignupUiEvent()
    data class EmailChange(val email: String) : SignupUiEvent()
    data class PasswordChange(val password: String) : SignupUiEvent()
    data class PrivacyPolicyCheckChange(val status: Boolean) : SignupUiEvent()

    object RegistrationButtonClick : SignupUiEvent()

}