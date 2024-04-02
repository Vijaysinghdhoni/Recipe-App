package com.example.recipeapp.presentation.auth.components

import android.accounts.Account
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AccountComp(
    modifier: Modifier = Modifier,
    onBttnClick: () -> Unit,
    acountTxt: String,
    bttnTxt: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(text = acountTxt)
        Spacer(modifier = modifier.width(2.dp))
        Text(
            text = bttnTxt,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = modifier.clickable {
                onBttnClick()
            })

    }
}