package com.nassdk.flowsummer.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nassdk.flowsummer.domain.MainUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

data class MainViewState(val sum: String, val inputState: String)

@Immutable
class MainViewModel(private val useCase: MainUseCase) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState("", ""))
    val viewState: StateFlow<MainViewState>
        get() = _viewState.asStateFlow()

    fun updateInputState(newValue: String) {
        _viewState.update { it.copy(inputState = newValue) }
    }

    fun calculateSum() {
        _viewState.update { it.copy(sum = "") }

        if (_viewState.value.inputState.isBlank()) return


        useCase.getSumsFlow(n = _viewState.value.inputState.replace(" ", "").toInt())
            .onEach { newSum ->
                _viewState.update { oldState ->
                    oldState.copy(sum = oldState.sum.plus("$newSum "))
                }
            }.launchIn(scope = viewModelScope)
    }
}