package com.elkin.challengeinstaleap.data.remote.service

import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mediaType = "application/json; charset=UTF-8".toMediaTypeOrNull()
        val request = chain.request()

        val response: Response = try {
            chain.proceed(request)
        } catch (ex: Exception){
            Log.e("errorIntercept", ex.message.toString())
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(400)
                .message(ex.message.toString().replace('"', ' '))
                .build()
        }


        val newBody = if (response.isSuccessful) {
            createResponse(200, "", response.body.string())
        } else {
            createResponse(response.code, response.message, null)
        }

        val newResponse = Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message(response.message)
            .body(newBody.toResponseBody(mediaType))
            .build()

        val jsonResponse = newResponse.body.string()

        return newResponse.newBuilder().body(jsonResponse.toResponseBody(newResponse.body.contentType()))
            .build()
    }

    private fun createResponse(error: Int, message: String, result: String?): String {
        return "{\"error_code\": $error,\"message\": \"${message}\",\"result\": $result}"
    }
}