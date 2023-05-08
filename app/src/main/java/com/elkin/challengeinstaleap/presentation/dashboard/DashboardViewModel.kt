package com.elkin.challengeinstaleap.presentation.dashboard

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.core.util.Resource
import com.elkin.challengeinstaleap.core.util.UiEvent
import com.elkin.challengeinstaleap.domain.use_case.DashboardUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardUseCase: DashboardUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val context get() = getApplication<Application>()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(DashboardState())
        private set

    init {
        onEvent(DashboardEvents.GetTrending)
    }

    private fun onEvent(events: DashboardEvents) {
        when (events) {
            is DashboardEvents.GetTrending -> {
                viewModelScope.launch {
                    val resp = dashboardUseCase.getTrending()
                    resp.collect { trends ->
                        Log.e("response_trendsVM", Gson().toJson(trends.data))
                        when (trends) {
                            is Resource.Loading -> state = state.copy(isLoading = true)
                            is Resource.Error -> {
                                state = trends.message?.let { it }
                                    ?.let {
                                        state.copy(
                                            isLoading = false,
                                            isError = true,
                                            message = it
                                        )
                                    }!!
                            }
                            is Resource.Success -> {
                                state = if (!trends.data.isNullOrEmpty()) {
                                    state.copy(isLoading = false, trends = trends.data)
                                } else {
                                    state.copy(
                                        isLoading = false,
                                        isError = true,
                                        message = context.getString(
                                            R.string.error_server
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
            is DashboardEvents.OnNavigateDetail -> {}
        }
    }
}