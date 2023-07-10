package com.nassdk.flowsummer.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nassdk.flowsummer.domain.MainUseCase
import com.nassdk.flowsummer.ui.MainViewModel

class ViewModelFactory(private val useCase: MainUseCase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(useCase = useCase) as T
    }
}