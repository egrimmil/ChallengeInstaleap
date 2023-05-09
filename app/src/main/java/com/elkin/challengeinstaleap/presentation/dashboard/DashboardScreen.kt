package com.elkin.challengeinstaleap.presentation.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.core.util.UiEvent
import com.elkin.challengeinstaleap.ui.components.*
import com.elkin.challengeinstaleap.ui.theme.red
import com.elkin.challengeinstaleap.ui.theme.white

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
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

    Scaffold(
        topBar = {
            ToolBar("Inicio")
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { padding ->
        if (state.isLoading) {
            Loading(
                modifier = Modifier.fillMaxSize(),
                colorProgress = red,
                messageColor = white
            )
        } else if (state.isError) {
            ErrorInfo(
                modifier = Modifier.fillMaxSize(),
                message = state.message,
                messageColor = white
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                item {
                    FeatureMedia(
                        infoMedia = state.featureItem,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) { media ->
                        viewModel.onEvent(DashboardEvents.OnNavigateDetail(media))
                    }
                    SectionCategories(
                        title = stringResource(id = R.string.movies),
                        items = state.movies
                    ) { item ->
                        viewModel.onEvent(DashboardEvents.OnNavigateDetail(item))
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
    }
}