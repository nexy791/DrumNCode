package com.test.data.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class FlowExt {

    companion object {

        fun <T : Any, R : Any> Flow<Result<T>>.mapResult(
            mapper: (T) -> R,
        ): Flow<Result<R>> {
            return this
                .map {
                    if (it.isSuccess) Result.success(mapper(it.getOrNull()!!))
                    else Result.failure(it.exceptionOrNull()!!)
                }
                .catch { emit(Result.failure(it)) }
        }

    }

}