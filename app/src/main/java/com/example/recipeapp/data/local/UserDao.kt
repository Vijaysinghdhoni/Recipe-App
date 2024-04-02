package com.example.recipeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipeapp.domain.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM USER_TABLE WHERE userId=:userId")
    suspend fun getUser(userId : String) : User


}