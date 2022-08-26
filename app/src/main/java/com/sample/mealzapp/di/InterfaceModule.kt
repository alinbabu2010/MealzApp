package com.sample.mealzapp.di

import com.sample.mealzapp.data.network.MealsNetworkDataSource
import com.sample.mealzapp.data.network.MealsNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InterfaceModule {

    @Binds
    fun bindMealsNetworkDataSource(
        mealsNetworkDataSourceImpl: MealsNetworkDataSourceImpl
    ): MealsNetworkDataSource


}