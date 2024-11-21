package com.technopradyumn.fastfood.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.technopradyumn.fastfood.presentation.view.AllScreens.AdminScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.CartScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.DiscoverScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.FavoriteScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.ForgotPasswordScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.HomeScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.ItemDetailScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.LoginScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.ProfileScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.RestaurantsScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.SearchScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.SignUpScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.SplashScreen
import com.technopradyumn.fastfood.presentation.view.AllScreens.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash) {
        composable<Screen.Splash> {
            SplashScreen(navController)
        }
        composable<Screen.Welcome> {
            WelcomeScreen(
                onStartWithEmail = {
                    navController.navigate(Screen.SignUp)
                },
                continueWithFacebook = {
                    navController.navigate(Screen.Home)
                },
                continueWithGoogle = {
                    navController.navigate(Screen.Home)
                },
                onSignIn = {
                    navController.navigate(Screen.Login)
                }
            )
        }
        composable<Screen.Login> {
            LoginScreen(
                onLogin = {
                    navController.navigate(Screen.Home)
                },
                onForgotPassword = {
                    navController.navigate(Screen.ForgotPassword)
                },
                onSignUp = {
                    navController.navigate(Screen.SignUp)
                },
                onContinueWithFacebook = {
                    navController.navigate(Screen.Home)
                },
                onContinueWithGoogle = {
                    navController.navigate(Screen.Home)
                }
            )
        }

        composable<Screen.ForgotPassword> {
            ForgotPasswordScreen(
                onBack = {
                    navController.navigate(Screen.Login)
                },
                onForgotPassword = {
                    navController.navigate(Screen.Login)
                }
            )
        }
        composable<Screen.SignUp> {
            SignUpScreen(
                onSignUp = {
                    navController.navigate(Screen.Home)
                },
                onLogin = {
                    navController.navigate(Screen.Login)
                },
                onContinueWithFacebook = {
                    navController.navigate(Screen.Home)
                },
                onContinueWithGoogle = {
                    navController.navigate(Screen.Home)
                },
            )
        }
        composable<Screen.Admin> {
            AdminScreen()
        }
        composable<Screen.Home> {
            HomeScreen()
        }
        composable<Screen.Discover> {
            DiscoverScreen()
        }
        composable<Screen.Restaurants> {
            RestaurantsScreen()
        }
        composable<Screen.Search> {
            SearchScreen()
        }
        composable<Screen.Favorite> {
            FavoriteScreen()
        }
        composable<Screen.Profile> {
            ProfileScreen(
                onLogout = {
                    navController.popBackStack()
                    navController.navigate(Screen.Welcome)
                },
                onCartClick = {
                    navController.navigate(Screen.Cart)
                }
            )
        }
        composable<Screen.ItemDetail> {
            ItemDetailScreen()
        }
        composable<Screen.Cart> {
            CartScreen()
        }
    }
}