package com.example.recipeapp.data.repository


import android.database.sqlite.SQLiteException
import com.example.recipeapp.data.local.BookMarkMealDao
import com.example.recipeapp.domain.model.BookMarkMeal
import com.example.recipeapp.domain.repo.BookMarkRepository
import com.example.recipeapp.util.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class BookMarkRepoImpl @Inject constructor(
    private val bookMarkMealDao: BookMarkMealDao,
    private val firebaseAuth: FirebaseAuth
) : BookMarkRepository {

    override suspend fun insertBookMarkMeal(
        mealId: String,
        mealName: String,
        mealImg: String,
        isMealFav: Boolean
    ) {
        val bookMarkMeal = BookMarkMeal(
            mealID = mealId,
            mealName = mealName,
            mealImg = mealImg,
            isMealFav = isMealFav,
            userMealID = firebaseAuth.uid.toString()
        )
        bookMarkMealDao.insertBookMarkMeal(bookMarkMeal)
    }

    override suspend fun deleteBookMarkMeal(bookMarkMeal: BookMarkMeal) {
        bookMarkMealDao.deleteBookMarkMeal(bookMarkMeal)
    }

    override fun getAllBookMarkMeal(): Flow<List<BookMarkMeal>> {
        return bookMarkMealDao.getAllBookMarkMeals(
            firebaseAuth.uid.toString()
        )
    }


}