package com.example.typesafenavigation.authenticationWithFirebase

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.Serializable

sealed interface AuthenticationDest {
    @Serializable
    data object Root : AuthenticationDest

    @Serializable
    data object Login : AuthenticationDest

    @Serializable
    data object Register : AuthenticationDest
}

sealed interface HomeDest {
    @Serializable
    data object Root : HomeDest

    @Serializable
    data object Home : HomeDest
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthenticationDest.Root
    ) {
        navigation<AuthenticationDest.Root>(startDestination = AuthenticationDest.Login) {
            composable<AuthenticationDest.Login> {
                LoginScreen(
                    onRegisterClick = {
                        navController.navigate(AuthenticationDest.Register)
                    },
                    onLoginSuccess = {
                        navController.navigate(HomeDest.Home) {
                            popUpTo(AuthenticationDest.Login) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<AuthenticationDest.Register>() {
                RegisterScreen(
                    onLoginClick = {
                        navController.navigate(AuthenticationDest.Login)
                    },
                    onSuccess = {
                        navController.navigate(HomeDest.Home) {
                            popUpTo(AuthenticationDest.Login) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            navigation<HomeDest.Root>(startDestination = HomeDest.Home){
                composable<HomeDest.Home>{
                    HomeScreen(
                        onLogout = {
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate(AuthenticationDest.Login){
                                popUpTo(HomeDest.Home){
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }

}