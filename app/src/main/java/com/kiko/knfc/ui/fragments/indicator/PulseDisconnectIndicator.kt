package com.kiko.knfc.ui.fragments.indicator

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PulseDisconnectIndicator(
    size: Float,
    color: Color = MaterialTheme.colorScheme.tertiaryContainer,
    content: @Composable () -> Unit
) {
    val animatedColor = remember { Animatable(color) }
    val animatedFloat = remember { Animatable(size * 1.5f) }

    LaunchedEffect(animatedFloat) {
        delay(200) // to avoid repeated delays

        launch {
            animatedFloat.animateTo(
                targetValue = size, animationSpec = tween(700, easing = FastOutSlowInEasing)
            )
        }

        launch {
            animatedColor.animateTo(color.copy(0.2f), animationSpec = tween(700))
        }
    }


    Box(
        modifier = Modifier
            .width(size.dp * 2)
            .height(size.dp * 1.3f)
            .background(
                Brush.radialGradient(
                    listOf(
                        animatedColor.value, Color.Transparent
                    ), radius = animatedFloat.value
                )
            ), contentAlignment = Alignment.Center
    ) {
        content()
    }
}