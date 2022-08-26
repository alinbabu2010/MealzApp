package com.sample.mealzapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.sample.mealzapp.R
import com.sample.mealzapp.data.models.Category
import com.sample.mealzapp.ui.components.ProgressErrorView
import com.sample.mealzapp.ui.states.MealsUiState
import com.sample.mealzapp.ui.theme.MealzAppTheme
import com.sample.mealzapp.ui.viewmodel.MealsCategoriesViewModel

@Composable
fun MealsCategoriesScreen() {

    val viewModel = hiltViewModel<MealsCategoriesViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState.state) {
        MealsUiState.State.None,
        MealsUiState.State.Loading -> ProgressErrorView(isProgress = true)
        MealsUiState.State.Empty -> ProgressErrorView(message = stringResource(R.string.empty_categories))
        MealsUiState.State.Error -> ProgressErrorView(message = uiState.error?.localizedMessage.toString())
        MealsUiState.State.Success -> {
            LazyColumn(contentPadding = PaddingValues(dimensionResource(R.dimen.lazy_column_padding))) {
                items(uiState.data) { category ->
                    MealCategory(category)
                }
            }
        }
    }

}


@Composable
fun MealCategory(category: Category) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var lineCount by rememberSaveable {
        mutableStateOf(3)
    }

    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.category_card_corner_size)),
        elevation = dimensionResource(R.dimen.category_card_elevation),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.category_card_padding))
    ) {
        Row(Modifier.animateContentSize()) {

            Image(
                painter = rememberAsyncImagePainter(category.imageUrl),
                contentDescription = "Meals Category imgae",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.categroy_image_size))
                    .padding(dimensionResource(R.dimen.category_image_padding))
                    .align(Alignment.CenterVertically)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8F)
                    .padding(dimensionResource(R.dimen.category_card_column_padding))
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = category.name, style = MaterialTheme.typography.h6)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = category.description,
                        style = MaterialTheme.typography.body2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded) 10 else 3,
                        onTextLayout = { lineCount = it.lineCount }
                    )
                }
            }

            if (lineCount >= 3) {
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.icon_button_padding))
                        .align(
                            if (!isExpanded) Alignment.CenterVertically
                            else Alignment.Bottom
                        )
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp
                        else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Expand collapse row icon"
                    )
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