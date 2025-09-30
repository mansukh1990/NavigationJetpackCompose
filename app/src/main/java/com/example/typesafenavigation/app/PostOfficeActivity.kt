package com.example.typesafenavigation.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.typesafenavigation.ui.theme.TypeSafeNavigationTheme

class PostOfficeActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Blue.toArgb(),
                Color.Gray.toArgb()
            )
        )
        setContent {
            TypeSafeNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadiing ->
                    PostOfficeApp(
                        modifier = Modifier.padding(innerPadiing)
                    )
                }

            }

        }
    }
}