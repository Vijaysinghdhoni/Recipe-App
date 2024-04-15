package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.repo.AuthenticationRepository
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend fun execute() = authenticationRepository.logoutUser()

}