package com.example.typesafenavigation.app.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var registrationUiState = mutableStateOf(RegistrationUiState())

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.FirstNameChange -> {
                registrationUiState.value = registrationUiState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is UiEvent.LastNameChange -> {
                registrationUiState.value = registrationUiState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is UiEvent.EmailChange -> {
                registrationUiState.value = registrationUiState.value.copy(
                    email = event.email
                )
                printState()
            }

            is UiEvent.PasswordChange -> {
                registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )
                printState()
            }

            UiEvent.RegistrationButtonClick -> {
                signUp()
            }
        }

    }

    private fun signUp() {
        Log.d(TAG, "Inside_signup   ")
        printState()
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUiState.value.toString())
    }

}
