package com.example.recipeapp.data.remote


import com.google.gson.annotations.SerializedName

data class CatgoryMealsDtoList(
    @SerializedName("meals")
    val categoryMealDtos: List<CategoryMealDto>
)