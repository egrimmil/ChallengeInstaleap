package com.elkin.challengeinstaleap.domain.use_case

import android.util.Log
import com.elkin.challengeinstaleap.core.util.Resource
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.domain.model.DetailMedia
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.domain.repository.DetailMediaRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailMediaUseCase @Inject constructor(
    private val detailMediaRepository: DetailMediaRepository
) {
    suspend fun getDetailMovie(item: Media): Flow<Resource<out DetailMedia?>> =
        flow {
            emit(Resource.Loading(true))
            when(val resp = detailMediaRepository.getDetailMovie(item.id.toString())){
                is ResponseWrapper.NetworkError -> {
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Error(message = resp.message))
                }
                is ResponseWrapper.ServerError -> {
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Error(message = resp.message))
                }
                is ResponseWrapper.Success -> {
                    Log.e("response_detailMvUse", Gson().toJson(resp.data))
                    if (resp.data.result != null) {
                        emit(Resource.Loading(loading = false))
                        emit(Resource.Success(data = resp.data.result))
                    }else{
                        emit(Resource.Loading(loading = false))
                        emit(Resource.Success(data = DetailMedia()))
                    }
                }
            }
        }

    suspend fun getDetailTv(item: Media): Flow<Resource<out DetailMedia?>> =
        flow {
            emit(Resource.Loading(true))
            when(val resp = detailMediaRepository.getDetailTv(item.id.toString())){
                is ResponseWrapper.NetworkError -> {
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Error(message = resp.message))
                }
                is ResponseWrapper.ServerError -> {
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Error(message = resp.message))
                }
                is ResponseWrapper.Success -> {
                    Log.e("response_detailTVUse", Gson().toJson(resp.data))
                    if (resp.data.result != null) {
                        emit(Resource.Loading(loading = false))
                        emit(Resource.Success(data = resp.data.result))
                    }else{
                        emit(Resource.Loading(loading = false))
                        emit(Resource.Success(data = DetailMedia()))
                    }
                }
            }
        }
}