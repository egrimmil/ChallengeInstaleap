package com.elkin.challengeinstaleap.data.repository

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.remote.dto.ListGenresMediaDto
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.dto.TrendingDto
import com.elkin.challengeinstaleap.data.remote.service.ApiService
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.data.remote.service.safeApiCallWithWrapperResponse
import com.elkin.challengeinstaleap.domain.repository.ListMediaRepository
import javax.inject.Inject

class ListMediaRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): ListMediaRepository {

    override suspend fun getPopularMedia(type: String): ResponseWrapper<ResponseDto<TrendingDto>> {
        val response = apiService.getPopular(type, Const.KEY)
        return safeApiCallWithWrapperResponse(response)
    }

    override suspend fun getGenres(type: String): ResponseWrapper<ResponseDto<ListGenresMediaDto>> {
        val resp = apiService.getGenres(type, Const.KEY)
        return safeApiCallWithWrapperResponse(resp)
    }
}