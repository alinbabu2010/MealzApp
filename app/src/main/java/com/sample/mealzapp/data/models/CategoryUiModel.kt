package com.sample.mealzapp.data.models

data class CategoryUiModel(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    var isExpanded: Boolean = false,
)
