package com.example.recipeapp.domain.usecases

import com.example.recipeapp.domain.model.BookMarkMeal
import com.example.recipeapp.domain.repo.BookMarkRepository
import javax.inject.Inject

class InsertBookMarkMealUseCase @Inject constructor(
    private val bookMarkRepository: BookMarkRepository
) {

    suspend fun execute(
        mealId: String,
        mealName: String,
        mealImg: String,
        isMealFav: Boolean
    ) =
        bookMarkRepository.insertBookMarkMeal(
            mealId = mealId,
            mealName = mealName,
            mealImg = mealImg,
            isMealFav = isMealFav
        )

}