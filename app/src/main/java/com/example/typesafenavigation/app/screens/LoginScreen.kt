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
import com.example.typesafenavigation.R
import com.example.typesafenavigation.app.components.ButtonComponent
import com.example.typesafenavigation.app.components.DividerTextComponent
import com.example.typesafenavigation.app.components.HeadingTextComponent
import com.example.typesafenavigation.app.components.NormalTextComponent
import com.example.typesafenavigation.app.components.PasswordTextFieldComponent
import com.example.typesafenavigation.app.components.SpannableLoginTextLayout
import com.example.typesafenavigation.app.components.TextFieldComponent
import com.example.typesafenavigation.app.components.UnderLineTextComponent

@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onRegister: () -> Unit
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
                onTextSelected = {}
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.str_password),
                painterResource = painterResource(id = R.drawable.baseline_lock_24),
                onTextSelected = {}
            )
            Spacer(Modifier.height(40.dp))
            UnderLineTextComponent(value = stringResource(id = R.string.forget_password))
            Spacer(Modifier.height(80.dp))
            ButtonComponent(
                value = stringResource(id = R.string.str_login),
                onButtonClick = {})
            Spacer(Modifier.height(20.dp))
            DividerTextComponent()
            SpannableLoginTextLayout(
                tryingToLogin = false,
                onLoginClick = {
                    onBack()
                },
                onRegisterClick = {
                    onRegister()
                }
            )

        }
    }
}