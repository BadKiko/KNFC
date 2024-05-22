package com.kiko.knfc.ui.fragments.indicator

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
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

@Composable
fun PulseLoadingIndicator(
    isLoading: Boolean,
    size: Float,
    color: Color = MaterialTheme.colorScheme.tertiaryContainer,
    content: @Composable () -> Unit
) {

    val animatedFloat = remember { Animatable(size) }

    LaunchedEffect(animatedFloat) {
        delay(200) // to avoid repeated delays
        if (isLoading) {
            animatedFloat.animateTo(
                targetValue = size * 1.5f, animationSpec = infiniteRepeatable(
                    animation = tween(700, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
        } else {
            animatedFloat.animateTo(
                targetValue = size * 1.5f, animationSpec = tween(700, easing = FastOutSlowInEasing)
            )
        }
    }


    Box(
        modifier = Modifier
            .width(size.dp * 2)
            .height(size.dp * 1.3f)
            .background(
                Brush.radialGradient(
                    listOf(
                        color,
                        Color.Transparent
                    ),
                    radius = animatedFloat.value
                )
            ),
        contentAlignment = Alignment.Center
    )
    {
        content()
    }
}


@Composable
fun PulseLoadingIndicator(
    startSize: Float,
    endSize: Float,
    heightIncrement: Float,
    color: Color = MaterialTheme.colorScheme.tertiaryContainer,
    content: @Composable () -> Unit
) {

    val animatedFloat = remember { Animatable(startSize) }

    LaunchedEffect(animatedFloat) {
        delay(200) // to avoid repeated delays
        animatedFloat.animateTo(
            targetValue = endSize, animationSpec = infiniteRepeatable(
                animation = tween(700, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }


    Box(
        modifier = Modifier
            .width(endSize.dp)
            .height((endSize / heightIncrement).dp)
            .background(
                Brush.radialGradient(
                    listOf(
                        color,
                        Color.Transparent
                    ),
                    radius = animatedFloat.value
                )
            ),
        contentAlignment = Alignment.Center
    )
    {
        content()
    }
}
