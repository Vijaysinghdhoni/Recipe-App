package com.example.recipeapp.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.recipe.BookMark
import com.example.recipeapp.presentation.recipe.home.Home
import com.example.recipeapp.presentation.recipe.Setting


@Composable
fun RecipeNavigator() {

    val navItems = remember {
        listOf(
            BottomNavigationItem(
                title = Route.RecipeHome.route,
                selectedIcon = Icons.Filled.Home,
                unSelectedIcon = Icons.Outlined.Home
            ),
            BottomNavigationItem(
                title = Route.RecipeBookMark.route,
                selectedIcon = Icons.Filled.Favorite,
                unSelectedIcon = Icons.Outlined.FavoriteBorder
            ),
            BottomNavigationItem(
                title = Route.RecipeSetting.route,
                selectedIcon = Icons.Filled.Settings,
                unSelectedIcon = Icons.Outlined.Settings
            )
        )
    }
    val selctedItem = remember {
        mutableIntStateOf(0)
    }

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = colorResource(id = R.color.card_background),
                tonalElevation = 8.dp
            ) {
                navItems.forEachIndexed { index, item ->

                    NavigationBarItem(
                        selected = selctedItem.intValue == index,
                        onClick = {
                            selctedItem.intValue = index
                            Log.d("MyTag", "selcted item is $selctedItem")
                            navController.navigate(item.title)
                        },
                        label = {
                            Text(text = item.title)
                        },
                        alwaysShowLabel = true,
                        icon = {
                            Icon(
                                imageVector = if (index == selctedItem.intValue) {
                                    item.selectedIcon
                                } else {
                                    item.unSelectedIcon
                                },
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    ) {

        Box(modifier = Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = Route.RecipeHome.route
            ) {

                composable(
                    route = Route.RecipeHome.route
                ) {
                    Home()
                }

                composable(
                    route = Route.RecipeBookMark.route
                ) {
                    BookMark()
                }

                composable(
                    route = Route.RecipeSetting.route
                ) {
                    Setting()
                }


            }

        }


    }

}