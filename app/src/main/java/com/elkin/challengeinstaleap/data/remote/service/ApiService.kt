package com.elkin.challengeinstaleap.data.remote.service

import com.elkin.challengeinstaleap.data.remote.dto.DetailMediaMovieDto
import com.elkin.challengeinstaleap.data.remote.dto.DetailMediaTvDto
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.dto.TrendingDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/trending/all/day")
    suspend fun getTrending(@Query("api_key") key: String): ResponseDto<TrendingDto?>

    @GET("3/movie/{idMovie}")
    suspend fun getDetailMovie(
        @Path("idMovie") idMovie: String,
        @Query("api_key") key: String
    ): ResponseDto<DetailMediaMovieDto>

    @GET("3/tv/{idTv}")
    suspend fun getDetailTv(
        @Path("idTv") idTv: String,
        @Query("api_key") key: String
    ): ResponseDto<DetailMediaTvDto>
}