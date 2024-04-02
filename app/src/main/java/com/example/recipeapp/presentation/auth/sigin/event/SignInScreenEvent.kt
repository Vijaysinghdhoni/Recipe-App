package com.example.recipeapp.presentation.auth.sigin.event

sealed class SignInScreenEvent {

    data class EmailEntered(val email: String) : SignInScreenEvent()

    data class UsrPasswordEntered(val password: String) : SignInScreenEvent()

    object OnPasswordTogale : SignInScreenEvent()

    object  OnSignInClick : SignInScreenEvent()

}