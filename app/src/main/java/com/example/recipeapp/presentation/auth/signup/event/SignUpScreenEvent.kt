package com.example.recipeapp.presentation.auth.signup.event

sealed class SignUpScreenEvent {

    data class UserNameEntered(val name: String) : SignUpScreenEvent()

    data class EmailEntered(val email: String) : SignUpScreenEvent()

    data class UsrPasswordEntered(val password: String) : SignUpScreenEvent()

    data class UsrCnfrmPassword(val cnfrmPassword: String) : SignUpScreenEvent()

    object OnPasswordTogle : SignUpScreenEvent()

    object OnCnfrmPsswrdToogle : SignUpScreenEvent()

    object OnSignupClicked : SignUpScreenEvent()

}