package com.example.recipeapp.domain.usecases

data class UserLocalUseCase(
    val insertUserUseCase: InsertUserUseCase,
    val getUserByIdUseCase: GetUserByIdUseCase
)