package com.example.recipeapp.presentation.recipe.bookmarks

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.recipe.bookmarks.components.BookMarkItem
import com.example.recipeapp.presentation.recipe.viewmodel.MealsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BookMark(
    modifier: Modifier = Modifier,
    viewModel: MealsViewModel = hiltViewModel(),
    onMealCLick: (String) -> Unit
) {

    val bookMarkState = viewModel.bookMarkState
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.onBookMarkEvent(BookMarkEvent.GetAllBookMarkMeals)
        viewModel.bookMarkEvent.collectLatest { event ->
            when (event) {
                is BookMarkUiEvent.ShowMessage -> {
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
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.card_background))
        ) {

            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(13.dp)
            ) {

                items(bookMarkState.bookMarkMeals) {
                    BookMarkItem(bookMarkMeal = it, onMealDetailClick = { mealid ->
                        onMealCLick(mealid)
                    }) { bookMarkMeal ->
                        viewModel.onBookMarkEvent(
                            BookMarkEvent.OnDeleteBookMarkMealClicked(
                                bookMarkMeal
                            )
                        )
                    }
                    Spacer(modifier = modifier.height(12.dp))
                }

            }


        }

        if (bookMarkState.bookMarkMeals.isEmpty()) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    imageVector = Icons.Filled.ThumbUp,
                    contentDescription = null,
                    tint = Color.Red
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "No BookMark Meal!",
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )

            }
        }

    }


}