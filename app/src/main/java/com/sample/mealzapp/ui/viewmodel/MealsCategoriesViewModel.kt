package com.sample.mealzapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.mealzapp.data.repository.MealsRepository
import com.sample.mealzapp.data.wrappers.ResponseWrapper
import com.sample.mealzapp.ui.states.MealsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsCategoriesViewModel @Inject constructor(
    private val mealsRepository: MealsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MealsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMealsCategories()
    }

    private fun getMealsCategories() {

        _uiState.value = _uiState.value.copy(state = MealsUiState.State.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            when (val response = mealsRepository.getMealsCategories()) {
                is ResponseWrapper.Success -> {
                    _uiState.value = if (response.data.isNullOrEmpty()) {
                        _uiState.value.copy(state = MealsUiState.State.Empty)
                    } else {
                        _uiState.value.copy(
                            state = MealsUiState.State.Success,
                            data = response.data
                        )
                    }

                }
                is ResponseWrapper.Error -> _uiState.value = _uiState.value.copy(
                    state = MealsUiState.State.Error
                )
            }
        }


    }

    fun onCategoryExpanded(categoryUiModel: CategoryUiModel) {
        mealsRepository.setCategoryExpanded(categoryUiModel)
    }


}