package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.repo.MealsRepository
import javax.inject.Inject

class GetMealDetailByIdUseCase @Inject constructor(private val mealsRepository: MealsRepository) {

    fun execute(mealID: String) = mealsRepository.getMealById(mealID)

}