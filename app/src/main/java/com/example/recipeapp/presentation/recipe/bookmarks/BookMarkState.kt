package com.example.recipeapp.presentation.recipe.bookmarks

import com.example.recipeapp.domain.model.BookMarkMeal

data class BookMarkState(
    val bookMarkMeals: List<BookMarkMeal> = emptyList(),
)