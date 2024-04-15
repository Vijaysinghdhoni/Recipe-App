package com.example.recipeapp.presentation.recipe.settings

sealed class SettingEvent {

    data class NavigationEvent(val route: String) : SettingEvent()

}
