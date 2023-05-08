package com.elkin.challengeinstaleap.data.remote.service

import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.dto.TrendingDto
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/trending/all/day")
    suspend fun getTrending(@Query("api_key") key: String): ResponseDto<TrendingDto?>
}