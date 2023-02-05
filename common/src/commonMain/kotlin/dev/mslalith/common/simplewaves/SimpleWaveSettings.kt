package dev.mslalith.common.simplewaves

import androidx.compose.ui.geometry.Offset

data class SimpleWaveSettings(
    val numberOfCircles: Int = 3,
    val circlesCenterPercent: Float = 0.3f,
    val waveStartPercent: Float = 0.54f,
    val maxWavePoints: Int = 450,
    private val _wavePoints: MutableList<Offset> = mutableListOf()
) {
    val wavePoints: List<Offset>
        get() = _wavePoints

    fun addWavePoint(point: Offset) {
        _wavePoints.add(index = 0, element = point)
        while (_wavePoints.size > maxWavePoints) _wavePoints.removeLast()
    }
}
