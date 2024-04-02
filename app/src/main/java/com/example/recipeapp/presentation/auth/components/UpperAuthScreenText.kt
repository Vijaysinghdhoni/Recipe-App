package com.example.recipeapp.presentation.auth.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R

@Composable
fun UpperAuthScreenText(
    modifier: Modifier = Modifier,
    introTxtone : String,
    introTxtTwo : String
) {

    Text(
        text = introTxtone,
        color = Color.Black,
        fontSize = 19.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = modifier.height(5.dp))

    Text(
        text = introTxtTwo
    )

}