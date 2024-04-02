package com.example.recipeapp.domain.usecases

data class AuthUseCases(
    val getUserByIdUseCase: GetUserByIdUseCase,
    val insertUserUseCase: InsertUserUseCase,
    val loginUserWithEmailUseCase: LoginUserWithEmailUseCase,
    val registerUserWithEmailUseCase: RegisterUserWithEmailUseCase
)
