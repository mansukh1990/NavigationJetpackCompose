package com.example.typesafenavigation.nestednavigation

import kotlinx.serialization.Serializable

sealed interface AuthDest {

    @Serializable
    data object Root : AuthDest

    @Serializable
    data object AuthScreen : AuthDest
}

sealed interface ProfileDest {

    @Serializable
    data object Root : ProfileDest

    @Serializable
    data object ProfileScreen : ProfileDest
}