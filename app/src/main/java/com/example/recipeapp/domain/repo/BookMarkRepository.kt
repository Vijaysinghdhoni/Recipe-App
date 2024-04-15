package com.example.recipeapp.domain.repo

import com.example.recipeapp.domain.model.BookMarkMeal
import kotlinx.coroutines.flow.Flow

interface BookMarkRepository {

    suspend fun insertBookMarkMeal(
         mealId: String,
         mealName: String,
         mealImg: String,
         isMealFav: Boolean
    )

    suspend fun deleteBookMarkMeal(bookMarkMeal: BookMarkMeal)

    fun getAllBookMarkMeal(): Flow<List<BookMarkMeal>>

}