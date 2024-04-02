package com.example.recipeapp.presentation.recipe.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int,
    fontWeight: FontWeight
) {

    Text(
        text = text,
        style = TextStyle(fontSize = fontSize.sp, fontWeight = fontWeight, color = Color.Black)
    )

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Per() {

    WelcomeText(
        text = "What do you want to cook today?",
        fontSize = 20,
        fontWeight = FontWeight.Medium
    )

}