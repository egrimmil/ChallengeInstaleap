package com.elkin.challengeinstaleap.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MediaDto(
    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    var backdrop_path: String? = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title", alternate = ["name"])
    var title: String = "",
    @SerializedName("original_language")
    var original_language: String = "",
    @SerializedName("original_title", alternate = ["original_name"])
    var original_title: String = "",
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("poster_path")
    var poster_path: String = "",
    @SerializedName("media_type")
    var media_type: String = "",
    @SerializedName("genre_ids")
    var genre_ids: List<Int> = emptyList(),
    @SerializedName("popularity")
    var popularity: Float = 0.0f,
    @SerializedName("release_date")
    var release_date: String = "",
    @SerializedName("video")
    var video: Boolean = false,
    @SerializedName("vote_average")
    var vote_average: Double = 0.0,
    @SerializedName("vote_count")
    var vote_count: Int = 0,
    @SerializedName("first_air_date")
    var first_air_date: String = ""
)
