package com.elkin.challengeinstaleap.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.core.util.UiEvent
import com.elkin.challengeinstaleap.core.util.toTimeMovie
import com.elkin.challengeinstaleap.core.util.toTwoDecimals
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.ui.components.ItemMedia
import com.elkin.challengeinstaleap.ui.theme.fonts
import com.elkin.challengeinstaleap.ui.theme.green

@Composable
fun DetailScreen(
    item: Media,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateUp -> {
                    navController.navigateUp()
                }
                else -> Unit
            }
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    viewModel.onEvent(
                        DetailEvents.OnLoadService(item)
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

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            ItemMedia(
                imgItem = state.mediaInfo.imgDetail,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                enableClick = false
            ) {}
            Column(
                Modifier
                    .padding(dimensionResource(id = R.dimen.dp_12))
            ) {
                Text(
                    text = state.mediaInfo.name,
                    fontFamily = fonts,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.dp_4), bottom = dimensionResource(id = R.dimen.dp_4)),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = state.mediaInfo.dateRelease,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                    Text(
                        text = if (state.mediaInfo.isMovie)
                            state.mediaInfo.runtime.toTimeMovie()
                        else
                            state.mediaInfo.seasons.size.toString() + " " + if(state.mediaInfo.seasons.size > 1)
                                stringResource(id = R.string.season)
                            else
                                stringResource(id = R.string.seasons),
                        fontFamily = fonts,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                    Text(
                        text = state.mediaInfo.votes.toTwoDecimals(),
                        color = green,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                }
                Column(
                    Modifier.padding(dimensionResource(id = R.dimen.dp_4))
                ) {
                    Text(
                        text = state.mediaInfo.overview,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}