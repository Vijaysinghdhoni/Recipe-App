package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.model.BookMarkMeal
import com.example.recipeapp.domain.repo.BookMarkRepository
import javax.inject.Inject

class DeleteBookMarkMealUseCase @Inject constructor(
    private val bookMarkRepository: BookMarkRepository
) {

    suspend fun execute(bookMarkMeal: BookMarkMeal) =
        bookMarkRepository.deleteBookMarkMeal(bookMarkMeal)

}