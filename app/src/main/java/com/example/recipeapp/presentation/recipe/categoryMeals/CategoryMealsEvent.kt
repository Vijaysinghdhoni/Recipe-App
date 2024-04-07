package com.example.recipeapp.presentation.recipe.categoryMeals

sealed class CategoryMealsEvent {

    data class GetCaetgroyMeals(val categoryName : String) : CategoryMealsEvent()

}