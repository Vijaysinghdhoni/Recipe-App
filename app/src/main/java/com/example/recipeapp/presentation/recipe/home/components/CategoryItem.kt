package com.example.recipeapp.presentation.recipe.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.Category

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category
) {

    Card(
        modifier = modifier
            .width(110.dp)
            .height(140.dp)
    ) {

        Box(modifier = modifier.fillMaxWidth()) {

            AsyncImage(
                model = category.categoryImg,
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.placeholder_img),
                error = painterResource(id = R.drawable.placeholder_img),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .align(Alignment.BottomStart)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = category.categoryName,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        fontSize = 15.sp
                    ),
                )


            }


        }

    }

}