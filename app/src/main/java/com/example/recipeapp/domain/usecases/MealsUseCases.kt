package com.example.recipeapp.domain.usecases

data class MealsUseCases(
    val getAllCategoriesUsecase: GetAllCategoriesUsecase,
    val getRandomMealUseCase: GetRandomMealUseCase,
    val getCategoryMealsUseCase: GetCategoryMealsUseCase,
    val getMealDetailByIdUseCase: GetMealDetailByIdUseCase
)
