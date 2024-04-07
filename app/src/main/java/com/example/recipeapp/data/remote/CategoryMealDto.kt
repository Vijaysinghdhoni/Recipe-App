package com.example.recipeapp.data.remote


import com.example.recipeapp.domain.model.CategoryMeal
import com.google.gson.annotations.SerializedName

data class CategoryMealDto(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String
)

fun CategoryMealDto.toCategoryMeal(): CategoryMeal {
    return CategoryMeal(
        mealID = idMeal,
        mealName = strMeal,
        mealImg = strMealThumb
    )
}