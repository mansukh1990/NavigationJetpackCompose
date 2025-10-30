package com.example.typesafenavigation.cloudFireStore.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.typesafenavigation.cloudFireStore.screens.cars.CarListScreen
import com.example.typesafenavigation.ui.theme.TypeSafeNavigationTheme

class CarListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TypeSafeNavigationTheme {
                CarListScreen()
            }
        }
    }
}