package com.example.typesafenavigation.loginAndSignupWithFirebase.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.typesafenavigation.loginAndSignupWithFirebase.data.rule.Validator
import com.google.firebase.auth.FirebaseAuth

class SignupViewModel(
) : ViewModel() {

    private val TAG = SignupViewModel::class.simpleName
    var signupUiState = mutableStateOf(SignupUiState())
    var btnRegisterState = mutableStateOf(false)
    var signUpInProgress = mutableStateOf(false)


    fun onEvent(event: SignupUiEvent) {
        validateDataWithRules()
        when (event) {
            is SignupUiEvent.FirstNameChange -> {
                signupUiState.value = signupUiState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignupUiEvent.LastNameChange -> {
                signupUiState.value = signupUiState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignupUiEvent.EmailChange -> {
                signupUiState.value = signupUiState.value.copy(
                    email = event.email
                )
                printState()
            }

            is SignupUiEvent.PasswordChange -> {
                signupUiState.value = signupUiState.value.copy(
                    password = event.password
                )
                printState()
            }
            is SignupUiEvent.PrivacyPolicyCheckChange -> {
                signupUiState.value = signupUiState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }

            SignupUiEvent.RegistrationButtonClick -> {
                signUp()
            }
        }

    }
    private fun signUp() {
        Log.d(TAG, "Inside_signup   ")
        printState()
        createUserInFirebase(
            email = signupUiState.value.email,
            password = signupUiState.value.password
        )
    }

    private fun validateDataWithRules() {
        val firstNameResult = Validator.validateFirstName(
            firstName = signupUiState.value.firstName
        )
        val lastNameResult = Validator.validateLastName(
            lastName = signupUiState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email = signupUiState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = signupUiState.value.password
        )
        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = signupUiState.value.privacyPolicyAccepted
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "firstNameResult = $firstNameResult")
        Log.d(TAG, "lastNameResult = $lastNameResult")
        Log.d(TAG, "emailResult = $emailResult")
        Log.d(TAG, "passwordResult = $passwordResult")
        Log.d(TAG, "privacyPolicyResult = $privacyPolicyResult")

        signupUiState.value = signupUiState.value.copy(
            firstNameError = firstNameResult.status,
            lastNameError = lastNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )
        btnRegisterState.value =
            firstNameResult.status &&
                    lastNameResult.status &&
                    emailResult.status &&
                    passwordResult.status &&
                    !privacyPolicyResult.status

    }
    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, signupUiState.value.toString())
    }
    fun createUserInFirebase(email: String, password: String) {
        signUpInProgress.value = true
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
                signUpInProgress.value = false

            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception = ${it.message}")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }

    }

    fun logout() {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside_signedOut")
            } else {
                Log.d(TAG, "Inside_signedIn")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
}


