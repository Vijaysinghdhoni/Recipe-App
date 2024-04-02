package com.example.recipeapp.data.repository

import com.example.recipeapp.data.remote.MealsApiService
import com.example.recipeapp.data.remote.toCategory
import com.example.recipeapp.data.remote.toMeal
import com.example.recipeapp.domain.model.Category
import com.example.recipeapp.domain.model.Meal
import com.example.recipeapp.domain.repo.MealsRepository
import com.example.recipeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(private val mealsApiService: MealsApiService) :
    MealsRepository {
    override suspend fun getMealById(mealId: String): Resource<Meal> {
        TODO("Not yet implemented")
    }

    override fun getAllCategorys(): Flow<Resource<List<Category>>> = flow {

        try {
            emit(Resource.Loading())
            val response = mealsApiService.getAllCategories()
            if (response.isSuccessful) {
                val categoryResponse = response.body()
                categoryResponse?.let {
                    val categories = it.categories.map { categoryDto ->
                        categoryDto.toCategory()
                    }
                    emit(Resource.Success(categories))
                }
            } else {
                val error = response.message()
                emit(Resource.Error(error))
            }
        } catch (ex: HttpException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        } catch (ex: IOException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        }
    }

    override fun getRandomMeal(): Flow<Resource<Meal>> = flow {
        try {
            emit(Resource.Loading())
            val response = mealsApiService.getRandomMeal()
            if (response.isSuccessful) {
                val mealDtoList = response.body()
                mealDtoList?.let {
                    val meal = it.meals.map { mealDto ->
                        mealDto.toMeal()
                    }
                    emit(Resource.Success(meal[0]))
                }
            } else {
                emit(Resource.Error(response.message()))
            }

        } catch (ex: HttpException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        } catch (ex: IOException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        }
    }

}