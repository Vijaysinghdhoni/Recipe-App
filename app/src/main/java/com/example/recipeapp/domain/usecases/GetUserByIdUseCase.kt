package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.repo.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(userId: String) = userRepository.getUserById(userId)

}