package com.elkin.challengeinstaleap.domain.use_case

import com.elkin.challengeinstaleap.core.util.Resource
import com.elkin.challengeinstaleap.data.remote.service.ResponseWrapper
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {
    suspend fun getTrending(): Flow<Resource<out MutableList<Media>>> = flow {
        emit(Resource.Loading(loading = true))
        val trends: MutableList<Media>

        when (val resp = dashboardRepository.getTrending()) {
            is ResponseWrapper.NetworkError -> {
                emit(Resource.Loading(loading = false))
                emit(Resource.Error(message = resp.message))
            }
            is ResponseWrapper.ServerError -> {
                emit(Resource.Loading(loading = false))
                emit(Resource.Error(message = resp.message))
            }
            is ResponseWrapper.Success -> {
                if (resp.data.result != null) {
                    trends = resp.data.result ?: mutableListOf()
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Success(data = trends))
                } else {
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Success(data = mutableListOf()))
                }
            }
        }
    }
}