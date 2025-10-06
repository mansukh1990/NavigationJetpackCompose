package com.example.typesafenavigation.app.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.typesafenavigation.app.data.rule.Validator
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    val loginUiState = mutableStateOf(LoginUiState())

    var btnLoginState = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.EmailChange -> {
                loginUiState.value = loginUiState.value.copy(
                    email = event.email
                )
            }

            is LoginUiEvent.PasswordChange -> {
                loginUiState.value = loginUiState.value.copy(
                    password = event.password
                )
            }

            is LoginUiEvent.OnLoginButtonButtonClick -> {
                login()
            }
        }
        validateDataWithRules()
    }

    private fun validateDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUiState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = loginUiState.value.password
        )

        loginUiState.value = loginUiState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )
        btnLoginState.value = emailResult.status && passwordResult.status


    }

    private fun login() {
        loginInProgress.value = true
        val email = loginUiState.value.email
        val password = loginUiState.value.password

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_login")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
                if (it.isSuccessful) {
                    loginInProgress.value = false
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_login_failure")
                Log.d(TAG, "exception = ${it.message}")
                loginInProgress.value = false
            }
    }

}