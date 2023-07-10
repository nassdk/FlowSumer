package com.nassdk.flowsummer.domain

import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun getSumsFlow(n: Int): Flow<Int>
}