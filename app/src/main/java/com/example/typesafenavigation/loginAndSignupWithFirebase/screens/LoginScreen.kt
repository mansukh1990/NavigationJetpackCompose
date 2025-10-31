package com.example.typesafenavigation.loginAndSignupWithFirebase.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.DividerTextComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.HeadingTextComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.NormalTextComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.PasswordTextFieldComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.SpannableLoginTextLayout
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.TextFieldComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.UnderLineTextComponent
import com.example.typesafenavigation.loginAndSignupWithFirebase.data.LoginUiEvent
import com.example.typesafenavigation.loginAndSignupWithFirebase.data.LoginViewModel

@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
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
                NormalTextComponent(value = stringResource(id = R.string.str_login))
                HeadingTextComponent(value = stringResource(id = R.string.str_welcome_back))
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.str_email),
                    painterResource = painterResource(id = R.drawable.baseline_email_24),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUiEvent.EmailChange(email = it))
                    },
                    errorStatus = loginViewModel.loginUiState.value.emailError
                )
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.str_password),
                    painterResource = painterResource(id = R.drawable.baseline_lock_24),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUiEvent.PasswordChange(password = it))
                    },
                    errorStatus = loginViewModel.loginUiState.value.passwordError
                )
                Spacer(Modifier.height(40.dp))
                UnderLineTextComponent(value = stringResource(id = R.string.forget_password))
                Spacer(Modifier.height(80.dp))
                ButtonComponent(
                    value = stringResource(id = R.string.str_login),
                    onButtonClick = {
                        loginViewModel.onEvent(LoginUiEvent.OnLoginButtonButtonClick)
                        onLoginClick()
                    },
                    isEnable = loginViewModel.btnLoginState.value
                )
                Spacer(Modifier.height(20.dp))
                DividerTextComponent()
                SpannableLoginTextLayout(
                    tryingToLogin = false,
                    onLoginClick = {
                        onBack()
                    },
                    onRegisterClick = {
                        onRegisterClick()
                    }
                )

            }
        }
        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }

    }

}