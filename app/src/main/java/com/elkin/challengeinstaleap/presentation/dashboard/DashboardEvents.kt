package com.elkin.challengeinstaleap.presentation.dashboard

sealed class DashboardEvents{
    object GetTrending: DashboardEvents()
    data class OnNavigateDetail(val idMedia: Int): DashboardEvents()
}