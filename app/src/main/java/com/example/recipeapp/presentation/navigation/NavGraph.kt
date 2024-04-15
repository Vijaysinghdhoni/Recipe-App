package com.example.recipeapp.presentation.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.recipeapp.presentation.auth.forgot_password.ForgotPassword
import com.example.recipeapp.presentation.auth.intro.LoadingScreen
import com.example.recipeapp.presentation.auth.intro.LoginOptionsScreen
import com.example.recipeapp.presentation.auth.sigin.SignInScreen
import com.example.recipeapp.presentation.auth.signup.SignUpScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {

    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Route.AppsStartNavigation.route
    )
    {

        navigation(
            route = Route.AppsStartNavigation.route,
            startDestination = Route.LoadingScreen.route
        ) {

            composable(
                route = Route.LoadingScreen.route
            ) {
                LoadingScreen(
                    navController = navController
                )
            }

            composable(
                route = Route.IntroScreen.route
            ) {
                LoginOptionsScreen(navController = navController)
            }

            composable(
                route = Route.SignINScreen.route
            ) {
                SignInScreen(navController = navController)
            }

            composable(route = Route.SignUpScreen.route) {
                SignUpScreen(navController = navController)
            }

            composable(route = Route.ForgotPassWordScreen.route) {
                ForgotPassword()
            }

        }

        navigation(
            route = Route.RecipeStartNavigation.route,
            startDestination = Route.RecipeNavigator.route
        ) {

            composable(
                route = Route.RecipeNavigator.route
            ) {
                RecipeNavigator {
                    navController.navigate(Route.AppsStartNavigation.route) {
                        popUpTo(0)
                    }
                    Toast.makeText(context, "LogoutSucces Success!", Toast.LENGTH_LONG).show()
                }
            }

        }

    }

}