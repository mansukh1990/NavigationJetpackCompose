package com.example.typesafenavigation.nestednavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun NestedNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthDest.Root
    ) {
        navigation<AuthDest.Root>(startDestination = AuthDest.AuthScreen) {
            composable<AuthDest.AuthScreen> {
                AuthScreen {
                    navController.navigate(ProfileDest.ProfileScreen)
                }
            }
        }
        navigation<ProfileDest.Root>(startDestination = ProfileDest.ProfileScreen) {
            composable<ProfileDest.ProfileScreen> {
                ProfileScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
fun AuthScreen(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick
        ) {
            Text(
                text = "Auth Screen"
            )
        }
    }
}

@Composable
fun ProfileScreen(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick
        ) {
            Text(
                text = "Profile Screen"
            )
        }
    }
}