package com.example.typesafenavigation.loginAndSignupWithFirebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.typesafenavigation.loginAndSignupWithFirebase.screens.HomeScreen
import com.example.typesafenavigation.loginAndSignupWithFirebase.screens.LoginScreen
import com.example.typesafenavigation.loginAndSignupWithFirebase.screens.PrivacyAndPolicyScreen
import com.example.typesafenavigation.loginAndSignupWithFirebase.screens.SignUpScreen
import com.example.typesafenavigation.loginAndSignupWithFirebase.screens.TermsAndConditionsScreen


@Composable
fun PostOfficeNestedNavigation() {

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
                    },
                    onRegisterClick = {
                        navController.navigate(HomeScreenDest.HomeScreen) {
                        }
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
                    onLoginClick = {
                        navController.navigate(HomeScreenDest.HomeScreen)
                    },
                    onRegisterClick = {
                        navController.navigate(AuthenticationRoot.SignUpScreen)
                    }
                )
            }
        }
        navigation<HomeScreenDest.HomeRoot>(startDestination = HomeScreenDest.HomeScreen) {
            composable<HomeScreenDest.HomeScreen> {
                HomeScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onLogout = {
                        navController.navigate(AuthenticationRoot.LoginScreen)
                    }
                )
            }
        }


    }
}