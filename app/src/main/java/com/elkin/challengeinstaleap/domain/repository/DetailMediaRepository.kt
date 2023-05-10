package com.elkin.challengeinstaleap.domain.repository

import com.elkin.challengeinstaleap.data.remote.dto.DetailMediaMovieDto
import com.elkin.challengeinstaleap.data.remote.dto.DetailMediaTvDto
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper

interface DetailMediaRepository {
    suspend fun getDetailMovie(id: String): ResponseWrapper<ResponseDto<DetailMediaMovieDto>>
    suspend fun getDetailTv(id: String): ResponseWrapper<ResponseDto<DetailMediaTvDto>>
}