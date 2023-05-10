package com.elkin.challengeinstaleap.data.repository

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.remote.dto.DetailMediaMovieDto
import com.elkin.challengeinstaleap.data.remote.dto.DetailMediaTvDto
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.service.ApiService
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.data.remote.service.safeApiCallWithWrapperResponse
import com.elkin.challengeinstaleap.domain.repository.DetailMediaRepository
import javax.inject.Inject

class DetailMediaRepositoryImpl @Inject constructor(
    private val service: ApiService
) : DetailMediaRepository {
    override suspend fun getDetailMovie(id: String): ResponseWrapper<ResponseDto<DetailMediaMovieDto>> {
        val response = service.getDetailMovie(id, Const.KEY)
        return safeApiCallWithWrapperResponse(response)
    }

    override suspend fun getDetailTv(id: String): ResponseWrapper<ResponseDto<DetailMediaTvDto>> {
        val response = service.getDetailTv(id, Const.KEY)
        return safeApiCallWithWrapperResponse(response)
    }
}