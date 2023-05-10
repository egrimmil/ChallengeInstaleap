package com.elkin.challengeinstaleap.domain.repository

import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.data.remote.dto.TrendingDto
import com.elkin.challengeinstaleap.domain.model.Media

interface DashboardRepository {
    suspend fun getTrending(): ResponseWrapper<ResponseDto<MutableList<Media>?>>
}