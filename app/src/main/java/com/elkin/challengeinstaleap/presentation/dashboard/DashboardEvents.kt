package com.elkin.challengeinstaleap.presentation.dashboard

sealed class DashboardEvents{
    object GetTrending: DashboardEvents()
    object OnNavigateDetail: DashboardEvents()
}