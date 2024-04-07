package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.repo.MealsRepository
import javax.inject.Inject

class GetCategoryMealsUseCase @Inject constructor(private val mealsRepository: MealsRepository) {
    fun execute(categoryName: String) = mealsRepository.getCategoryMeals(categoryName)
}