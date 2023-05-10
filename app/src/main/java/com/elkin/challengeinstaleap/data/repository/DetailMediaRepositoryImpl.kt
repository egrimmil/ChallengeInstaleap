package com.elkin.challengeinstaleap.data.repository

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.local.dao.DetailMediaDao
import com.elkin.challengeinstaleap.data.mapper.toDetailMedia
import com.elkin.challengeinstaleap.data.mapper.toDetailMediaEntity
import com.elkin.challengeinstaleap.data.remote.dto.ResponseDto
import com.elkin.challengeinstaleap.data.remote.service.ApiService
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.data.remote.service.safeApiCallWithWrapperResponse
import com.elkin.challengeinstaleap.domain.model.DetailMedia
import com.elkin.challengeinstaleap.domain.repository.DetailMediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

class DetailMediaRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val detailMediaDao: DetailMediaDao
) : DetailMediaRepository {
    override suspend fun getDetailMovie(id: String): ResponseWrapper<ResponseDto<DetailMedia>> {
        val responseRemote = service.getDetailMovie(id, Const.KEY)
        val response: ResponseDto<DetailMedia> = if (responseRemote.result != null){
            withContext(Dispatchers.IO){
                responseRemote.result.run {
                    this!!.copy(
                        poster_path = URL(Const.URL_IMG + this.poster_path).readBytes().toString()
                    )
                    detailMediaDao.insertDetailMedia(this.toDetailMediaEntity())
                }
            }
            ResponseDto(responseRemote.error_code, responseRemote.message, responseRemote.result!!.toDetailMedia())
        } else {
            val responseLocal = detailMediaDao.getDetailMediaById(id.toInt())
            if (responseLocal != null) {
                val resp = responseLocal.toDetailMedia()
                ResponseDto(200, "", resp)
            } else {
                ResponseDto(responseRemote.error_code, responseRemote.message, null)
            }
        }

        return safeApiCallWithWrapperResponse(response)
    }

    override suspend fun getDetailTv(id: String): ResponseWrapper<ResponseDto<DetailMedia>> {
        val responseRemote = service.getDetailTv(id, Const.KEY)
        val response: ResponseDto<DetailMedia> = if (responseRemote.result != null){
            withContext(Dispatchers.IO){
                responseRemote.result.apply {
                    this!!.copy(
                        poster_path = URL(Const.URL_IMG + this.poster_path).readBytes().toString()
                    )
                    detailMediaDao.insertDetailMedia(this.toDetailMediaEntity())
                }
            }
            ResponseDto(responseRemote.error_code, responseRemote.message, responseRemote.result!!.toDetailMedia())
        } else {
            val responseLocal = detailMediaDao.getDetailMediaById(id.toInt())
            if (responseLocal != null) {
                val resp = responseLocal.toDetailMedia()
                ResponseDto(200, "", resp)
            } else {
                ResponseDto(responseRemote.error_code, responseRemote.message, null)
            }
        }

        return safeApiCallWithWrapperResponse(response)
    }
}