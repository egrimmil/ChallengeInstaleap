package com.elkin.challengeinstaleap.domain.repository

import com.elkin.challengeinstaleap.data.remote.dto.ListGenresMediaDto
import com.elkin.challengeinstaleap.data.remote.dto.MediaGenreDto
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.dto.TrendingDto
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.domain.model.GenresMedia
import com.elkin.challengeinstaleap.domain.model.Media

interface ListMediaRepository {
    suspend fun getPopularMedia(type: String): ResponseWrapper<ResponseDto<MutableList<Media>?>>
    suspend fun getGenres(type: String): ResponseWrapper<ResponseDto<MutableList<GenresMedia>>>
}