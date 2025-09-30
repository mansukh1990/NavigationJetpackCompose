package com.example.typesafenavigation.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.typesafenavigation.R
import com.example.typesafenavigation.app.components.ButtonComponent
import com.example.typesafenavigation.app.components.CheckBoxComponent
import com.example.typesafenavigation.app.components.DividerTextComponent
import com.example.typesafenavigation.app.components.HeadingTextComponent
import com.example.typesafenavigation.app.components.NormalTextComponent
import com.example.typesafenavigation.app.components.PasswordTextFieldComponent
import com.example.typesafenavigation.app.components.SpannableLoginTextLayout
import com.example.typesafenavigation.app.components.TextFieldComponent
import com.example.typesafenavigation.app.data.LoginViewModel
import com.example.typesafenavigation.app.data.UiEvent

@Composable
fun SignUpScreen(
    onPrivacyPolicyClick: () -> Unit,
    onTermsClick: () -> Unit,
    onLoginClick: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
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
                    loginViewModel.onEvent(UiEvent.FirstNameChange(firstName = it))
                }
            )
            TextFieldComponent(
                labelValue = stringResource(id = R.string.str_last_name),
                painterResource = painterResource(R.drawable.profile),
                onTextSelected = {
                    loginViewModel.onEvent(UiEvent.LastNameChange(lastName = it))
                }
            )
            TextFieldComponent(
                labelValue = stringResource(id = R.string.str_email),
                painterResource = painterResource(id = R.drawable.baseline_email_24),
                onTextSelected = {
                    loginViewModel.onEvent(UiEvent.EmailChange(email = it))
                }
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.str_password),
                painterResource = painterResource(R.drawable.baseline_lock_24),
                onTextSelected = {
                    loginViewModel.onEvent(UiEvent.PasswordChange(password = it))
                }
            )
            CheckBoxComponent(
                value = stringResource(id = R.string.str_terms_and_conditions),
                onPrivacyPolicyClick = { onPrivacyPolicyClick() },
                onTermsClick = { onTermsClick() }
            )
            Spacer(Modifier.height(80.dp))
            ButtonComponent(
                value = stringResource(R.string.str_register),
                onButtonClick = {
                    loginViewModel.onEvent(UiEvent.RegistrationButtonClick)
                })
            Spacer(Modifier.height(20.dp))
            DividerTextComponent()
            Spacer(Modifier.height(130.dp))
            SpannableLoginTextLayout(
                tryingToLogin = true,
                onLoginClick = {
                    onLoginClick()
                },
                onRegisterClick = {
                }
            )
        }
    }

}

