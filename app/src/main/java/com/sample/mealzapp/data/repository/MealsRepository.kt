package com.sample.mealzapp.data.repository

import com.sample.mealzapp.data.network.MealsNetworkDataSource
import javax.inject.Inject

class MealsRepository @Inject constructor(
    private val mealsNetworkDataSource: MealsNetworkDataSource
) {

    suspend fun getMealsCategories() = mealsNetworkDataSource.getMealsCategories()

}