package com.elkin.challengeinstaleap.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DetailMediaTvDto(
    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    var backdrop_path: String = "",
    @SerializedName("created_by")
    var created_by: List<CreateMediaTvDto> = emptyList(),
    @SerializedName("episode_run_time")
    var episode_run_time: List<Int> = emptyList(),
    @SerializedName("first_air_date")
    var first_air_date: String = "",
    @SerializedName("genres")
    var genres: List<MediaGenreDto> = emptyList(),
    @SerializedName("homepage")
    var homepage: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("in_production")
    var in_production: Boolean = false,
    @SerializedName("languages")
    var languages: List<String> = emptyList(),
    @SerializedName("last_air_date")
    var last_air_date: String = "",
    @SerializedName("last_episode_to_air")
    var last_episode_to_air: EpisodeMediaTvDto = EpisodeMediaTvDto(),
    @SerializedName("name")
    var name: String = "",
    @SerializedName("next_episode_to_air")
    var next_episode_to_air: EpisodeMediaTvDto = EpisodeMediaTvDto(),
    @SerializedName("networks")
    var networks: List<NetworkMediaTvDto> = emptyList(),
    @SerializedName("number_of_episodes")
    var number_of_episodes: Int = 0,
    @SerializedName("number_of_seasons")
    var number_of_seasons: Int = 0,
    @SerializedName("origin_country")
    var origin_country: List<String> = emptyList(),
    @SerializedName("original_language")
    var original_language: String = "",
    @SerializedName("original_name")
    var original_name: String = "",
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
    @SerializedName("seasons")
    var seasons: List<SeasonMediaTvDto> = emptyList(),
    @SerializedName("status")
    var status: String = "",
    @SerializedName("tagline")
    var tagline: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("vote_average")
    var vote_average: Double = 0.0,
    @SerializedName("vote_count")
    var vote_count: Int = 0,
)

data class CreateMediaTvDto(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("credit_id")
    var credit_id: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("gender")
    var gender: Int = 0,
    @SerializedName("profile_path")
    var profile_path: String = "",
)

data class EpisodeMediaTvDto(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("vote_average")
    var vote_average: Double = 0.0,
    @SerializedName("vote_count")
    var vote_count: Int = 0,
    @SerializedName("air_date")
    var air_date: String = "",
    @SerializedName("episode_number")
    var episode_number: Int = 0,
    @SerializedName("production_code")
    var production_code: String = "",
    @SerializedName("runtime")
    var runtime: Int = 0,
    @SerializedName("season_number")
    var season_number: Int = 0,
    @SerializedName("show_id")
    var show_id: Int = 0,
    @SerializedName("still_path")
    var still_path: String = "",
)

data class NetworkMediaTvDto(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("logo_path")
    var logo_path: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("origin_country")
    var origin_country: String = "",
)

data class CountryMediaTvDto(
    @SerializedName("iso_3166_1")
    var iso_3166_1: String = "",
    @SerializedName("name")
    var name: String = "",
)

data class SeasonMediaTvDto(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("air_date")
    var air_date: String = "",
    @SerializedName("season_number")
    var season_number: Int = 0,
    @SerializedName("episode_count")
    var episode_count: Int = 0,
    @SerializedName("poster_path")
    var poster_path: String = "",
)