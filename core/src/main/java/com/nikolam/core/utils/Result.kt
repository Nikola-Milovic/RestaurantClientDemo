package com.nikolam.core.utils


sealed class Result<T> {
    class Loading<T> : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    data class Failed<T>(val exception: Exception) : Result<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(exception: Exception) = Failed<T>(exception)
    }
}