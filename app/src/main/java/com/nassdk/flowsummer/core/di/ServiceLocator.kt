package com.nassdk.flowsummer.core.di

import com.nassdk.flowsummer.domain.MainUseCase
import com.nassdk.flowsummer.domain.MainUseCaseImpl

object ServiceLocator {

    val useCase: MainUseCase by lazy {
        MainUseCaseImpl()
    }
}