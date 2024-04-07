package com.example.recipeapp.presentation.recipe.mealdetail

 sealed class MealDetailEvent {

     data class GetMealDetailsByID(val mealId : String) : MealDetailEvent()

}