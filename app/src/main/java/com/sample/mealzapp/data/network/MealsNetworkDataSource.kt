package com.sample.mealzapp.data.network

import com.sample.mealzapp.data.models.Category
import com.sample.mealzapp.data.wrappers.ResponseWrapper

interface MealsNetworkDataSource {
    suspend fun getMealsCategories(): ResponseWrapper<List<Category>>
}