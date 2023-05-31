package com.mahnoosh.core.data.models.local

sealed class ResultWrapper<out T : Any?> {
    data class Success<out T : Any?>(val data: T) : ResultWrapper<T>()
    data class Error(val throwable: Throwable) : ResultWrapper<Nothing>()
}