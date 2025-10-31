package com.example.typesafenavigation.loginAndSignupWithFirebase.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.typesafenavigation.R
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.AppBarIcon
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.AppToolBar
import com.example.typesafenavigation.loginAndSignupWithFirebase.data.SignupViewModel

@Composable
fun HomeScreen(
    loginViewModel: SignupViewModel = viewModel(),
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            AppToolBar(
                toolbarTitle = stringResource(id = R.string.home),
                onActionClick = {
                    loginViewModel.logout()
                    onLogout()
                },
                navigationIcon = AppBarIcon.Menu.icon,
                actionIcon = AppBarIcon.Logout.icon,
                onNavigationClick = {
                    onBack()
                }
            )
        }
    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

            }
        }

    }

}