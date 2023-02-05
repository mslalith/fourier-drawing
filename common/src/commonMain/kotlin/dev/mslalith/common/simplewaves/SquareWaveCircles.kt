package dev.mslalith.common.simplewaves

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SimpleWaves(
    modifier: Modifier = Modifier,
    settings: SimpleWaveSettings,
    time: Float
) {
    val radius = 180f
    Canvas(modifier = modifier) {
        val xCircleCenterOffset = size.width * settings.circlesCenterPercent
        val xWaveStartOffset = size.width * settings.waveStartPercent
        epicycles(
            numberOfCircles = settings.numberOfCircles,
            radius = radius,
            time = time,
            center = Offset(x = xCircleCenterOffset, y = size.height * 0.5f),
            addWavePoint = { wavePoint ->
                settings.addWavePoint(wavePoint)
                drawLine(
                    color = Color.Blue,
                    start = wavePoint,
                    end = Offset(x = xWaveStartOffset, y = wavePoint.y),
                    strokeWidth = 2f
                )
            }
        )


        drawPath(
            path = settings.wavePoints.toWavePath(startOffset = xWaveStartOffset),
            color = Color.Blue,
            style = Stroke(width = 4f)
        )
    }
}

private fun List<Offset>.toWavePath(startOffset: Float): Path = Path().apply {
    firstOrNull()?.let { moveTo(startOffset, it.y) }
    drop(n = 1).forEachIndexed { index, wavePoint ->
        lineTo(x = startOffset + index, y = wavePoint.y)
    }
}

private fun DrawScope.epicycles(
    numberOfCircles: Int = 1,
    radius: Float,
    time: Float,
    center: Offset,
    addWavePoint: (Offset) -> Unit
) {
    val angleInRadians = Math.toRadians(359.0 * time).toFloat()
    var previousCenter = center

    repeat(numberOfCircles) {
        val circleCount = (it * 2) + 1
        val theta = circleCount * angleInRadians
        val multiplier = radius * (4f / (circleCount * Math.PI.toFloat()))
        val x = multiplier * cos(theta)
        val y = multiplier * sin(theta)
        val dotCenter = previousCenter + Offset(x, y)

        drawCircle(
            color = Color.Gray.copy(alpha = 0.3f),
            radius = multiplier,
            center = previousCenter,
            style = Stroke(width = 4f)
        )

        drawLine(
            color = Color.Black.copy(alpha = 0.6f),
            start = previousCenter,
            end = dotCenter,
            strokeWidth = 3f
        )

        drawCircle(
            color = Color.Red,
            radius = 4f,
            center = dotCenter
        )

        previousCenter = dotCenter
    }
    addWavePoint(previousCenter)
}
