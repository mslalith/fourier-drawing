package dev.mslalith.common

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mslalith.common.extensions.fillMaxWidth
import dev.mslalith.common.squareWaveCircles.SquareWaveCircles
import dev.mslalith.common.squareWaveCircles.SquareWaveCirclesSettings
import dev.mslalith.common.squareWaveCircles.SquareWaveCirclesSettingsPanel

@Composable
fun App() {
    val infiniteTransition = rememberInfiniteTransition()
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(
                durationMillis = 6_000,
                easing = LinearEasing
            )
        )
    )

    var settings by remember { mutableStateOf(SquareWaveCirclesSettings()) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row {
            SquareWaveCircles(
                modifier = Modifier.weight(weight = 1f).fillMaxHeight(),
                settings = settings,
                time = time,
            )
            SquareWaveCirclesSettingsPanel(
                modifier = Modifier.fillMaxWidth(
                    fraction = .3f,
                    minAllowedSize = 360.dp,
                    maxAllowedSize = 600.dp
                ).fillMaxHeight(),
                settings = settings,
                onSettingsUpdate = { settings = it }
            )
        }
    }
}
