package com.example.recipeapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Fav_Meals")
data class BookMarkMeal(

    @PrimaryKey(autoGenerate = false)
    val mealID: String,
    @ColumnInfo(name = "meal_name")
    val mealName: String,
    @ColumnInfo(name = "meal_image_link")
    val mealImg: String,
    @ColumnInfo(name = "is_meal_fav")
    val isMealFav: Boolean = false,
    @ColumnInfo(name = "meal_usr_id")
    val userMealID: String
)
