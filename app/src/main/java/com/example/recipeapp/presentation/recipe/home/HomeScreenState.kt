package com.example.recipeapp.presentation.recipe.home

import com.example.recipeapp.domain.model.Category
import com.example.recipeapp.domain.model.Meal

data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val categoryList: List<Category>? = emptyList(),
    val randomMeal: Meal? = null
)
