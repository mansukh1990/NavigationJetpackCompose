package com.example.typesafenavigation.app.data.rule

object Validator {

    fun validateFirstName(firstName: String): ValidateResult {
        return ValidateResult(
            status = firstName.isNotEmpty() && firstName.length >= 6
        )

    }

    fun validateLastName(lastName: String): ValidateResult {
        return ValidateResult(
            lastName.isNotEmpty() && lastName.length >= 4
        )

    }

    fun validateEmail(email: String): ValidateResult {
        return ValidateResult(
            status = email.isNotEmpty()
        )

    }

    fun validatePassword(password: String): ValidateResult {
        return ValidateResult(
            status = password.isNotEmpty() && password.length >= 6
        )
    }
    fun validatePrivacyPolicyAcceptance(statusValue: Boolean): ValidateResult {
        return ValidateResult(
            status = statusValue
        )
    }

}

data class ValidateResult(
    val status: Boolean = false
)