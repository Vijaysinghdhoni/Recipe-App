package com.example.recipeapp.domain.usecases

data class AuthUseCases(
    val loginUserWithEmailUseCase: LoginUserWithEmailUseCase,
    val registerUserWithEmailUseCase: RegisterUserWithEmailUseCase,
    val logoutUserUseCase: LogoutUserUseCase,
    val resetPasswordWithEmailUseCase: ResetPasswordWithEmailUseCase
)
