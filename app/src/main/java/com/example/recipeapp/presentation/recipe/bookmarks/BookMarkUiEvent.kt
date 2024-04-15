package com.example.recipeapp.presentation.recipe.bookmarks

sealed class BookMarkUiEvent {

    data class ShowMessage(val message: String) : BookMarkUiEvent()

}