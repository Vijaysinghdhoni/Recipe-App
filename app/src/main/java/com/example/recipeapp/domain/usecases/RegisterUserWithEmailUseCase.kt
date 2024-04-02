package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.repo.AuthenticationRepository
import javax.inject.Inject

class RegisterUserWithEmailUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {


    suspend fun execute(userEmail: String, password: String) =
        authenticationRepository.reigisterUser(userEmail, password)

}