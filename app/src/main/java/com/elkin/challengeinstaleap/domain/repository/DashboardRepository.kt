package com.elkin.challengeinstaleap.domain.repository

import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.data.remote.dto.TrendingDto

interface DashboardRepository {
    suspend fun getTrending(): ResponseWrapper<ResponseDto<TrendingDto?>>
}