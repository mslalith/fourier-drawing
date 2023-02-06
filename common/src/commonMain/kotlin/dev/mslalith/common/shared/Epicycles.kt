package dev.mslalith.common.shared

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
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
