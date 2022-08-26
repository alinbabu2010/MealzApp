package com.sample.mealzapp.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.sample.mealzapp.R
import com.sample.mealzapp.data.models.Category

@Composable
fun MealDetailsScreen(category: Category) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val imageSize: Dp by animateDpAsState(
        targetValue = if (isExpanded)
            dimensionResource(R.dimen.meal_image_size_expanded)
        else dimensionResource(R.dimen.meal_image_size_collapsed)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card {
                AsyncImage(
                    model = category.imageUrl,
                    contentDescription = "Meal image",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(imageSize)
                        .padding(dimensionResource(R.dimen.meal_image_padding))
                )
            }
            Text(text = category.name)
        }

        Button(onClick = { isExpanded = !isExpanded }) {
            Text(text = "Change state of meal profile picture")
        }
    }
}