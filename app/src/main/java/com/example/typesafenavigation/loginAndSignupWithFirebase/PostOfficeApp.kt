package com.example.typesafenavigation.loginAndSignupWithFirebase

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.typesafenavigation.loginAndSignupWithFirebase.navigation.PostOfficeNestedNavigation

@Composable
fun PostOfficeApp(modifier: Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {
        PostOfficeNestedNavigation()


    }

}