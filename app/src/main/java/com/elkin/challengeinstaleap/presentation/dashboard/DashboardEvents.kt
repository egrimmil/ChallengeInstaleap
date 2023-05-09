package com.elkin.challengeinstaleap.presentation.dashboard

import com.elkin.challengeinstaleap.domain.model.Media

sealed class DashboardEvents{
    object GetTrending: DashboardEvents()
    data class OnNavigateDetail(val item: Media): DashboardEvents()
}