package com.elkin.challengeinstaleap.presentation.dashboard

import com.elkin.challengeinstaleap.domain.model.Media

data class DashboardState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val message: String = "",
    val trends: MutableList<Media> = mutableListOf(),
    val featureItem: Media = Media(),
    val movies: List<Media> = mutableListOf(),
    val tvs: List<Media> = mutableListOf()
)
