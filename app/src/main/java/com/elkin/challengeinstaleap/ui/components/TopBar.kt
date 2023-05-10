package com.elkin.challengeinstaleap.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.elkin.challengeinstaleap.ui.theme.transparent
import com.elkin.challengeinstaleap.ui.theme.white

@Composable
fun ToolBar(
    title: String,
    isIntern: Boolean = false,
    onActionBack: (() -> Unit?)? = null
) {
    TopAppBar(
        navigationIcon = {
            if (isIntern) {
                IconButton(onClick = { onActionBack?.invoke() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back", tint = white)
                }
            }
        },
        title = { Text(text = title, color = white) },
        elevation = 0.dp,
        actions = {}
    )
}