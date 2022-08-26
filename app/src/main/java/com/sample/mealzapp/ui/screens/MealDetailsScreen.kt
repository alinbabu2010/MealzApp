package com.sample.mealzapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.sample.mealzapp.R
import com.sample.mealzapp.data.models.Category

@Composable
fun MealDetailsScreen(category: Category) {
    Column {
        Row {
            Card {
                AsyncImage(
                    model = category.imageUrl,
                    contentDescription = "Meal image",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(dimensionResource(R.dimen.meal_image_size))
                )
            }
            Text(text = category.name)
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Change state of meal profile picture")
        }
    }
}