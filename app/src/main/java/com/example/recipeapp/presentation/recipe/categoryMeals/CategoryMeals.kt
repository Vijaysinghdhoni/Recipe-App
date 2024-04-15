package com.example.recipeapp.presentation.recipe.categoryMeals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.R
import com.example.recipeapp.presentation.recipe.categoryMeals.components.MealsItem
import com.example.recipeapp.presentation.recipe.viewmodel.MealsViewModel

@Composable
fun CategoryMeals(
    modifier: Modifier = Modifier,
    categoryName: String?,
    viewModel: MealsViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {

    val categoryMealsState = viewModel.categoryMealsState

    LaunchedEffect(key1 = true) {
        categoryName?.let {
            viewModel.onCategoryMealsEvent(CategoryMealsEvent.GetCaetgroyMeals(categoryName))
        }
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.card_background))
    ) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.card_background))
                .padding(13.dp)
        ) {

            Text(
                text = categoryName ?: "",
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = modifier.height(7.dp))
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    top = 13.dp,
                    bottom = 23.dp,
                    start = 13.dp,
                    end = 13.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalItemSpacing = 8.dp
            ) {
                categoryMealsState.categoryMeals?.let { categoryMealsList ->
                    items(categoryMealsList) {
                        MealsItem(categoryMeal = it) { mealId ->
                            onItemClick(mealId)
                        }
                        Spacer(modifier = modifier.size(width = 5.dp, height = 8.dp))
                    }
                }
            }
        }

        if (categoryMealsState.isLoading) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center),
                color = Color.Black,
                strokeWidth = 4.dp,
                trackColor = Color.Gray
            )
        }

        if (!categoryMealsState.error.isNullOrBlank()) {
            Text(
                text = categoryMealsState.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)

            )
        }

//        if (categoryMealsState.categoryMeals?.isEmpty() == true) {
//            Column(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .align(Alignment.Center),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//
//                Icon(
//                    imageVector = Icons.Filled.ThumbUp,
//                    contentDescription = null,
//                    tint = Color.Red
//                )
//                Spacer(modifier = modifier.height(8.dp))
//                Text(
//                    text = "No meals in this category!",
//                    color = MaterialTheme.colorScheme.error,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center,
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 19.dp)
//                )
//
//            }
//        }

    }


}