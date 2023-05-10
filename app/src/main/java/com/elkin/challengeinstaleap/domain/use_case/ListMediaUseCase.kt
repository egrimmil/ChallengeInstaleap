package com.elkin.challengeinstaleap.domain.use_case

import android.util.Log
import com.elkin.challengeinstaleap.core.util.Resource
import com.elkin.challengeinstaleap.data.mapper.toGenresMedia
import com.elkin.challengeinstaleap.data.mapper.toMedia
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.domain.model.GenresMedia
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.domain.repository.ListMediaRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListMediaUseCase @Inject constructor(
    private val listMediaRepository: ListMediaRepository
) {
    suspend fun getPopularMedia(type: String): Flow<Resource<out MutableList<Media>>> =
        flow {
            emit(Resource.Loading(loading = true))
            val listPopular: MutableList<Media>

            when (val resp = listMediaRepository.getPopularMedia(type)) {
                is ResponseWrapper.NetworkError -> {
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Error(message = resp.message))
                }
                is ResponseWrapper.ServerError -> {
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Error(message = resp.message))
                }
                is ResponseWrapper.Success -> {
                    Log.e("response_PopularUse", Gson().toJson(resp.data))
                    if (resp.data.result != null) {
                        listPopular = resp.data.result!!.results.map { it.toMedia() }.toMutableList()
                        emit(Resource.Loading(loading = false))
                        emit(Resource.Success(data = listPopular))
                    } else {
                        emit(Resource.Loading(loading = false))
                        emit(Resource.Success(data = mutableListOf()))
                    }
                }
            }
        }

    suspend fun getGenres(type: String): Flow<Resource<out MutableList<GenresMedia>>> =
        flow {
            emit(Resource.Loading(loading = true))
            val listGenre: MutableList<GenresMedia>
            when(val resp = listMediaRepository.getGenres(type)){
                is ResponseWrapper.NetworkError -> {
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Error(message = resp.message))
                }
                is ResponseWrapper.ServerError -> {
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Error(message = resp.message))
                }
                is ResponseWrapper.Success -> {
                    Log.e("response_GenresUse", Gson().toJson(resp.data))
                    if (resp.data.result != null) {
                        listGenre = resp.data.result!!.genres.map{ it.toGenresMedia() }.toMutableList()
                        emit(Resource.Loading(loading = false))
                        emit(Resource.Success(data = listGenre))
                    } else {
                        emit(Resource.Loading(loading = false))
                        emit(Resource.Success(data = mutableListOf()))
                    }
                }
            }
        }
}