package com.elkin.challengeinstaleap.presentation.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkin.challengeinstaleap.core.util.Resource
import com.elkin.challengeinstaleap.core.util.UiEvent
import com.elkin.challengeinstaleap.domain.model.DetailMedia
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.domain.use_case.DetailMediaUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailMediaUseCase: DetailMediaUseCase
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(DetailState())
        private set

    fun onEvent(events: DetailEvents) {
        when (events) {
            is DetailEvents.OnLoadService -> {
                Log.e("itemDetailVM", Gson().toJson(events.item))
                getDetail(events.item)
            }
            is DetailEvents.OnNavigateUp -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateUp)
                }
            }
        }
    }

    private fun getDetail(item: Media) {
        if (item.media_type.lowercase() != "tv") {
            getDetailMovie(item)
        } else {
            getDetailTv(item)
        }
    }

    private fun getDetailMovie(item: Media) {
        viewModelScope.launch {
            val resp = detailMediaUseCase.getDetailMovie(item)
            resp.collect { detailMovie ->
                Log.e("response_DetailVM", Gson().toJson(detailMovie.data))
                when (detailMovie) {
                    is Resource.Loading -> state = state.copy(isLoading = true)
                    is Resource.Error -> {
                        state = detailMovie.message?.let {
                            state.copy(
                                isLoading = false,
                                isError = true,
                                message = it
                            )
                        }!!
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            mediaInfo = detailMovie.data ?: DetailMedia()
                        )
                    }
                }
            }
        }
    }

    private fun getDetailTv(item: Media) {
        viewModelScope.launch {
            val resp = detailMediaUseCase.getDetailTv(item)
            resp.collect { detailTv ->
                Log.e("response_DetailVM", Gson().toJson(detailTv.data))
                when (detailTv) {
                    is Resource.Loading -> state = state.copy(isLoading = true)
                    is Resource.Error -> {
                        state = detailTv.message?.let {
                            state.copy(
                                isLoading = false,
                                isError = true,
                                message = it
                            )
                        }!!
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            mediaInfo = detailTv.data ?: DetailMedia()
                        )
                    }
                }
            }
        }
    }
}