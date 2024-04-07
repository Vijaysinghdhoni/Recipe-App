package com.example.recipeapp.presentation.recipe.mealdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.Meal
import com.example.recipeapp.presentation.recipe.viewmodel.MealsViewModel

@Composable
fun MealDetail(
    modifier: Modifier = Modifier,
    mealId: String?,
    viewModel: MealsViewModel = hiltViewModel(),
) {

    val mealDetailState = viewModel.mealDetailState
    val meal = mealDetailState.meal

    LaunchedEffect(key1 = true) {
        mealId?.let {
            viewModel.onMealDetailEvent(MealDetailEvent.GetMealDetailsByID(mealId))
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
        ) {

            AsyncImage(
                model = meal?.mealThumbImg,
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder_img),
                error = painterResource(id = R.drawable.placeholder_img),
            )

            Spacer(modifier = modifier.height(13.dp))

            Text(
                text = meal?.mealName ?: "",
                modifier = modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                maxLines = 2,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = modifier.height(8.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = meal?.mealArea ?: "",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

                Spacer(modifier = modifier.width(3.dp))
                Text(
                    text = "|", color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = modifier.width(3.dp))
                Text(
                    text = meal?.mealCategory ?: "",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

            }

        }

        if (mealDetailState.isLoading) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center),
                color = Color.Black,
                strokeWidth = 4.dp,
                trackColor = Color.Gray
            )
        }

    }

}