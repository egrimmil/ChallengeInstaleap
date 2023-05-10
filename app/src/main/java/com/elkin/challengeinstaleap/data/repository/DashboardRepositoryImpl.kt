package com.elkin.challengeinstaleap.data.repository

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.service.ApiService
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.data.remote.dto.TrendingDto
import com.elkin.challengeinstaleap.data.remote.service.safeApiCallWithWrapperResponse
import com.elkin.challengeinstaleap.domain.repository.DashboardRepository
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val service: ApiService
) : DashboardRepository {
    override suspend fun getTrending(): ResponseWrapper<ResponseDto<TrendingDto?>> {
        val response = service.getTrending(Const.KEY)
        return safeApiCallWithWrapperResponse(response)
    }
}