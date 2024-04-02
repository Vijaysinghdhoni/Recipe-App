package com.example.recipeapp.presentation.auth.forgot_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.presentation.auth.components.AccountComp
import com.example.recipeapp.presentation.auth.components.UpperAuthScreenText

@Composable
fun ForgotPassword(
    modifier: Modifier = Modifier,
    forgotPasswordState: ForgotPasswordState,
    onContinueBttnClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        UpperAuthScreenText(
            introTxtone = stringResource(id = R.string.forgot_pass),
            introTxtTwo = stringResource(id = R.string.frgt_pass_discrip)
        )

        Spacer(modifier = modifier.height(22.dp))

        OutlinedTextField(
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            },
            value = forgotPasswordState.email, onValueChange = {
                forgotPasswordState.email = it
            },
            maxLines = 1,
            textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(5.dp)),
            label = { Text(text = stringResource(id = R.string.email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = modifier.height(19.dp))

        Button(
            onClick = { onContinueBttnClick() },
            modifier = modifier
                .padding(6.dp)
                .fillMaxWidth(), shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = stringResource(id = R.string.cntinue))
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    ForgotPassword(forgotPasswordState = ForgotPasswordState(), onContinueBttnClick = {})
}