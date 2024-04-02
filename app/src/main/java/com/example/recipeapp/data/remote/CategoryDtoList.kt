package com.example.recipeapp.data.remote


import com.google.gson.annotations.SerializedName

data class CategoryDtoList(
    @SerializedName("categories")
    val categories: List<CategoryDto>
)