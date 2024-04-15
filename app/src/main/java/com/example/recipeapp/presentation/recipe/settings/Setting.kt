package com.example.recipeapp.presentation.recipe.settings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.auth.viewmodel.AuthenticationViewModel
import com.example.recipeapp.presentation.navigation.Route
import com.example.recipeapp.presentation.recipe.settings.components.LogoutAlertDialog
import com.example.recipeapp.presentation.recipe.settings.components.LogoutCom
import com.example.recipeapp.presentation.recipe.settings.components.SwitchCom
import com.example.recipeapp.presentation.recipe.settings.components.UserData
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Setting(
    modifier: Modifier = Modifier,
    viewModel: AuthenticationViewModel = hiltViewModel(),
    onlogoutClick: () -> Unit
) {

    val context = LocalContext.current
    val settingState = viewModel.settingState

    LaunchedEffect(key1 = true) {
        viewModel.onSettingScreenEvent(SettingLocalEvent.GetUser)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.card_background))
            .padding(15.dp),
    ) {

        UserData(userName = settingState.userName!!, userEmail = settingState.userEmail!!)

        Spacer(modifier = modifier.height(18.dp))

        SwitchCom(text = "Dark Theme", icon = R.drawable.dark_theme_ic)

        Spacer(modifier = modifier.height(17.dp))

        LogoutCom(text = "Log out", icon = Icons.Filled.AccountCircle) {
            viewModel.onSettingScreenEvent(SettingLocalEvent.ShowAlertBox(true))
        }
    }

    if (settingState.showAlertDialog) {
        LogoutAlertDialog(
            onDismissRequest = {
                viewModel.onSettingScreenEvent(SettingLocalEvent.ShowAlertBox(false))
            },
            onConfirmation = {
                viewModel.onSettingScreenEvent(SettingLocalEvent.ShowAlertBox(false))
                viewModel.onSettingScreenEvent(SettingLocalEvent.LogoutClicked)
                onlogoutClick()
            },
            dialogTitle = "Logout!",
            dialogText = "Are You Sure You Want To Logout?",
            icon = Icons.Filled.ExitToApp
        )
    }


}
