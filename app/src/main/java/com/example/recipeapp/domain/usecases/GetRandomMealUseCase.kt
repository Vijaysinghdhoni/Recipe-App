package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.repo.MealsRepository
import javax.inject.Inject

class GetRandomMealUseCase @Inject constructor(private val mealsRepository: MealsRepository) {

    fun execute() = mealsRepository.getRandomMeal()

}