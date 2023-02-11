package dev.mslalith.common.shapes

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import dev.mslalith.common.shared.FourierSettings
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

    ShapeDrawingCanvas(
        modifier = modifier,
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
}
