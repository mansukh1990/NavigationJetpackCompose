package com.example.typesafenavigation.authenticationWithFirebase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authManager: AuthenticationManager
) : ViewModel() {

    var state by mutableStateOf(AuthState())
        private set

    fun onEmailChange(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state = state.copy(password = password)
    }

    fun register() = viewModelScope.launch {
        authManager.loginWithEmailAndPassword(state.email, state.password).collect { response ->
            when (response) {
                is AuthResponse.Success -> state = state.copy(isSuccess = true)
                is AuthResponse.Error -> state = state.copy(isError = response.message)
            }

        }
    }

    fun login() = viewModelScope.launch {
        authManager.createAccountWithEmailAndPassword(state.email, state.password)
            .collect { response ->
                when (response) {
                    is AuthResponse.Success -> state = state.copy(isSuccess = true)
                    is AuthResponse.Error -> state = state.copy(isError = response.message)
                }
            }
    }

    fun googleLogin() = viewModelScope.launch {
        authManager.signInWithGoogle().collect { response ->
            when (response) {
                is AuthResponse.Success -> state = state.copy(isSuccess = true)
                is AuthResponse.Error -> state = state.copy(isError = response.message)
            }

        }
    }
}