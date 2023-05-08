package com.elkin.challengeinstaleap.data.remote.service

import com.elkin.challengeinstaleap.BuildConfig
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto

sealed class ResponseWrapper<T>(
    data: T?,
    message: String?
) {
    data class Success<T>(val data: T) : ResponseWrapper<T>(data = data, null)
    data class NetworkError<T>(val message: String) : ResponseWrapper<T>(null, message = message)
    data class ServerError<T>(val message: String) : ResponseWrapper<T>(null, message = message)
}

fun <T> safeApiCallWithWrapperResponse(response: ResponseDto<T>): ResponseWrapper<ResponseDto<T>> {
    return when (response.error_code) {
            in 300..499 -> {
                ResponseWrapper.NetworkError(
                    if(BuildConfig.DEBUG)response.message else "Por favor revisa tu conexión de internet."
                )
            }
            in 500..599 -> {
                ResponseWrapper.ServerError(
                    if(BuildConfig.DEBUG)response.message else "No se pudo completar la tarea, por favor intenta más tarde."
                )
            }
            else -> {
                ResponseWrapper.Success(response)
            }
        }
}