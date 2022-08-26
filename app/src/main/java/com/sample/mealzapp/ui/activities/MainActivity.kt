package com.sample.mealzapp.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.mealzapp.R
import com.sample.mealzapp.ui.states.MealsUiState
import com.sample.mealzapp.ui.theme.MealzAppTheme
import com.sample.mealzapp.ui.viewmodel.MealsCategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                MealsCategoriesScreen()
            }
        }
    }

}

@Composable
fun MealsCategoriesScreen() {

    val viewModel = hiltViewModel<MealsCategoriesViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState.state) {
        MealsUiState.State.None,
        MealsUiState.State.Loading -> CircularProgressIndicator()
        MealsUiState.State.Empty -> Text(text = stringResource(R.string.empty_categories))
        MealsUiState.State.Error -> Text(text = uiState.error?.localizedMessage.toString())
        MealsUiState.State.Success -> {
            LazyColumn {
                items(uiState.data) { category ->
                    Text(text = category.name)
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealsCategoriesScreen()
    }
}