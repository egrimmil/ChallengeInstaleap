package com.elkin.challengeinstaleap.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.core.util.UiEvent
import com.elkin.challengeinstaleap.ui.navigation.Route
import com.elkin.challengeinstaleap.ui.theme.black
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
){
    val scale = remember {
        Animatable(0.0f)
    }
    LaunchedEffect(true){
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        delay(2500)
        navController.navigate(Route.DASHBOARD){
            popUpTo(Route.SPLASH) {
                inclusive = true
            }
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(color = black)) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(dimensionResource(id = R.dimen.dp_20))
                .scale(scale.value)
        )
    }
}