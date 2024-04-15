package com.example.recipeapp.presentation.recipe.settings

sealed class SettingLocalEvent {

    object LogoutClicked : SettingLocalEvent()

    object GetUser : SettingLocalEvent()

    data class ShowAlertBox(val showAlertBox: Boolean) : SettingLocalEvent()

}