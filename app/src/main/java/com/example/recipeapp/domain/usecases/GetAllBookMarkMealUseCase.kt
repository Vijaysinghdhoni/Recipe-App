package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.repo.BookMarkRepository
import javax.inject.Inject

class GetAllBookMarkMealUseCase @Inject constructor(
    private val bookMarkRepository: BookMarkRepository
) {

    fun execute() = bookMarkRepository.getAllBookMarkMeal()

}