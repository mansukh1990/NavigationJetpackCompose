package com.example.typesafenavigation.loginAndSignupWithFirebase.data

data class SignupUiState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var privacyPolicyAccepted: Boolean = false,


    val firstNameError: Boolean = false,
    val lastNameError: Boolean = false,
    val emailError: Boolean = false,
    val passwordError: Boolean = false,
    val privacyPolicyError: Boolean = false,
)
