package com.example.recipeapp.presentation.auth.signup

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
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.auth.viewmodel.AuthenticationViewModel
import com.example.recipeapp.presentation.auth.components.AccountComp
import com.example.recipeapp.presentation.auth.components.UpperAuthScreenText
import com.example.recipeapp.presentation.navigation.Route
import com.example.recipeapp.presentation.auth.signup.event.SignUpScreenEvent
import com.example.recipeapp.presentation.auth.signup.event.SignUpUIEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthenticationViewModel = hiltViewModel(),
    navController: NavController
) {

    val signUpLocalState = viewModel.signUpLocalState
    val context = LocalContext.current
    val signUpState = viewModel.signUpState

    LaunchedEffect(key1 = true) {
        viewModel.uiEventFlow.collectLatest { event ->

            when (event) {
                is SignUpUIEvent.ShowMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }

                is SignUpUIEvent.NavigationEvent -> {
                    viewModel.updateUserLoginState(true)
                    navController.navigate(Route.RecipeStartNavigation.route) {
                        popUpTo(Route.IntroScreen.route) {
                            inclusive = true
                        }
                    }
                    Toast.makeText(context, "Register Sucess!!", Toast.LENGTH_LONG).show()
                }

            }

        }
    }

    //status bar color change
    //then signIn and other screens and then complete sigIn/signUp with firebase and then nested navigation with bnv ad then recipe

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {
        UpperAuthScreenText(
            introTxtone = stringResource(id = R.string.getting_started),
            introTxtTwo = stringResource(id = R.string.create_acc)
        )

        Spacer(modifier = modifier.height(22.dp))

        OutlinedTextField(
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            value = signUpLocalState.name, onValueChange = {
                viewModel.onSignUpEvent(SignUpScreenEvent.UserNameEntered(it))
            },
            maxLines = 1,
            textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(5.dp)),
            label = { Text(text = stringResource(id = R.string.name)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = modifier.height(10.dp))

        OutlinedTextField(
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            },
            value = signUpLocalState.email, onValueChange = {
                viewModel.onSignUpEvent(SignUpScreenEvent.EmailEntered(it))
            },
            maxLines = 1,
            textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(5.dp)),
            label = { Text(text = stringResource(id = R.string.email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = modifier.height(10.dp))

        OutlinedTextField(
            trailingIcon = {
                Icon(
                    modifier = modifier.clickable {
                        viewModel.onSignUpEvent(SignUpScreenEvent.OnPasswordTogle)
                    },
                    painter = if (signUpLocalState.isPasswordVisbile) {
                        painterResource(id = R.drawable.password_visible)
                    } else {
                        painterResource(
                            id = R.drawable.password_hide__ic
                        )
                    },
                    contentDescription = null
                )
            },
            value = signUpLocalState.password, onValueChange = {
                viewModel.onSignUpEvent(SignUpScreenEvent.UsrPasswordEntered(it))
            },
            maxLines = 1,
            textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(5.dp)),
            label = { Text(text = stringResource(id = R.string.password)) },
            visualTransformation = if (signUpLocalState.isPasswordVisbile) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = modifier.height(10.dp))

        OutlinedTextField(
            trailingIcon = {
                Icon(
                    modifier = modifier.clickable {
                        viewModel.onSignUpEvent(SignUpScreenEvent.OnCnfrmPsswrdToogle)
                    },
                    painter = if (signUpLocalState.isCnfrmPasswordVisible) {
                        painterResource(id = R.drawable.password_visible)
                    } else {
                        painterResource(
                            id = R.drawable.password_hide__ic
                        )
                    },
                    contentDescription = null
                )
            },
            value = signUpLocalState.cnfrmPassword, onValueChange = {
                viewModel.onSignUpEvent(SignUpScreenEvent.UsrCnfrmPassword(it))
            },
            maxLines = 1,
            textStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(5.dp)),
            label = { Text(text = stringResource(id = R.string.confrm_passw)) },
            visualTransformation = if (signUpLocalState.isCnfrmPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = modifier.height(13.dp))

        Button(
            onClick = { viewModel.onSignUpEvent(SignUpScreenEvent.OnSignupClicked) },
            modifier = modifier
                .padding(6.dp)
                .fillMaxWidth(), shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }

        Spacer(modifier = modifier.height(15.dp))


        AccountComp(
            onBttnClick = {
                navController.navigate(Route.SignINScreen.route)
            },
            acountTxt = stringResource(id = R.string.alrdy_acc),
            bttnTxt = stringResource(id = R.string.sign_in)
        )

        Spacer(modifier = modifier.height(25.dp))

        if (signUpState.isLoading) {

            CircularProgressIndicator(
                modifier = modifier.align(Alignment.CenterHorizontally),
                color = Color.Black,
                strokeWidth = 4.dp,
                trackColor = Color.Gray
            )

        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun Preview() {
//    SignUpScreen(signUpState = SignUpState(), onSignUpClick = {})
//}