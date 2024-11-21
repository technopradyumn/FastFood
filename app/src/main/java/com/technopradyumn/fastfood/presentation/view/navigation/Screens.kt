package com.technopradyumn.fastfood.presentation.view.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object Splash : Screen()
    @Serializable
    object Welcome : Screen()
    @Serializable
    object Login : Screen()
    @Serializable
    object ForgotPassword : Screen()
    @Serializable
    object SignUp : Screen()
    @Serializable
    object Admin : Screen()
    @Serializable
    object Home : Screen()
    @Serializable
    object Discover : Screen()
    @Serializable
    object Restaurants : Screen()
    @Serializable
    object Search : Screen()
    @Serializable
    object Favorite : Screen()
    @Serializable
    object Profile : Screen()
    @Serializable
    object ItemDetail : Screen()
    @Serializable
    object Cart : Screen()
}