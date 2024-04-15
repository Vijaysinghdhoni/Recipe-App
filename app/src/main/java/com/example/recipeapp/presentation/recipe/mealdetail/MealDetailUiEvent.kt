package com.example.recipeapp.presentation.recipe.mealdetail

sealed class MealDetailUiEvent {

    data class ShowMessage(val message: String) : MealDetailUiEvent()

}