package com.elkin.challengeinstaleap.core.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val loading: Boolean? = null
) {
    class Success<T>(data: T? = null) : Resource<T>(data = data)
    class Error<T>(data: T? = null, message: String? = null) : Resource<T>(data, message = message)
    class Loading<T>(loading: Boolean? = null) : Resource<T>(loading = loading)
}
