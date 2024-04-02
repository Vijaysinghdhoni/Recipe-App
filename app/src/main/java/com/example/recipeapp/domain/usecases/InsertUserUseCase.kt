package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.model.User
import com.example.recipeapp.domain.repo.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(user: User) = userRepository.insertUser(user)

}