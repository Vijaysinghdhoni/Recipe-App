package com.example.recipeapp.data.remote


import com.example.recipeapp.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("idCategory")
    val idCategory: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String
)

fun CategoryDto.toCategory(): Category {

    return Category(
        categoryID = idCategory,
        categoryName = strCategory,
        categoryImg = strCategoryThumb
    )

}