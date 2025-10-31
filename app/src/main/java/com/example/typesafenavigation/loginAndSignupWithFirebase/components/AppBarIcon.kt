package com.example.typesafenavigation.loginAndSignupWithFirebase.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppBarIcon(val icon: ImageVector) {
    data object Menu : AppBarIcon(Icons.Filled.Menu)
    data object Back : AppBarIcon(Icons.AutoMirrored.Filled.ArrowBack)
    data object Search : AppBarIcon(Icons.Filled.Search)
    data object Logout : AppBarIcon(Icons.AutoMirrored.Filled.Logout)
}