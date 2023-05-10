package com.elkin.challengeinstaleap.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MediaGenreDto(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
)

data class ListGenresMediaDto(
    @SerializedName("genres")
    var genres: List<MediaGenreDto> = emptyList(),
)
