package com.example.recipeapp.presentation.recipe.mealdetail

import com.example.recipeapp.domain.model.Meal

data class MealDetailState(
    val isLoading: Boolean = true,
    val meal: Meal? = null,
    val error: String? = null
)
