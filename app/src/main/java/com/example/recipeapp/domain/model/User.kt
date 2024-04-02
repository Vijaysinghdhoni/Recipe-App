package com.example.recipeapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER_TABLE")
data class User(
    @PrimaryKey
    val userId : String,
    val userName : String,
    val userEmail : String
)
