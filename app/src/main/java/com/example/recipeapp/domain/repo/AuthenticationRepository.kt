package com.example.recipeapp.domain.repo

import com.example.recipeapp.util.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {

    suspend fun loginUserWithEmail(email: String, password: String): Resource<FirebaseUser?>

    suspend fun reigisterUser(email: String, password: String): Resource<FirebaseUser?>

    suspend fun logoutUser()

    suspend fun resetPasswordWithEmail(email: String): Resource<String>

}

