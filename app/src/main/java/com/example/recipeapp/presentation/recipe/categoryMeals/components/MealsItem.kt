package com.example.recipeapp.presentation.recipe.categoryMeals.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.CategoryMeal

@Composable
fun MealsItem(
    modifier: Modifier = Modifier,
    categoryMeal: CategoryMeal,
    onClick: (String) -> Unit
) {

    Card(
        modifier = modifier
            .width(120.dp)
            .height(150.dp)
            .clickable {
                onClick(categoryMeal.mealID)
            }
    ) {

        Box(modifier = modifier.fillMaxWidth()) {

            AsyncImage(
                model = categoryMeal.mealImg,
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.placeholder_img),
                error = painterResource(id = R.drawable.placeholder_img),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color.White)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = categoryMeal.mealName,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        fontSize = 13.sp
                    ),
                )


            }


        }

    }

}