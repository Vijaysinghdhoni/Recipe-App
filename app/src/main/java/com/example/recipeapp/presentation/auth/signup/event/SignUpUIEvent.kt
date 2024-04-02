package com.example.recipeapp.presentation.auth.signup.event

import com.example.recipeapp.presentation.auth.sigin.event.SignInUiEvent

sealed class SignUpUIEvent {

    data class ShowMessage(val message: String) : SignUpUIEvent()

    data class NavigationEvent(val route: String) : SignUpUIEvent()

}