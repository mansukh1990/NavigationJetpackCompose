package com.example.typesafenavigation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object FirstScreen : Screen

    @Serializable
    data class SecondScreen(
        var name: String,
        var age: String
    ) : Screen

}
