package com.example.recipeapp.presentation.auth.sigin.event

sealed class SignInUiEvent {

    data class ShowMessage(val message: String) : SignInUiEvent()

    data class NavigationEvent(val route: String) : SignInUiEvent()

}