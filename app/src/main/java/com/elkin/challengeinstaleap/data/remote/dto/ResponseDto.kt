package com.elkin.challengeinstaleap.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDto<T>(
    @SerializedName("error_code")
    var error_code: Int = 0,
    @SerializedName("message")
    var message: String = "",
    @Expose
    @SerializedName("result")
    var result: T?
)