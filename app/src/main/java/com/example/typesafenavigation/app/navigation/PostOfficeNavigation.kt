package com.example.typesafenavigation.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.typesafenavigation.app.screens.LoginScreen
import com.example.typesafenavigation.app.screens.PrivacyAndPolicyScreen
import com.example.typesafenavigation.app.screens.SignUpScreen
import com.example.typesafenavigation.app.screens.TermsAndConditionsScreen


@Composable
fun PostOfficeNestedNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthenticationRoot.AuthRoot
    ) {
        navigation<AuthenticationRoot.AuthRoot>(startDestination = AuthenticationRoot.SignUpScreen) {
            composable<AuthenticationRoot.SignUpScreen> {
                SignUpScreen(
                    onPrivacyPolicyClick = {
                        navController.navigate(AuthenticationRoot.PrivacyPolicyScreen)
                    },
                    onTermsClick = {
                        navController.navigate(AuthenticationRoot.TermsAndConditionScreen)
                    },
                    onLoginClick = {
                        navController.navigate(AuthenticationRoot.LoginScreen)
                    }
                )
            }
            composable<AuthenticationRoot.PrivacyPolicyScreen> {
                PrivacyAndPolicyScreen(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
            composable<AuthenticationRoot.TermsAndConditionScreen> {
                TermsAndConditionsScreen(onBack = {
                    navController.popBackStack()
                })
            }
            composable<AuthenticationRoot.LoginScreen> {
                LoginScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onRegister = {
                        navController.navigate(AuthenticationRoot.SignUpScreen)
                    })
            }
        }

    }
}