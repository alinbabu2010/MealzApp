package com.sample.mealzapp.ui.states

import com.sample.mealzapp.data.models.CategoryUiModel

data class MealsUiState(
    val state: State = State.None,
    val data: List<CategoryUiModel> = emptyList(),
    val error: Throwable? = Throwable(),
) {

    sealed class State {
        object None : State()
        object Loading : State()
        object Success : State()
        object Empty : State()
        object Error : State()
    }

}