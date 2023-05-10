package com.elkin.challengeinstaleap.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elkin.challengeinstaleap.R
import com.elkin.challengeinstaleap.ui.theme.black
import com.elkin.challengeinstaleap.ui.theme.fonts
import com.elkin.challengeinstaleap.ui.theme.red

@Composable
fun ErrorInfo(
    modifier: Modifier = Modifier,
    message: String,
    messageColor: Color,
) {
    Column(
        modifier.padding(dimensionResource(id = R.dimen.dp_12)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            painter = painterResource(id = R.drawable.ic_info),
            contentDescription = "Error Information",
            tint = red
        )
        Divider(
            Modifier.height(dimensionResource(id = R.dimen.dp_16)),
            color = black
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = message,
            color = messageColor,
            fontFamily = fonts,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}