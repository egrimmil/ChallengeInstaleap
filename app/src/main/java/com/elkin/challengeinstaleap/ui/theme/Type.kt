package com.elkin.challengeinstaleap.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.elkin.challengeinstaleap.R

val fonts = FontFamily(
    Font(R.font.montserrat_light, weight = FontWeight.Light),
    Font(R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold),
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

)