package com.elkin.challengeinstaleap.data.repository

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.local.dao.GenresDao
import com.elkin.challengeinstaleap.data.local.dao.MediaDao
import com.elkin.challengeinstaleap.data.mapper.toGenresMedia
import com.elkin.challengeinstaleap.data.mapper.toMedia
import com.elkin.challengeinstaleap.data.mapper.toMediaEntity
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.service.ApiService
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.data.remote.service.safeApiCallWithWrapperResponse
import com.elkin.challengeinstaleap.domain.model.GenresMedia
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.domain.repository.ListMediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

class ListMediaRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mediaDao: MediaDao,
    private val genresDao: GenresDao
): ListMediaRepository {

    override suspend fun getPopularMedia(type: String): ResponseWrapper<ResponseDto<MutableList<Media>?>> {
        val responseRemote = apiService.getPopular(type, Const.KEY)
        val response: ResponseDto<MutableList<Media>?> = if (responseRemote.result != null) {
            withContext(Dispatchers.Default) {
                responseRemote.result!!.results.forEach {
                    it.run {
                        this.copy(
                            poster_path = URL(Const.URL_IMG + it.poster_path).readBytes().toString()
                        )
                    }
                    mediaDao.insertMedia(it.toMediaEntity())
                }
            }

            val mediaList = responseRemote.result!!.results.map { it.toMedia() }.toMutableList()
            ResponseDto(responseRemote.error_code, responseRemote.message, mediaList)
        } else {
            val responseLocal = mediaDao.getAllMediaByType(type)
            if (responseLocal.isNotEmpty()) {
                val resp = responseLocal.map { it.toMedia() }.toMutableList()
                ResponseDto(200, "", resp)
            } else {
                ResponseDto(responseRemote.error_code, responseRemote.message, null)
            }
        }

        return safeApiCallWithWrapperResponse(response)
    }

    override suspend fun getGenres(type: String): ResponseWrapper<ResponseDto<MutableList<GenresMedia>>> {
        val respRemote = apiService.getGenres(type, Const.KEY)
        val response: ResponseDto<MutableList<GenresMedia>> = if (respRemote.result != null) {
            respRemote.result!!.genres.forEach {
                genresDao.insertGenres(it.toGenresMedia())
            }
            ResponseDto(respRemote.error_code, respRemote.message, respRemote.result!!.genres.map { it.toGenresMedia() }.toMutableList())
        } else {
            val respLocal = genresDao.getAllGenres()
            if (respLocal.isNotEmpty()) {
                ResponseDto(200, "", respLocal.toMutableList())
            } else {
                ResponseDto(respRemote.error_code, respRemote.message, null)
            }
        }

        return safeApiCallWithWrapperResponse(response)
    }
}