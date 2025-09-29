package com.example.typesafenavigation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlin.reflect.typeOf

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
                        null
                    )
                )
            }
        }
        composable<Screen.SecondScreen>(
            typeMap = mapOf(
                typeOf<Dummy>() to CustomNavType<Dummy>(
                    Dummy::class.java, Dummy.serializer()
                ),
                typeOf<Dummy?>() to CustomNavType<Dummy>(
                    Dummy::class.java, Dummy.serializer()
                )
            )
        ) {
            val args = it.toRoute<Screen.SecondScreen>().dummy
            val name = args?.name ?: "null"
            val age = args?.age ?: 0
            LaunchedEffect(key1 = Unit) {
                Log.d("Navigation", "$name $age")
            }
            SecondScreenComposable(
                name = name, age = age.toString()
            ) {
                navController.navigate(Screen.FirstScreen) {
                    popUpTo<Screen.FirstScreen> { inclusive = true }
                }
            }


        }
    }
}