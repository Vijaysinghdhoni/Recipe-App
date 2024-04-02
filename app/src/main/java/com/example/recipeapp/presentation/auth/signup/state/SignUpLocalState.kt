package com.example.recipeapp.presentation.auth.signup.state

data class SignUpLocalState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val cnfrmPassword: String = "",
    val isPasswordVisbile: Boolean = false,
    val isCnfrmPasswordVisible: Boolean = false
)
