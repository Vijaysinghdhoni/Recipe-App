package com.example.recipeapp.presentation.recipe.bookmarks

import com.example.recipeapp.domain.model.BookMarkMeal

sealed class BookMarkEvent {

    object GetAllBookMarkMeals : BookMarkEvent()

    data class OnDeleteBookMarkMealClicked(val bookMarkMeal: BookMarkMeal) : BookMarkEvent()

}