package com.example.recipeapp.presentation.auth.forgot_password

data class ForgotPasswordState(
    val email : String = "",
    val isLoading : Boolean = false,
    val error : String? =null,
    val succesMssg : String = ""
)
