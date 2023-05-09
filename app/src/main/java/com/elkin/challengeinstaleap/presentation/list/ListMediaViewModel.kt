package com.elkin.challengeinstaleap.presentation.list

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkin.challengeinstaleap.core.util.Resource
import com.elkin.challengeinstaleap.core.util.UiEvent
import com.elkin.challengeinstaleap.domain.model.GenresMedia
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.domain.use_case.ListMediaUseCase
import com.elkin.challengeinstaleap.ui.navigation.Route
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMediaViewModel @Inject constructor(
    private val listMediaUseCase: ListMediaUseCase
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(ListMediaState())
        private set

    fun onEvent(events: ListMediaEvents) {
        when (events) {
            is ListMediaEvents.OnLoadPopular -> {
                getPopularMedia(events.isMovies)
                getGenres(events.isMovies)
            }
            is ListMediaEvents.OnLoadByGenre -> getListByGenre(events.idGenre)
            is ListMediaEvents.OnNavigateDetail -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            Route.DETAIL_MEDIA.replace(
                                "{item}",
                                Uri.encode(Gson().toJson(events.item))
                            )
                        )
                    )
                }
            }
            is ListMediaEvents.OnNavigateUp -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateUp)
                }
            }
        }
    }

    private fun getPopularMedia(isMovie: Boolean) {
        viewModelScope.launch {
            val type = if(isMovie) "movie" else "tv"
            val resp = listMediaUseCase.getPopularMedia(type)
            resp.collect { popular ->
                Log.e("response_ListPopularVM", Gson().toJson(popular.data))
                when (popular) {
                    is Resource.Loading -> state = state.copy(isLoading = true)
                    is Resource.Error -> {
                        state = popular.message?.let {
                            state.copy(
                                isLoading = false,
                                isError = true,
                                message = it
                            )
                        }!!
                    }
                    is Resource.Success -> {
                        popular.data!!.forEach { it.media_type =type}
                        state = state.copy(
                            isLoading = false,
                            message = if (isMovie) "Películas" else "Series de TV",
                            popularFirst =  popular.data[0],
                            listMedia = popular.data,
                            listMediaOrigin = popular.data
                        )
                    }
                }
            }
        }
    }

    private fun getGenres(isMovie: Boolean) {
        viewModelScope.launch {
            val resp = listMediaUseCase.getGenres(if (isMovie) "movie" else "tv")
            resp.collect { genres ->
                Log.e("response_LisGenresVM", Gson().toJson(genres.data))
                when (genres) {
                    is Resource.Loading -> state = state.copy(isLoading = false)
                    is Resource.Error -> {
                        state = genres.message?.let {
                            state.copy(
                                isLoading = false,
                                isErrorGenres = true,
                                message = it
                            )
                        }!!
                    }
                    is Resource.Success -> {
                        val list = genres.data!!
                        list.sortBy { it.name }
                        list.add(0, GenresMedia(id = 0, name = "All"))
                        state = state.copy(
                                isLoading = false,
                                genres = list
                            )
                    }
                }
            }
        }
    }

    private fun getListByGenre(idGenre: Int) {
        var list = mutableListOf<Media>()
        if (idGenre == 0){
            list = state.listMediaOrigin
        }else {
            state.listMediaOrigin.forEach { item ->
                item.genre_ids.forEach {
                    if (it == idGenre) {
                        list.add(item)
                    }
                }
            }
        }
        Log.e("listMediaGenre", Gson().toJson(list))
        state = state.copy(
            isLoading = false,
            message = state.genres.first { it.id == idGenre }.name,
            listMedia = list
        )
    }
}