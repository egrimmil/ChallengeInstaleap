package com.elkin.challengeinstaleap.data.repository

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.local.dao.MediaDao
import com.elkin.challengeinstaleap.data.mapper.toMedia
import com.elkin.challengeinstaleap.data.mapper.toMediaEntity
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.service.ApiService
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.data.remote.service.safeApiCallWithWrapperResponse
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.domain.repository.DashboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val mediaDao: MediaDao
) : DashboardRepository {
    override suspend fun getTrending(): ResponseWrapper<ResponseDto<MutableList<Media>?>> {
        val responseRemote = service.getTrending(Const.KEY)
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
            val responseLocal = mediaDao.getAll()
            if (responseLocal.isNotEmpty()) {
                val resp = responseLocal.map { it.toMedia() }.toMutableList()
                ResponseDto(200, "", resp)
            } else {
                ResponseDto(responseRemote.error_code, responseRemote.message, null)
            }
        }
        return safeApiCallWithWrapperResponse(response)
    }
}