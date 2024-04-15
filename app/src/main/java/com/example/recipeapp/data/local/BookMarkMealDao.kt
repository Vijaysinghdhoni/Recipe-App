package com.example.recipeapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.domain.model.BookMarkMeal
import kotlinx.coroutines.flow.Flow

@Dao
interface BookMarkMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookMarkMeal(bookMarkMeal: BookMarkMeal)

    @Delete
    suspend fun deleteBookMarkMeal(bookMarkMeal: BookMarkMeal)

    @Query("SELECT * FROM User_Fav_Meals WHERE meal_usr_id = :userID")
    fun getAllBookMarkMeals(userID : String): Flow<List<BookMarkMeal>>
}