package com.elkin.challengeinstaleap.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elkin.challengeinstaleap.domain.model.Media
import com.elkin.challengeinstaleap.presentation.dashboard.DashboardScreen
import com.elkin.challengeinstaleap.presentation.detail.DetailScreen
import com.elkin.challengeinstaleap.presentation.list.ListMediaScreen
import com.elkin.challengeinstaleap.presentation.splash.SplashScreen
import com.elkin.challengeinstaleap.ui.navigation.Route
import com.elkin.challengeinstaleap.ui.theme.ChallengeInstaleapTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeInstaleapTheme(true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                        NavHost(
                            navController = navController,
                            startDestination = Route.SPLASH,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            composable(Route.SPLASH) {
                                SplashScreen(navController = navController)
                            }
                            composable(Route.DASHBOARD) {
                                DashboardScreen(navController = navController)
                            }
                            composable(Route.DETAIL_MEDIA) { backStackEntry ->
                                backStackEntry.arguments?.getString("item")
                                    ?.let { jsonString ->
                                        DetailScreen(
                                            Gson().fromJson(
                                                jsonString,
                                                Media::class.java
                                            ),
                                            navController = navController
                                        )
                                    }
                            }
                            composable(Route.LIST_MEDIA){ backStackEntry ->
                                backStackEntry.arguments?.getString("isMovie")
                                    ?.let{
                                        ListMediaScreen(it.toBoolean(), navController)
                                    }
                            }
                        }
                    }
                }
            }
        }
    }
}