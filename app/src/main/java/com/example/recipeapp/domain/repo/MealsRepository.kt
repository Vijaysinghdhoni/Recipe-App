package com.example.recipeapp.domain.repo

import com.example.recipeapp.data.remote.MealDtoList
import com.example.recipeapp.domain.model.Category
import com.example.recipeapp.domain.model.Meal
import com.example.recipeapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    suspend fun getMealById(mealId: String): Resource<Meal>

    fun getAllCategorys(): Flow<Resource<List<Category>>>

    fun getRandomMeal(): Flow<Resource<Meal>>

}