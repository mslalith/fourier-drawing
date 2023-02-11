package dev.mslalith.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import dev.mslalith.common.shapes.ShapeDrawing
import dev.mslalith.common.simplewaves.SimpleWaveSettings

@Composable
fun App() {
    val settings = remember { SimpleWaveSettings() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
//        SimpleWaves(
//            modifier = Modifier.fillMaxSize(),
//            settings = settings,
//        )
        ShapeDrawing(
            settings = settings,
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { settings.clearShapePoints() },
        )
    }
}
