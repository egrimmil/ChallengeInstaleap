package com.elkin.challengeinstaleap.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elkin.challengeinstaleap.presentation.dashboard.DashboardScreen
import com.elkin.challengeinstaleap.presentation.splash.SplashScreen
import com.elkin.challengeinstaleap.ui.navigation.Route
import com.elkin.challengeinstaleap.ui.theme.ChallengeInstaleapTheme
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
                    val scaffoldState = rememberScaffoldState()

                    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                        NavHost(
                            navController = navController,
                            startDestination = Route.SPLASH,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            composable(Route.SPLASH){
                                SplashScreen(navController = navController)
                            }
                            composable(Route.DASHBOARD){
                                DashboardScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}