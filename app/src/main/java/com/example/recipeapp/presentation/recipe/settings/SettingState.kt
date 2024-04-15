package com.example.recipeapp.presentation.recipe.settings

data class SettingState(
    val userName: String? = "",
    val userEmail: String? = "",
    val isLoading: Boolean = false,
    val error : String? = null,
    val showAlertDialog : Boolean = false
)
