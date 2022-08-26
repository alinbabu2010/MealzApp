@file:OptIn(ExperimentalAnimationApi::class)

package com.sample.mealzapp.ui.navigations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sample.mealzapp.ui.screens.MealDetailsScreen
import com.sample.mealzapp.ui.screens.MealsCategoriesScreen
import com.sample.mealzapp.ui.viewmodel.MealDetailsViewModel

const val ARG_MEAL = "MEAL_CATEGORY_ID"

enum class MealsScreens {
    LIST_SCREEN,
    DETAILS_SCREEN
}

@Composable
fun MealsNavigation() {

    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = MealsScreens.LIST_SCREEN.name
    ) {

        composable(route = MealsScreens.LIST_SCREEN.name) {
            MealsCategoriesScreen(navController)
        }

        val route = "${MealsScreens.DETAILS_SCREEN.name}/{$ARG_MEAL}"
        composable(
            route = route,
            arguments = listOf(navArgument(ARG_MEAL) {
                type = NavType.StringType
            })
        ) {
            val viewModel = hiltViewModel<MealDetailsViewModel>()
            viewModel.mealState?.let { category -> MealDetailsScreen(category) }
        }

    }

}

