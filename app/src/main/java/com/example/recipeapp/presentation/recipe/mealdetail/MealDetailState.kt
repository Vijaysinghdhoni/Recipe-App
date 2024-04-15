package com.example.recipeapp.presentation.recipe.mealdetail

import com.example.recipeapp.domain.model.Ingredients
import com.example.recipeapp.domain.model.Meal

data class MealDetailState(
    val isLoading: Boolean = true,
    val meal: Meal? = null,
    val ingredients: List<Ingredients> = emptyList(),
    val error: String? = null,
    val isMealFav : Boolean = false
)
