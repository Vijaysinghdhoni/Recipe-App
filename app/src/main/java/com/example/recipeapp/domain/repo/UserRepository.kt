package com.example.recipeapp.domain.repo

import com.example.recipeapp.domain.model.User
import com.example.recipeapp.util.Resource

interface UserRepository {

    suspend fun insertUser(user: User)

    suspend fun getUserById(): Resource<User>

}