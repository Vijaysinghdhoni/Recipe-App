package com.example.recipeapp.presentation.auth.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.presentation.auth.viewmodel.AuthenticationViewModel
import com.example.recipeapp.presentation.navigation.Route
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthenticationViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {

        viewModel.isUserLogin.collectLatest {

            if (it != null && it == true) {
                navController.navigate(Route.RecipeStartNavigation.route) {
                    popUpTo(Route.LoadingScreen.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(Route.IntroScreen.route) {
                    popUpTo(Route.LoadingScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircularProgressIndicator(
            color = Color.Black,
            strokeWidth = 4.dp,
            trackColor = Color.Gray
        )

    }


}