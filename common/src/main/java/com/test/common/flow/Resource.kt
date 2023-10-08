package com.test.common.flow

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

    companion object {

        fun <T> Result<T>.asResource(): Resource<T> = when (this.isSuccess) {
            true -> Success(this.getOrNull()!!)
            false -> Error(this.exceptionOrNull()!!)
        }
    }

}