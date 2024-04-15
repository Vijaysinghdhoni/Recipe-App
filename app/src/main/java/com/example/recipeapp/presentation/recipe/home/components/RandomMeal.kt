package com.example.recipeapp.presentation.recipe.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.Meal

@Composable
fun RandomMeal(
    modifier: Modifier = Modifier,
    meal: Meal?,
    onMealClick: (String?) -> Unit,
    onRandomClick: () -> Unit
) {


    Column(modifier = modifier.padding(2.dp)) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = modifier
                .fillMaxWidth()

        ) {

            Column(modifier = modifier.padding(8.dp)) {
                WelcomeText(
                    text = "what should i eat today?",
                    fontSize = 15,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = modifier.height(8.dp))

                WelcomeText(
                    text = "Deciding what to eat can be a chore, Let us help you out!",
                    fontSize = 12,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = modifier.height(9.dp))

                Button(onClick = {
                    onRandomClick()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(text = "Random Meal", fontSize = 12.sp)
                }
            }


        }

        Spacer(modifier = modifier.height(12.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = modifier
                .height(180.dp)
                .clickable {
                    onMealClick(meal?.mealId)
                }
        ) {

            Box(modifier = modifier.fillMaxSize()) {
                AsyncImage(
                    model = meal?.mealThumbImg ?: "",
                    contentDescription = null,
                    modifier = modifier.fillMaxSize(),
                    placeholder = painterResource(id = R.drawable.placeholder_img),
                    error = painterResource(id = R.drawable.placeholder_img),
                    contentScale = ContentScale.FillWidth
                )

                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(color = Color.White)
                ) {

                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = meal?.mealName ?: "",
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                fontSize = 17.sp
                            )
                        )

                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                        )

                    }


                }

            }


        }


    }


}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Preview() {
    // RandomMeal()
}