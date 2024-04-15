package com.example.recipeapp.presentation.recipe.mealdetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.recipeapp.domain.model.Ingredients

@Composable
fun IngredientsandMeasures(
    modifier: Modifier = Modifier,
    ingredients: Ingredients
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = ingredients.ingredient,
            style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )

        Text(
            text = ": ${ingredients.itsMeasure}",
            style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )
    }

}