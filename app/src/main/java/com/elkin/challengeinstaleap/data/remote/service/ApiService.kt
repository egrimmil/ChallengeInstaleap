package com.elkin.challengeinstaleap.data.remote.service

import com.elkin.challengeinstaleap.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("trending/all/day")
    suspend fun getTrending(@Query("api_key") key: String): ResponseDto<TrendingDto?>

    @GET("movie/{idMovie}")
    suspend fun getDetailMovie(
        @Path("idMovie") idMovie: String,
        @Query("api_key") key: String
    ): ResponseDto<DetailMediaMovieDto>

    @GET("tv/{idTv}")
    suspend fun getDetailTv(
        @Path("idTv") idTv: String,
        @Query("api_key") key: String
    ): ResponseDto<DetailMediaTvDto>

    @GET("{type}/popular")
    suspend fun getPopular(
        @Path("type") type: String,
        @Query("api_key") key: String
    ): ResponseDto<TrendingDto>

    @GET("genre/{type}/list")
    suspend fun getGenres(
        @Path("type") type: String,
        @Query("api_key") key: String
    ): ResponseDto<ListGenresMediaDto>
}