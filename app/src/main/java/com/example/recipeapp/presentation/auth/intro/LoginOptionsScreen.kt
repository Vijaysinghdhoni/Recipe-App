package com.example.recipeapp.presentation.auth.intro

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.auth.components.AccountComp
import com.example.recipeapp.presentation.auth.viewmodel.AuthenticationViewModel
import com.example.recipeapp.presentation.navigation.Route
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginOptionsScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(4.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.food_svg),
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Inside
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        ) {

            Button(
                onClick = {
                    navController.navigate(Route.SignINScreen.route)
                },
                shape = RoundedCornerShape(30.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Sign In with email")
            }

            Spacer(modifier = modifier.height(10.dp))

            AccountComp(
                onBttnClick = {
                    navController.navigate(Route.SignUpScreen.route)
                },
                acountTxt = stringResource(id = R.string.don_nt_acc),
                bttnTxt = stringResource(id = R.string.sign_up)
            )

            Spacer(modifier = modifier.height(10.dp))

        }


    }


}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun preview() {
//    LoginOptionsScreen(onSignInclick = {}, onSignupCLick = {})
//}
