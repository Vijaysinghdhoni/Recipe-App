package com.example.recipeapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService {

    @GET("lookup.php")
    suspend fun getMealById(
        @Query("i") mealId: String
    ): Response<MealDtoList>

    @GET("categories.php")
    suspend fun getAllCategories(): Response<CategoryDtoList>

    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealDtoList>

    @GET("filter.php")
    suspend fun getCategoryMeals(
        @Query("c") categoryName: String
    ): Response<CatgoryMealsDtoList>

}