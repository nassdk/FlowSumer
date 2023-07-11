package com.nassdk.flowsummer.domain

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.scan

/**
Необходимо создать массив Flow<Int>,
количества N, каждый из которых после задержки в (index + 1) * 100,
эмитит значение index + 1.

Т.е. Flow с индексом 0 с задержкой 100 эмитит значение 1,
Flow с индексом 1 с задержкой 200 эмитит значение 2.
 */
class MainUseCaseImpl : MainUseCase {

    @OptIn(FlowPreview::class)
    override fun getSumsFlow(n: Int): Flow<Int> {
        return Array(
            size = n,
            init = { index ->
                flow {
                    delay(100L * (index + 1))
                    emit(index + 1)
                }
            }
        )
            .asFlow()
            .flattenMerge()
            .scan(
                initial = 0,
                operation = { acc, value ->
                    acc + value
                }
            )
            .drop(1)
    }
}