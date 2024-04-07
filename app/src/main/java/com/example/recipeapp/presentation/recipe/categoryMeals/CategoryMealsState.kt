package com.example.recipeapp.presentation.recipe.categoryMeals

import com.example.recipeapp.domain.model.CategoryMeal

data class CategoryMealsState(
    val isLoading : Boolean = false,
    val categoryMeals : List<CategoryMeal>? = emptyList(),
    val error : String ?  = null
)
