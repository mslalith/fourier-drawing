package dev.mslalith.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import dev.mslalith.common.extensions.fillMaxWidth
import dev.mslalith.common.shapes.ShapeDrawing
import dev.mslalith.common.simplewaves.SimpleWaveSettings
import dev.mslalith.common.simplewaves.SimpleWaveSettingsPanel

@Composable
fun App() {
    val settings = remember { SimpleWaveSettings() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row {
//            SimpleWaves(
//                modifier = Modifier.weight(weight = 1f).fillMaxHeight(),
//                settings = settings,
//            )
            ShapeDrawing(
                modifier = Modifier.weight(weight = 1f).fillMaxHeight().onSizeChanged { settings.clearShapePoints() },
                settings = settings,
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
}
