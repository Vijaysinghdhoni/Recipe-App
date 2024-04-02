package com.example.recipeapp.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title : String,
    val selectedIcon : ImageVector,
    val unSelectedIcon : ImageVector
)
