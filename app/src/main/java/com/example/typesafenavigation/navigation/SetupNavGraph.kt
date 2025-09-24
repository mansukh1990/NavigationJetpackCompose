package com.example.typesafenavigation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

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
                    Screen.SecondScreen(
                        name = "Mansukh Mano",
                        age = "33"
                    )
                )
            }
        }
        composable<Screen.SecondScreen> {
            val args = it.toRoute<Screen.SecondScreen>()
            LaunchedEffect(key1 = Unit) {
                Log.d("Navigation", "${args.name} ${args.age}")
            }
            SecondScreenComposable {
                navController.navigate(Screen.FirstScreen) {
                    popUpTo<Screen.FirstScreen> { inclusive = true }
                }
            }


        }
    }
}