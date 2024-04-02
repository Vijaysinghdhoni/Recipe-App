package com.example.recipeapp.presentation.recipe.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.R
import com.example.recipeapp.presentation.recipe.home.components.CategoryItem
import com.example.recipeapp.presentation.recipe.home.components.RandomMeal
import com.example.recipeapp.presentation.recipe.viewmodel.MealsViewModel

@Composable
fun Home(
    modifier: Modifier = Modifier,
    viewModel: MealsViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val homeState = viewModel.homeState

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.card_background))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.card_background))
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(top = 13.dp, bottom = 13.dp, start = 12.dp, end = 12.dp)
        ) {

            RandomMeal(
                meal = homeState.randomMeal,
            ) {
                viewModel.onHomeEvent(HomeScreenEvent.onRandomMealClick)
            }
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = "Categories",
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = modifier.height(3.dp))
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                contentPadding = PaddingValues(
                    top = 13.dp,
                    bottom = 23.dp,
                    start = 13.dp,
                    end = 13.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalItemSpacing = 8.dp
            ) {
                homeState.categoryList?.let { categoryList ->
                    items(categoryList) {
                        CategoryItem(category = it)
                        Spacer(modifier = modifier.size(width = 5.dp, height = 8.dp))
                    }
                }
            }


        }

        if (homeState.isLoading) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center),
                color = Color.Black,
                strokeWidth = 4.dp,
                trackColor = Color.Gray
            )
        }


    }


    //impliment pagination using pagination 3
    //first display a random meal with full screen width size and then below that display categorys
    //when user opens the app the random meal should not display but when user clicks on random meal button the one meal should display with max width as screen
    // or you can display a recomended meal and when user clicks on random meal then the recomended meal should change with the random meal


}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Test() {


}