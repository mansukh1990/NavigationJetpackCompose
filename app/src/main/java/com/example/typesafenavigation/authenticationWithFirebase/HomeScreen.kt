package com.example.typesafenavigation.authenticationWithFirebase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.typesafenavigation.R
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.AppBarIcon
import com.example.typesafenavigation.loginAndSignupWithFirebase.components.AppToolBar

@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            AppToolBar(
                toolbarTitle = stringResource(id = R.string.home),
                onActionClick = {
                    onLogout()
                },
                navigationIcon = AppBarIcon.Menu.icon,
                actionIcon = AppBarIcon.Logout.icon,
                onNavigationClick = {
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