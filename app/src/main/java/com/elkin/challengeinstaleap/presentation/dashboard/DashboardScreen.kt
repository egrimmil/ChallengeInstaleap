package com.elkin.challengeinstaleap.presentation.dashboard

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DashboardScreen(
    navController: NavController,
    actionMenu: (() -> Unit)? = null
){
    Row {
        Text(text = "Hola")
    }
}