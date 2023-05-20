package com.elkin.challengeinstaleap.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DetailMediaMovieDto(
    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    var backdrop_path: String = "",
    @SerializedName("belongs_to_collection")
    var belongs_to_collection: CollectionMediaMovieDto? = CollectionMediaMovieDto(),
    @SerializedName("budget")
    var budget: Long = 0,
    @SerializedName("genres")
    var genres: List<MediaGenreDto> = emptyList(),
    @SerializedName("homepage")
    var homepage: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("imdb_id")
    var imdb_id: String = "",
    @SerializedName("original_language")
    var original_language: String = "",
    @SerializedName("original_title")
    var original_title: String = "",
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("popularity")
    var popularity: Float = 0.0f,
    @SerializedName("poster_path")
    var poster_path: String = "",
    @SerializedName("production_companies")
    var production_companies: List<NetworkMediaTvDto> = emptyList(),
    @SerializedName("production_countries")
    var production_countries: List<CountryMediaTvDto> = emptyList(),
    @SerializedName("release_date")
    var release_date: String = "",
    @SerializedName("revenue")
    var revenue: Long = 0,
    @SerializedName("runtime")
    var runtime: Int = 0,
    @SerializedName("status")
    var status: String = "",
    @SerializedName("tagline")
    var tagline: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("video")
    var video: Boolean = false,
    @SerializedName("vote_average")
    var vote_average: Double = 0.0,
    @SerializedName("vote_count")
    var vote_count: Int = 0,
)

data class CollectionMediaMovieDto(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("poster_path")
    var poster_path: String = "",
    @SerializedName("backdrop_path")
    var backdrop_path: String = "",
)
