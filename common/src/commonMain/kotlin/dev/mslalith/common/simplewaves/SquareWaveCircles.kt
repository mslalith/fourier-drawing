package dev.mslalith.common.simplewaves

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import dev.mslalith.common.shared.epicycles

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
