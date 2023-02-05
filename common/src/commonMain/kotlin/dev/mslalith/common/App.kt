package dev.mslalith.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.unit.dp
import dev.mslalith.common.extensions.fillMaxWidth
import dev.mslalith.common.simplewaves.SimpleWaveSettings
import dev.mslalith.common.simplewaves.SimpleWaveSettingsPanel
import dev.mslalith.common.simplewaves.SimpleWaves
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onEach

@Composable
fun App() {
    var settings by remember { mutableStateOf(SimpleWaveSettings()) }

//    val infiniteTransition = rememberInfiniteTransition()
//    val time by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 1f,
//        animationSpec = InfiniteRepeatableSpec(
//            animation = tween(
//                durationMillis = settings.cycleDuration,
//                easing = LinearEasing
//            )
//        )
//    )
    val animatable = remember { Animatable(initialValue = 0f) }
    var changed = remember { false }
    LaunchedEffect(key1 = settings) {
        snapshotFlow { settings.cycleDuration }.drop(count = 1).onEach { changed = true }
    }
    LaunchedEffect(key1 = settings.cycleDuration) {
        animatable.stop()
        if (changed) {
            val transitionDuration = lerp(
                start = Offset(x = 0f, y = 0f),
                stop = Offset(x = settings.cycleDuration.toFloat(), y = 0f),
                fraction = animatable.value
            ).x.toInt()
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = settings.cycleDuration - transitionDuration, easing = LinearEasing)
            )
            changed = false
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


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row {
            SimpleWaves(
                modifier = Modifier.weight(weight = 1f).fillMaxHeight(),
                settings = settings,
                time = time,
            )
            SimpleWaveSettingsPanel(
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
