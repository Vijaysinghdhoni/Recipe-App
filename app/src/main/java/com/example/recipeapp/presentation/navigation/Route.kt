package com.example.recipeapp.presentation.navigation

sealed class Route(val route: String) {

    object SignINScreen : Route("SIGN_IN_SCREEN")

    object SignUpScreen : Route("SIGN_UP_SCREEN")

    object IntroScreen : Route("INTRO_SCREEN")

    object ForgotPassWordScreen : Route("FORGOT_PASSWORD_SCREEN")

    object AppsStartNavigation : Route("APPS_STARTS_NAVIGATION_GRAPH")

    object RecipeStartNavigation : Route("RECIPE_START_NAVIGATION_GRAPH")

    object RecipeHome : Route("Home")

    object LoadingScreen : Route("LOADING_SCREEN")

    object RecipeBookMark : Route("BookMark")

    object RecipeSetting : Route("Setting")

    object RecipeNavigator : Route("RecipeNavigator")

    object CategoryMealsScreen : Route("categoryMealScreen")

    object MealDetailScreen : Route("mealDetailScreen")

}