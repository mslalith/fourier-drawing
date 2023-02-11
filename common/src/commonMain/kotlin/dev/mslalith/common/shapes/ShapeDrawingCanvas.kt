package dev.mslalith.common.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import dev.mslalith.common.extensions.discreteFourierTransformOf
import dev.mslalith.common.shared.FourierSettings
import dev.mslalith.common.shared.epicycles
import dev.mslalith.common.utils.TWO_PI

//val piDft = discreteFourierTransformOf(series = piShape)
//val piDft = discreteFourierTransformOf(series = eighthNote)
//val piDft = discreteFourierTransformOf(series = musicNote)
val piDft = discreteFourierTransformOf(series = pikachuShort)

@Composable
fun ShapeDrawingCanvas(
    modifier: Modifier = Modifier,
    settings: FourierSettings,
    time: Float,
    updateTime: (Float) -> Unit
) {
    Canvas(modifier = modifier) {
        val xCircleCenterOffset = size.width * settings.circlesCenterPercent
        epicycles(
            series = piDft,
            time = time,
            center = Offset(x = xCircleCenterOffset, y = size.height * 0.5f),
            showEpicycleCenter = settings.showEpicycleCenter,
            onEpicycleEnd = { point ->
                settings.addShapePoint(point)
                val delta = TWO_PI / piDft.size
                val newTime = time + delta
                updateTime(if (newTime >= TWO_PI) 0f else newTime)
            }
        )


        drawPath(
            path = settings.shapePathExact(),
            color = Color.Blue,
            style = Stroke(width = 4f)
        )
    }
}
