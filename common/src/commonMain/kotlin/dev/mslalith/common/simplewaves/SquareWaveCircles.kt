package dev.mslalith.common.simplewaves

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import dev.mslalith.common.shared.epicycles
import dev.mslalith.common.utils.extensions.fillMaxWidth
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map

@Composable
fun SimpleWaves(
    modifier: Modifier = Modifier,
    settings: SimpleWaveSettings,
    radius: Float = 180f
) {
    val animatable = remember { Animatable(initialValue = 0f) }

    // TODO: to be revisited
    LaunchedEffect(key1 = settings.cycleDuration) {
        animatable.stop()
        snapshotFlow { settings.cycleDuration }.drop(count = 1).map { newDuration ->
            val transitionDuration = lerp(
                start = Offset(x = 0f, y = 0f),
                stop = Offset(x = settings.cycleDuration.toFloat(), y = 0f),
                fraction = animatable.value
            ).x.toInt()
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = settings.cycleDuration - transitionDuration, easing = LinearEasing)
            )
        }
        while (true) {
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = settings.cycleDuration, easing = LinearEasing)
            )
            animatable.snapTo(targetValue = 0f)
        }
    }
    val time = animatable.value

    Row(modifier = modifier) {
        SimpleWavesCanvas(
            modifier = Modifier.weight(weight = 1f).fillMaxHeight(),
            settings = settings,
            radius = radius,
            time = time
        )
        SimpleWaveSettingsPanel(
            modifier = Modifier.fillMaxWidth(
                fraction = .3f,
                minAllowedSize = 360.dp,
                maxAllowedSize = 600.dp
            ).fillMaxHeight(),
            settings = settings,
        )
    }
}

@Composable
private fun SimpleWavesCanvas(
    modifier: Modifier = Modifier,
    settings: SimpleWaveSettings,
    radius: Float,
    time: Float
) {
    Canvas(modifier = modifier) {
        val xCircleCenterOffset = size.width * settings.circlesCenterPercent
        val xWaveStartOffset = size.width * settings.waveStartPercent
        epicycles(
            numberOfCircles = settings.numberOfCircles,
            radius = radius,
            time = time,
            center = Offset(x = xCircleCenterOffset, y = size.height * 0.5f),
            showEpicycleCenter = settings.showEpicycleCenter,
            onEpicycleEnd = { wavePoint ->
                settings.addShapePoint(wavePoint)
                drawLine(
                    color = Color.Blue,
                    start = wavePoint,
                    end = Offset(x = xWaveStartOffset, y = wavePoint.y),
                    strokeWidth = 2f
                )
            }
        )


        drawPath(
            path = settings.shapePathContinuous(startOffset = xWaveStartOffset),
            color = Color.Blue,
            style = Stroke(width = 4f)
        )
    }
}
