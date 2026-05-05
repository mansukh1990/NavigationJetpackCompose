package com.example.typesafenavigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SetupNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen
    ) {
        composable<Screen.FirstScreen> {
            FirstScreenComposable {
                navController.navigate(
                    Screen.FirstScreen
                )
            }
        }
        composable<Screen.SecondScreen>(
        ) {
            SecondScreenComposable {
                navController.navigate(Screen.FirstScreen) {
                    popUpTo<Screen.FirstScreen> { inclusive = true }
                }
            }


        }
    }
}