package com.example.recipeapp.presentation.recipe.mealdetail

import com.example.recipeapp.domain.model.BookMarkMeal

sealed class MealDetailEvent {

    data class GetMealDetailsByID(val mealId: String) : MealDetailEvent()

    data class InsertBookMarkMeal(
        val mealId: String,
        val mealName: String,
        val mealImg: String,
        val isMealFav: Boolean
    ) : MealDetailEvent()

}