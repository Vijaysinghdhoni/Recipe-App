package com.example.recipeapp.presentation.auth.sigin

import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.auth.viewmodel.AuthenticationViewModel
import com.example.recipeapp.presentation.auth.components.AccountComp
import com.example.recipeapp.presentation.auth.components.UpperAuthScreenText
import com.example.recipeapp.presentation.navigation.Route
import com.example.recipeapp.presentation.auth.sigin.event.SignInScreenEvent
import com.example.recipeapp.presentation.auth.sigin.event.SignInUiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthenticationViewModel = hiltViewModel(),
    navController: NavController
) {

    val signInLocalState = viewModel.signInLocalState
    val context = LocalContext.current
    val signInState = viewModel.signInState


    LaunchedEffect(key1 = true) {
        viewModel.signIn_uiEventFlow.collectLatest { event ->

            when (event) {
                is SignInUiEvent.ShowMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }

                is SignInUiEvent.NavigationEvent -> {
                    viewModel.updateUserLoginState(true)
                    navController.navigate(Route.RecipeStartNavigation.route) {
                        popUpTo(Route.IntroScreen.route) {
                            inclusive = true
                        }
                    }
                    Toast.makeText(context, "Login Success!", Toast.LENGTH_LONG).show()
                }

            }

        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {
        UpperAuthScreenText(
            introTxtone = stringResource(id = R.string.wlcm_bck),
            introTxtTwo = stringResource(id = R.string.login_now)
        )

        Spacer(modifier = modifier.height(22.dp))

        OutlinedTextField(
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            },
            value = signInLocalState.email, onValueChange = {
                viewModel.onSignInEvent(SignInScreenEvent.EmailEntered(it))
            },
            maxLines = 1,
            textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(5.dp)),
            label = { Text(text = stringResource(id = R.string.email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = modifier.height(13.dp))

        OutlinedTextField(
            trailingIcon = {
                Icon(
                    modifier = modifier.clickable {
                        viewModel.onSignInEvent(SignInScreenEvent.OnPasswordTogale)
                    },
                    painter = if (signInLocalState.isPasswordVisible) {
                        painterResource(id = R.drawable.password_visible)
                    } else {
                        painterResource(id = R.drawable.password_hide__ic)
                    },
                    contentDescription = null
                )
            },
            value = signInLocalState.password, onValueChange = {
                viewModel.onSignInEvent(SignInScreenEvent.UsrPasswordEntered(it))
            },
            maxLines = 1,
            textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(5.dp)),
            label = { Text(text = stringResource(id = R.string.password)) },
            visualTransformation = if (signInLocalState.isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )


        Spacer(modifier = modifier.height(19.dp))

        Text(
            text = stringResource(id = R.string.forgot_pass),
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Route.ForgotPassWordScreen.route)
                },
            textAlign = TextAlign.End,
            style = TextStyle(
                color = colorResource(id = R.color.purple_500),
                fontSize = 15.sp
            )
        )

        Spacer(modifier = modifier.height(19.dp))

        Button(
            onClick = {
                viewModel.onSignInEvent(SignInScreenEvent.OnSignInClick)
            },
            modifier = modifier
                .padding(6.dp)
                .fillMaxWidth(), shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = stringResource(id = R.string.sign_in))
        }

        Spacer(modifier = modifier.height(15.dp))


        AccountComp(
            onBttnClick = {
                navController.navigate(Route.SignUpScreen.route)
            },
            acountTxt = stringResource(id = R.string.don_nt_acc),
            bttnTxt = stringResource(id = R.string.sign_up)
        )

        Spacer(modifier = modifier.height(25.dp))

        if (signInState.isLoading) {

            CircularProgressIndicator(
                modifier = modifier.align(Alignment.CenterHorizontally),
                color = Color.Black,
                strokeWidth = 4.dp,
                trackColor = Color.Gray
            )

        }

    }

}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun Preview() {
//    SignInScreen(signInLocalState = SignInLocalState(), onSigInClick = {})
//}