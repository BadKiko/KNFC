package com.kiko.knfc.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import kotlin.random.Random

fun Color.Companion.random(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}

fun Color.Companion.randomNeutral(): Color {
    val red = Random.nextInt(from = 128, until = 200)
    val green = Random.nextInt(from = 128, until = 200)
    val blue = Random.nextInt(from = 128, until = 200)
    return Color(red, green, blue)
}

fun Color.isColorDark(): Boolean {
    return ColorUtils.calculateLuminance(this.toArgb()) < 0.5
}