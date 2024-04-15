package com.example.recipeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeapp.domain.model.BookMarkMeal
import com.example.recipeapp.domain.model.User

@Database(entities = [User::class, BookMarkMeal::class], version = 1)
abstract class MealLocalDb : RoomDatabase() {

    abstract val userDao: UserDao

    abstract val bookMarkMealDao: BookMarkMealDao
}