package com.elkin.challengeinstaleap.presentation.dashboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.core.util.UiEvent
import com.elkin.challengeinstaleap.ui.components.FeatureMedia
import com.elkin.challengeinstaleap.ui.components.SectionCategories

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel(),
    actionMenu: (() -> Unit)? = null
) {
    val state = viewModel.state

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navController.navigate(event.route)
                }
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            FeatureMedia(
                infoMedia = state.featureItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) { idMedia ->
                viewModel.onEvent(DashboardEvents.OnNavigateDetail(idMedia))
            }
            SectionCategories(
                title = stringResource(id = R.string.movies),
                items = state.movies
            ) { id ->
                viewModel.onEvent(DashboardEvents.OnNavigateDetail(id))
            }
            SectionCategories(
                title = stringResource(id = R.string.tvs),
                items = state.tvs
            ) {
                viewModel.onEvent(DashboardEvents.OnNavigateDetail(it))
            }
        }
    }
}