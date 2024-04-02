package com.example.recipeapp.data.remote


import com.google.gson.annotations.SerializedName

data class MealDtoList(
    @SerializedName("meals")
    val meals: List<MealDto>
)