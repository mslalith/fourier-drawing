package dev.mslalith.common.simplewaves

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.mslalith.common.settings.FourierSettings

class SimpleWaveSettings : FourierSettings() {
    var circlesCenterPercent by mutableStateOf(0.3f)
    var waveStartPercent by mutableStateOf(0.54f)
}
