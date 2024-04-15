package com.example.recipeapp.presentation.auth.forgot_password

sealed class ForgotPasswordEvent {

    object OnContinueCLick : ForgotPasswordEvent()

    data class OnEmailEntered(val email: String) : ForgotPasswordEvent()

}