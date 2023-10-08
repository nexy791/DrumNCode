package com.test.common.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

// method to handle api response
// if response is successful and body is not null, return success
// else return failure
// I use this method in data sources
suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>,
): Result<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else {
            Result.failure(Throwable(response.message()))
        }
    } catch (e: HttpException) {
        Result.failure(Throwable(e.message()))
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

fun <T : Any> handleApiFlow(
    execute: suspend () -> Response<T>,
): Flow<Result<T>> = flow {
    emit(handleApi(execute))
}.flowOn(Dispatchers.IO)
