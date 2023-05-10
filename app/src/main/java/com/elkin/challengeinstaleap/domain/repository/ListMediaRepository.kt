package com.elkin.challengeinstaleap.domain.repository

import com.elkin.challengeinstaleap.data.remote.dto.ListGenresMediaDto
import com.elkin.challengeinstaleap.data.remote.dto.MediaGenreDto
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.dto.TrendingDto
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper

interface ListMediaRepository {
    suspend fun getPopularMedia(type: String): ResponseWrapper<ResponseDto<TrendingDto>>
    suspend fun getGenres(type: String): ResponseWrapper<ResponseDto<ListGenresMediaDto>>
}