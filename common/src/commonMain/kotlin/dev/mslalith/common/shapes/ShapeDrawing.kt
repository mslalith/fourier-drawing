package dev.mslalith.common.shapes

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import dev.mslalith.common.shared.FourierSettings
import dev.mslalith.common.shared.epicycles
import dev.mslalith.common.utils.TWO_PI
import dev.mslalith.common.utils.extensions.discreteFourierTransformOf
import dev.mslalith.common.utils.extensions.fillMaxWidth
import kotlinx.coroutines.launch

@Composable
fun ShapeDrawing(
    modifier: Modifier = Modifier,
    settings: FourierSettings
) {
    settings.maxShapePoints = 0
    val coroutineScope = rememberCoroutineScope()
    val animatable = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(key1 = settings.circlesCenterPercent) {
        settings.clearShapePoints()
    }

    Row(modifier = modifier) {
        ShapeDrawingCanvas(
            modifier = Modifier.weight(weight = 1f).fillMaxHeight(),
            settings = settings,
            time = animatable.value,
            updateTime = { targetValue ->
                coroutineScope.launch {
                    animatable.animateTo(
                        targetValue = targetValue,
                        animationSpec = tween(durationMillis = 1)
                    )
                }
            }
        )
        ShapeDrawingSettingsPanel(
            modifier = Modifier.fillMaxWidth(
                fraction = .3f,
                minAllowedSize = 360.dp,
                maxAllowedSize = 600.dp
            ).fillMaxHeight(),
            settings = settings,
        )
    }
}

//val piDft = discreteFourierTransformOf(series = piShape)
//val piDft = discreteFourierTransformOf(series = eighthNote)
//val piDft = discreteFourierTransformOf(series = musicNote)
val piDft = discreteFourierTransformOf(series = pikachuShort)

@Composable
private fun ShapeDrawingCanvas(
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
