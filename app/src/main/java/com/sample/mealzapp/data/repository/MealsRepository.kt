package com.sample.mealzapp.data.repository

import com.sample.mealzapp.data.models.Category
import com.sample.mealzapp.data.network.MealsNetworkDataSource
import com.sample.mealzapp.data.wrappers.ResponseWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsRepository @Inject constructor(
    private val mealsNetworkDataSource: MealsNetworkDataSource
) {

    private var cachedMeals = listOf<Category>()

    suspend fun getMealsCategories(): ResponseWrapper<List<Category>> {
        val response = mealsNetworkDataSource.getMealsCategories()
        cachedMeals = when (response) {
            is ResponseWrapper.Error -> cachedMeals
            is ResponseWrapper.Success -> response.data ?: cachedMeals
        }
        return response
    }

    fun getMeal(id: String?): Category? {
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }

}