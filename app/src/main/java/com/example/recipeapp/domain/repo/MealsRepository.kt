package com.example.recipeapp.domain.repo

import com.example.recipeapp.data.remote.MealDtoList
import com.example.recipeapp.domain.model.Category
import com.example.recipeapp.domain.model.CategoryMeal
import com.example.recipeapp.domain.model.Meal
import com.example.recipeapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    fun getMealById(mealId: String): Flow<Resource<Meal>>

    fun getAllCategorys(): Flow<Resource<List<Category>>>

    fun getRandomMeal(): Flow<Resource<Meal>>

    fun getCategoryMeals(categoryName: String): Flow<Resource<List<CategoryMeal>>>

}