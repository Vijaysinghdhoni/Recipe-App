package com.example.recipeapp.presentation.recipe.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserData(
    modifier: Modifier = Modifier,
    userName: String,
    userEmail: String
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            text = userName,
            style = TextStyle(
                color = Color.Black,
                fontSize = 21.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )

        Spacer(modifier = modifier.height(10.dp))

        Text(
            text = userEmail,
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        )

        Spacer(modifier = modifier.height(18.dp))

        Divider(color = Color.Black, thickness = 1.dp)
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Prev(){
    UserData(userName = "Vinod", userEmail ="vinod1234@gmail.com" )
}