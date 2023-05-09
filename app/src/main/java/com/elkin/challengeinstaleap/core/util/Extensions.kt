package com.elkin.challengeinstaleap.core.util

import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.capitalizeName(): String {
    return this.split(" ").joinToString(" ") { text ->
        text.lowercase().replaceFirstChar { it.uppercase() }
    }
}

fun Int.toTimeMovie(): String{
    var duration: String
    var sdf = SimpleDateFormat("mm", Locale.getDefault())
    try {
        val dt = sdf.parse(this.toString())
        sdf = SimpleDateFormat("HH'h' mm'm'", Locale.getDefault())
        duration = sdf.format(dt!!)
    } catch (e: ParseException) {
        duration = ""
    }
    return duration
}

fun Double.toTwoDecimals(): String{
    val df = DecimalFormat("#.##")
    return df.format(this)
}