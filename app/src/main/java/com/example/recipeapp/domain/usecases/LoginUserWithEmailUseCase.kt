package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.repo.AuthenticationRepository
import javax.inject.Inject

class LoginUserWithEmailUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {

    suspend fun execute(userEmail: String, password: String) =
        authenticationRepository.loginUserWithEmail(userEmail, password)

}