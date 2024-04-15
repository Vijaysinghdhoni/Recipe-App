package com.example.recipeapp.presentation.recipe.bookmarks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.BookMarkMeal

@Composable
fun BookMarkItem(
    modifier: Modifier = Modifier,
    bookMarkMeal: BookMarkMeal,
    onMealDetailClick: (String) -> Unit,
    onIconCLick: (BookMarkMeal) -> Unit
) {


    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(190.dp)
            .clickable {
                onMealDetailClick(bookMarkMeal.mealID)
            }
    ) {

        Box(modifier = modifier.fillMaxSize()) {

            AsyncImage(
                model = bookMarkMeal.mealImg,
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.placeholder_img),
                error = painterResource(id = R.drawable.placeholder_img),
                contentScale = ContentScale.Crop
            )


            Text(
                text = bookMarkMeal.mealName,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = modifier
                    .padding(5.dp)
                    .align(Alignment.TopStart),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            SmallFloatingActionButton(
                onClick = {
                    onIconCLick(bookMarkMeal)
                },
                modifier = modifier.align(Alignment.BottomEnd),
                shape = CircleShape,
                containerColor = colorResource(id = R.color.transpernt_back)
            ) {
                Icon(
                    if (bookMarkMeal.isMealFav) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Outlined.FavoriteBorder
                    },
                    "Small floating action button.",
                    tint = Color.White
                )
            }
        }

    }


}