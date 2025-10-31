package com.example.typesafenavigation.loginAndSignupWithFirebase.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.typesafenavigation.R
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.ButtonComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.CheckBoxComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.DividerTextComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.HeadingTextComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.NormalTextComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.PasswordTextFieldComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.SpannableLoginTextLayout
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.TextFieldComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.data.SignupViewModel
import com.example.typesafenavigation.loginAndSignupWithFirebase.data.SignupUiEvent

@Composable
fun SignUpScreen(
    onPrivacyPolicyClick: () -> Unit,
    onTermsClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    loginViewModel: SignupViewModel = viewModel()
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                NormalTextComponent(value = stringResource(id = R.string.str_hello))
                HeadingTextComponent(value = stringResource(id = R.string.str_create))
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.str_first_name),
                    painterResource = painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        loginViewModel.onEvent(SignupUiEvent.FirstNameChange(firstName = it))
                    },
                    errorStatus = loginViewModel.signupUiState.value.firstNameError
                )
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.str_last_name),
                    painterResource = painterResource(R.drawable.profile),
                    onTextSelected = {
                        loginViewModel.onEvent(SignupUiEvent.LastNameChange(lastName = it))
                    },
                    errorStatus = loginViewModel.signupUiState.value.lastNameError
                )
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.str_email),
                    painterResource = painterResource(id = R.drawable.baseline_email_24),
                    onTextSelected = {
                        loginViewModel.onEvent(SignupUiEvent.EmailChange(email = it))
                    },
                    errorStatus = loginViewModel.signupUiState.value.emailError
                )
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.str_password),
                    painterResource = painterResource(R.drawable.baseline_lock_24),
                    onTextSelected = {
                        loginViewModel.onEvent(SignupUiEvent.PasswordChange(password = it))
                    },
                    errorStatus = loginViewModel.signupUiState.value.passwordError
                )
                CheckBoxComponent(
                    value = stringResource(id = R.string.str_terms_and_conditions),
                    onPrivacyPolicyClick = { onPrivacyPolicyClick() },
                    onTermsClick = { onTermsClick() },
                    onCheckedChange = {
                        loginViewModel.onEvent(SignupUiEvent.PrivacyPolicyCheckChange(status = it))
                    }
                )
                Spacer(Modifier.height(80.dp))
                ButtonComponent(
                    value = stringResource(R.string.str_register),
                    onButtonClick = {
                        loginViewModel.onEvent(SignupUiEvent.RegistrationButtonClick)
                        onRegisterClick()
                    },
                    isEnable = loginViewModel.btnRegisterState.value
                )
                Spacer(Modifier.height(20.dp))
                DividerTextComponent()
                Spacer(Modifier.height(130.dp))
                SpannableLoginTextLayout(
                    tryingToLogin = true,
                    onLoginClick = {
                        onLoginClick()
                    },
                    onRegisterClick = {}
                )
            }
        }
        if (loginViewModel.signUpInProgress.value) {
            CircularProgressIndicator(
                color = ProgressIndicatorDefaults.circularColor
            )

        }

    }

}

