package com.example.recipeapp.presentation.auth.sigin.state

data class SignInLocalState(
    var email: String = "",
    var password: String = "",
    var isPasswordVisible: Boolean = false,

)
