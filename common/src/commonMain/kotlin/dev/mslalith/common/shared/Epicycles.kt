package dev.mslalith.common.shared

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import dev.mslalith.common.utils.PI
import kotlin.math.cos
import kotlin.math.sin

fun DrawScope.epicycles(
    numberOfCircles: Int,
    radius: Float,
    time: Float,
    center: Offset,
    showEpicycleCenter: Boolean,
    onEpicycleEnd: (Offset) -> Unit
) {
    val angleInRadians = Math.toRadians(359.0 * time).toFloat()
    var previousCenter = center

    repeat(numberOfCircles) {
        // Formula reference: https://upload.wikimedia.org/wikipedia/commons/b/bd/Fourier_series_square_wave_circles_animation.svg
        val circleCount = (it * 2) + 1
        val theta = circleCount * angleInRadians
        val multiplier = radius * (4f / (circleCount * PI))
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

        if (showEpicycleCenter) {
            drawCircle(
                color = Color.Red,
                radius = 4f,
                center = dotCenter
            )
        }

        previousCenter = dotCenter
    }
    onEpicycleEnd(previousCenter)
}

fun DrawScope.epicycles(
    series: List<FourierItem>,
    time: Float,
    center: Offset,
    showEpicycleCenter: Boolean,
    onEpicycleEnd: (Offset) -> Unit
) {
    var offset = center.copy(y = size.height * .5f)

    series.forEach { item ->
        val previousOffset = offset
        val frequency = item.frequency
        val radius = item.complexNumber.amplitude
        val phase = item.complexNumber.phase
        val theta = (frequency * time) + phase
        val x = radius * cos(theta)
        val y = radius * sin(theta)
        offset += Offset(x, y)

        drawCircle(
            color = Color.Gray.copy(alpha = 0.3f),
            radius = radius,
            center = previousOffset,
            style = Stroke(width = 4f)
        )

        drawLine(
            color = Color.Black.copy(alpha = 0.6f),
            start = previousOffset,
            end = offset,
            strokeWidth = 3f
        )

        if (showEpicycleCenter) {
            drawCircle(
                color = Color.Red,
                radius = 4f,
                center = offset
            )
        }
    }
    onEpicycleEnd(offset)
}
