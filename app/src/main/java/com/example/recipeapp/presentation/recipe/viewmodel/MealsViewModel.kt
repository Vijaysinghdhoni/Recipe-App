package com.example.recipeapp.presentation.recipe.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.BookMarkMeal
import com.example.recipeapp.domain.model.Ingredients
import com.example.recipeapp.domain.model.Meal
import com.example.recipeapp.domain.usecases.MealsUseCases
import com.example.recipeapp.domain.usecases.UserLocalUseCase
import com.example.recipeapp.presentation.recipe.bookmarks.BookMarkEvent
import com.example.recipeapp.presentation.recipe.bookmarks.BookMarkState
import com.example.recipeapp.presentation.recipe.bookmarks.BookMarkUiEvent
import com.example.recipeapp.presentation.recipe.categoryMeals.CategoryMealsEvent
import com.example.recipeapp.presentation.recipe.categoryMeals.CategoryMealsState
import com.example.recipeapp.presentation.recipe.home.HomeScreenEvent
import com.example.recipeapp.presentation.recipe.home.HomeScreenState
import com.example.recipeapp.presentation.recipe.mealdetail.MealDetailEvent
import com.example.recipeapp.presentation.recipe.mealdetail.MealDetailState
import com.example.recipeapp.presentation.recipe.mealdetail.MealDetailUiEvent
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealsUseCases: MealsUseCases
) : ViewModel() {

    var homeState by mutableStateOf(HomeScreenState())
        private set

    var categoryMealsState by mutableStateOf(CategoryMealsState())
        private set

    var mealDetailState by mutableStateOf(MealDetailState())
        private set

    var bookMarkState by mutableStateOf(BookMarkState())
        private set

    private val bookMarkUiSharedFlow = MutableSharedFlow<BookMarkUiEvent>()
    val bookMarkEvent = bookMarkUiSharedFlow.asSharedFlow()

    private val mealDetailEventFlow = MutableSharedFlow<MealDetailUiEvent>()
    val mealDetailUIEvent = mealDetailEventFlow.asSharedFlow()

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

            is MealDetailEvent.InsertBookMarkMeal -> {

                bookMarkMeal(
                    mealId = mealDetailEvent.mealId,
                    mealName = mealDetailEvent.mealName,
                    mealImg = mealDetailEvent.mealImg,
                    isMealFav = mealDetailEvent.isMealFav,
                )

            }


        }

    }


    fun onBookMarkEvent(bookMarkEvent: BookMarkEvent) {
        when (bookMarkEvent) {
            is BookMarkEvent.GetAllBookMarkMeals -> {
                getAllBookMarkMeals()
            }

            is BookMarkEvent.OnDeleteBookMarkMealClicked -> {
                deleteBookMarkMeal(bookMarkEvent.bookMarkMeal)
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
                    val ingredients = extractIngredientsFromDetails(resource.data)
                    mealDetailState = mealDetailState.copy(
                        isLoading = false,
                        meal = resource.data,
                        ingredients = ingredients
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

    private fun getAllBookMarkMeals() {
        mealsUseCases.getAllBookMarkMealUseCase.execute().onEach {
            bookMarkState = bookMarkState.copy(
                bookMarkMeals = it
            )
        }.launchIn(viewModelScope)
    }

    private fun deleteBookMarkMeal(bookMarkMeal: BookMarkMeal) {
        viewModelScope.launch {
            mealsUseCases.deleteBookMarkMealUseCase.execute(bookMarkMeal)
            bookMarkUiSharedFlow.emit(
                BookMarkUiEvent.ShowMessage("Meal deleted from favourite")
            )
        }
    }

    private fun bookMarkMeal(
        mealId: String,
        mealName: String,
        mealImg: String,
        isMealFav: Boolean
    ) {
        viewModelScope.launch {
            mealDetailState = mealDetailState.copy(
                isMealFav = true
            )
            mealsUseCases.insertBookMarkMealUseCase.execute(
                mealId = mealId,
                mealName = mealName,
                mealImg = mealImg,
                isMealFav = isMealFav
            )
            mealDetailEventFlow.emit(
                MealDetailUiEvent.ShowMessage("Meal is marked as favourite")
            )
        }
    }

    private fun extractIngredientsFromDetails(mealDetail: Meal?): List<Ingredients> {
        val ingredients = mutableListOf<Ingredients>()
        if (mealDetail?.ingredient1?.isNotEmpty() == true && mealDetail.strMeasure1?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient1, mealDetail.strMeasure1)
        )
        if (mealDetail?.ingredient2?.isNotEmpty() == true && mealDetail.strMeasure2?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient2, mealDetail.strMeasure2)
        )
        if (mealDetail?.ingredient3?.isNotEmpty() == true && mealDetail.strMeasure3?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient3, mealDetail.strMeasure3)
        )
        if (mealDetail?.ingredient4?.isNotEmpty() == true && mealDetail.strMeasure4?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient4, mealDetail.strMeasure4)
        )
        if (mealDetail?.ingredient5?.isNotEmpty() == true && mealDetail.strMeasure5?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient5, mealDetail.strMeasure5)
        )
        if (mealDetail?.ingredient6?.isNotEmpty() == true && mealDetail.strMeasure6?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient6, mealDetail.strMeasure6)
        )
        if (mealDetail?.ingredient7?.isNotEmpty() == true && mealDetail.strMeasure7?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient7, mealDetail.strMeasure7)
        )
        if (mealDetail?.ingredient8?.isNotEmpty() == true && mealDetail.strMeasure8?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient8, mealDetail.strMeasure8)
        )
        if (mealDetail?.ingredient9?.isNotEmpty() == true && mealDetail.strMeasure9?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient9, mealDetail.strMeasure9)
        )
        if (mealDetail?.ingredient10?.isNotEmpty() == true && mealDetail.strMeasure10?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient10, mealDetail.strMeasure10)
        )
        if (mealDetail?.ingredient11?.isNotEmpty() == true && mealDetail.strMeasure11?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient11, mealDetail.strMeasure11)
        )
        if (mealDetail?.ingredient12?.isNotEmpty() == true && mealDetail.strMeasure12?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient12, mealDetail.strMeasure12)
        )
        if (mealDetail?.ingredient13?.isNotEmpty() == true && mealDetail.strMeasure13?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient13, mealDetail.strMeasure13)
        )
        if (mealDetail?.ingredient14?.isNotEmpty() == true && mealDetail.strMeasure14?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient14, mealDetail.strMeasure14)
        )
        if (mealDetail?.ingredient15?.isNotEmpty() == true && mealDetail.strMeasure15?.isNotEmpty() == true) ingredients.add(
            Ingredients(mealDetail.ingredient15, mealDetail.strMeasure15)
        )
        if (mealDetail?.ingredient16?.toString()
                ?.isNotEmpty() == true && mealDetail.strMeasure16?.toString()?.isNotEmpty() == true
        ) ingredients.add(
            Ingredients(
                mealDetail.ingredient16.toString(),
                mealDetail.strMeasure16.toString()
            )
        )
        if (mealDetail?.ingredient17?.toString()
                ?.isNotEmpty() == true && mealDetail.strMeasure17?.toString()?.isNotEmpty() == true
        ) ingredients.add(
            Ingredients(
                mealDetail.ingredient17.toString(),
                mealDetail.strMeasure17.toString()
            )
        )
        if (mealDetail?.ingredient18?.toString()
                ?.isNotEmpty() == true && mealDetail.strMeasure18?.toString()?.isNotEmpty() == true
        ) ingredients.add(
            Ingredients(
                mealDetail.ingredient18.toString(),
                mealDetail.strMeasure18.toString()
            )
        )
        if (mealDetail?.ingredient19?.toString()
                ?.isNotEmpty() == true && mealDetail.strMeasure19?.toString()?.isNotEmpty() == true
        ) ingredients.add(
            Ingredients(
                mealDetail.ingredient19.toString(),
                mealDetail.strMeasure19.toString()
            )
        )
        if (mealDetail?.ingredient20?.toString()
                ?.isNotEmpty() == true && mealDetail.strMeasure20?.toString()?.isNotEmpty() == true
        ) ingredients.add(
            Ingredients(
                mealDetail.ingredient20.toString(),
                mealDetail.strMeasure20.toString()
            )
        )

        return ingredients.toList()
    }

}