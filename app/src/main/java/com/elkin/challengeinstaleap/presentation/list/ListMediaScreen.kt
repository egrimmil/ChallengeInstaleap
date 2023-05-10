package com.elkin.challengeinstaleap.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.core.util.UiEvent
import com.elkin.challengeinstaleap.domain.model.GenresMedia
import com.elkin.challengeinstaleap.presentation.detail.DetailEvents
import com.elkin.challengeinstaleap.ui.components.*
import com.elkin.challengeinstaleap.ui.theme.fonts
import com.elkin.challengeinstaleap.ui.theme.red
import com.elkin.challengeinstaleap.ui.theme.white

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListMediaScreen(
    isMovie: Boolean,
    navController: NavController,
    viewModel: ListMediaViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateUp -> {
                    navController.navigateUp()
                }
                is UiEvent.Navigate -> {
                    navController.navigate(event.route)
                }
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    viewModel.onEvent(
                        ListMediaEvents.OnLoadPopular(isMovie)
                    )
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        topBar = {
            ToolBar("", isIntern = true){
                viewModel.onEvent(ListMediaEvents.OnNavigateUp)
            }
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
                    .fillMaxSize()
                    .padding(padding)
            ) {
                item {
                    FeatureMedia(
                        infoMedia = state.popularFirst,
                        typeVisible = false,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) { media ->
                        viewModel.onEvent(ListMediaEvents.OnNavigateDetail(media))
                    }
                    if (state.genres.isEmpty() || state.isErrorGenres){
                        Text(
                            text = stringResource(id = R.string.error_load_genres),
                            color = white,
                            fontFamily = fonts,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp
                        )
                    } else {
                        SelectorDropdown(state.genres.map { it.name }) { name ->
                            val genre = state.genres.first { it.name == name }
                            viewModel.onEvent(ListMediaEvents.OnLoadByGenre(genre.id))
                        }
                    }
                }
                item {
                    Column(
                        Modifier
                            .padding(dimensionResource(id = R.dimen.dp_12))
                    ) {
                        Text(
                            text = state.message,
                            fontFamily = fonts,
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp
                        )
                        if(state.listMedia.size > 0) {
                            FlowRow(
                                maxItemsInEachRow = 3
                            ) {
                                state.listMedia.forEach { media ->
                                    ItemMedia(
                                        imgItem = media.poster_path,
                                        item = media,
                                        modifier = Modifier
                                            .height(dimensionResource(id = R.dimen.dp_180))
                                            .width(dimensionResource(id = R.dimen.dp_120)),
                                    ) {
                                        viewModel.onEvent(ListMediaEvents.OnNavigateDetail(media))
                                    }
                                }
                            }
                        } else {
                            ErrorInfo(
                                modifier = Modifier.fillMaxWidth(),
                                message = stringResource(id = R.string.error_load_list_by_genres),
                                messageColor = white
                            )
                        }
                    }
                }
            }
        }
    }
}