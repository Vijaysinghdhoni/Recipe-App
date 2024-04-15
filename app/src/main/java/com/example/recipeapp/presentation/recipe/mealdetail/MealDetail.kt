package com.example.recipeapp.presentation.recipe.mealdetail

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.recipeapp.domain.model.BookMarkMeal
import com.example.recipeapp.presentation.recipe.mealdetail.components.IngredientsandMeasures
import com.example.recipeapp.presentation.recipe.viewmodel.MealsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MealDetail(
    modifier: Modifier = Modifier,
    mealId: String?,
    viewModel: MealsViewModel = hiltViewModel(),
) {

    val mealDetailState = viewModel.mealDetailState
    val meal = mealDetailState.meal
    val ingredients = mealDetailState.ingredients
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        mealId?.let {
            viewModel.onMealDetailEvent(MealDetailEvent.GetMealDetailsByID(mealId))
        }
        viewModel.mealDetailUIEvent.collectLatest { event ->
            when (event) {
                is MealDetailUiEvent.ShowMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
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
                .verticalScroll(scrollState)
                .background(color = colorResource(id = R.color.card_background))
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {

                AsyncImage(
                    model = meal?.mealThumbImg,
                    contentDescription = null,
                    modifier = modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder_img),
                    error = painterResource(id = R.drawable.placeholder_img),
                )

                SmallFloatingActionButton(
                    onClick = {
                        meal?.let { meal ->
                            meal.mealThumbImg?.let {
                                viewModel.onMealDetailEvent(
                                    MealDetailEvent.InsertBookMarkMeal(
                                        mealId = meal.mealId,
                                        mealName = meal.mealName,
                                        mealImg = meal.mealThumbImg,
                                        isMealFav = true
                                    )
                                )
                            }
                        }

                    },
                    modifier = modifier.align(Alignment.TopEnd),
                    shape = CircleShape,
                    containerColor = colorResource(id = R.color.transpernt_back)
                ) {
                    Icon(
                        if (mealDetailState.isMealFav) {
                            Icons.Outlined.Favorite
                        } else {
                            Icons.Outlined.FavoriteBorder
                        }, "Small floating action button.",
                        tint = Color.White
                    )
                }


            }

            Spacer(modifier = modifier.height(7.dp))

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {

                Text(
                    text = meal?.mealName ?: "",
                    modifier = modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    ),
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = modifier.height(7.dp))

                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = meal?.mealArea ?: "",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = modifier.width(3.dp))
                    Text(
                        text = "|", color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = modifier.width(3.dp))
                    Text(
                        text = meal?.mealCategory ?: "",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    )

                }

                Spacer(modifier = modifier.height(14.dp))

                Text(
                    text = "Ingredients :", style = TextStyle(
                        fontWeight = FontWeight.ExtraBold, color = Color.Black, fontSize = 25.sp
                    )
                )

                Spacer(modifier = modifier.height(14.dp))

                Column(
                    modifier = modifier.padding(
                        vertical = 10.dp,
                        horizontal = 30.dp
                    )
                ) {
                    for (i in ingredients) {
                        IngredientsandMeasures(ingredients = i)
                        Spacer(modifier = modifier.height(6.dp))
                    }
                }

                Spacer(modifier = modifier.height(12.dp))

                Text(
                    text = "Instructions :", style = TextStyle(
                        fontWeight = FontWeight.ExtraBold, color = Color.Black, fontSize = 25.sp
                    )
                )

                Spacer(modifier = modifier.height(15.dp))

                Text(
                    text = meal?.mealInstruction ?: "",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal
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

        Button(
            onClick = {
                Intent(Intent.ACTION_VIEW, Uri.parse(meal?.mealYoutubeLink)).let {
                    context.startActivity(it)
                }

            },
            modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        ) {
            Row(
                modifier = modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.watch_vid),
                    contentDescription = null
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = "Watch Video",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }


    }

}