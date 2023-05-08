package com.elkin.challengeinstaleap.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TrendingDto(
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("results")
    var results: MutableList<MediaDto> = ArrayList(),
    @SerializedName("total_pages")
    var total_pages: Int = 0,
    @SerializedName("total_results")
    var total_results: Int = 0,
)
