package com.example.recipeapp.presentation.recipe.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.usecases.MealsUseCases
import com.example.recipeapp.presentation.recipe.categoryMeals.CategoryMealsEvent
import com.example.recipeapp.presentation.recipe.categoryMeals.CategoryMealsState
import com.example.recipeapp.presentation.recipe.home.HomeScreenEvent
import com.example.recipeapp.presentation.recipe.home.HomeScreenState
import com.example.recipeapp.presentation.recipe.mealdetail.MealDetailEvent
import com.example.recipeapp.presentation.recipe.mealdetail.MealDetailState
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val mealsUseCases: MealsUseCases) : ViewModel() {

    var homeState by mutableStateOf(HomeScreenState())
        private set

    var categoryMealsState by mutableStateOf(CategoryMealsState())
        private set

    var mealDetailState by mutableStateOf(MealDetailState())
        private set

    init {
        getAllCategories()
        getRandomMeal()
    }


    fun onHomeEvent(homeScreenEvent: HomeScreenEvent) {

        when (homeScreenEvent) {

            is HomeScreenEvent.onRandomMealClick -> {
                getRandomMeal()
            }

        }

    }


    fun onCategoryMealsEvent(categoryMealsEvent: CategoryMealsEvent) {

        when (categoryMealsEvent) {

            is CategoryMealsEvent.GetCaetgroyMeals -> {

                getCategoryMeals(categoryMealsEvent.categoryName)

            }

        }

    }

    fun onMealDetailEvent(mealDetailEvent: MealDetailEvent) {

        when (mealDetailEvent) {

            is MealDetailEvent.GetMealDetailsByID -> {

                getMealDetail(mealDetailEvent.mealId)

            }

        }

    }

    private fun getAllCategories() {
        mealsUseCases.getAllCategoriesUsecase.execute().onEach { resource ->

            when (resource) {
                is Resource.Loading -> {
                    homeState = homeState.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    homeState = homeState.copy(
                        isLoading = false,
                        categoryList = resource.data
                    )
                }

                is Resource.Error -> {
                    homeState = homeState.copy(
                        isLoading = false,
                        error = resource.message
                    )

                }

            }
        }.launchIn(viewModelScope)
    }

    private fun getRandomMeal() {
        mealsUseCases.getRandomMealUseCase.execute().onEach { resource ->

            when (resource) {

                is Resource.Loading -> {
                    homeState = homeState.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    homeState = homeState.copy(
                        isLoading = false,
                        randomMeal = resource.data
                    )
                }

                is Resource.Error -> {
                    homeState = homeState.copy(
                        isLoading = false,
                        error = resource.message
                    )

                }

            }

        }.launchIn(viewModelScope)
    }


    private fun getCategoryMeals(categoryName: String) {
        mealsUseCases.getCategoryMealsUseCase.execute(categoryName).onEach { resource ->

            when (resource) {

                is Resource.Loading -> {

                    categoryMealsState = categoryMealsState.copy(
                        isLoading = true
                    )

                }

                is Resource.Success -> {
                    categoryMealsState = categoryMealsState.copy(
                        isLoading = false,
                        categoryMeals = resource.data
                    )

                }


                is Resource.Error -> {
                    categoryMealsState = categoryMealsState.copy(
                        isLoading = false,
                        error = resource.message
                    )

                }


            }


        }.launchIn(viewModelScope)

    }

    private fun getMealDetail(mealID: String) {

        mealsUseCases.getMealDetailByIdUseCase.execute(mealID).onEach { resource ->

            when (resource) {

                is Resource.Loading -> {

                    mealDetailState = mealDetailState.copy(
                        isLoading = true
                    )

                }

                is Resource.Success -> {

                    mealDetailState = mealDetailState.copy(
                        isLoading = false,
                        meal = resource.data
                    )

                }

                is Resource.Error -> {

                    mealDetailState = mealDetailState.copy(
                        isLoading = false,
                        error = resource.message
                    )


                }

            }

        }.launchIn(viewModelScope)

    }

}