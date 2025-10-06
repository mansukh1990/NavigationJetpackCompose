package com.example.typesafenavigation.app.navigation

import kotlinx.serialization.Serializable

sealed interface AuthenticationRoot {

    @Serializable
    data object AuthRoot : AuthenticationRoot

    @Serializable
    data object SignUpScreen : AuthenticationRoot

    @Serializable
    data object TermsAndConditionScreen : AuthenticationRoot

    @Serializable
    data object PrivacyPolicyScreen : AuthenticationRoot

    @Serializable
    data object LoginScreen : AuthenticationRoot
}

sealed interface HomeScreenDest {

    @Serializable
    data object HomeRoot : HomeScreenDest

    @Serializable
    data object HomeScreen : HomeScreenDest
}