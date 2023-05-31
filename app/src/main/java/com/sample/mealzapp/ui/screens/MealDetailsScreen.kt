package com.sample.mealzapp.ui.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sample.mealzapp.R
import com.sample.mealzapp.data.models.CategoryUiModel

@Composable
fun MealDetailsScreen(category: CategoryUiModel) {

    var profilePictureState by remember {
        mutableStateOf(ProfilePictureState.Normal)
    }

    val transition = updateTransition(targetState = profilePictureState, label = "")

    val imageSize: Dp by transition.animateDp(label = "") {
        it.size
    }

    val color by transition.animateColor(label = "") {
        it.color
    }

    val widthSize: Dp by transition.animateDp(label = "") {
        it.borderWidth
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(
                modifier = Modifier.padding(dimensionResource(R.dimen.details_card_padding)),
                shape = CircleShape,
                border = BorderStroke(
                    width = widthSize,
                    color = color
                )
            ) {
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

        Button(onClick = {
            profilePictureState = if (profilePictureState == ProfilePictureState.Normal)
                ProfilePictureState.Expanded else ProfilePictureState.Normal
        }) {
            Text(text = "Change state of meal profile picture")
        }
    }
}

enum class ProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}