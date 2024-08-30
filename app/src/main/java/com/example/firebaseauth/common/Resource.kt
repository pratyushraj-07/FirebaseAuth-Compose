package com.example.firebaseauth.common

sealed class Resource<out T> {
    data class Success<out T>(val data : T): Resource<T>()
    data class Error<T>(val error: String, val data: T? = null) : Resource<T>()
    data object Loading : Resource<Nothing>()
}
