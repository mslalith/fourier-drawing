package dev.mslalith.common.shared

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

abstract class FourierSettings {
    var cycleDuration by mutableStateOf(value = 4_000)
    var numberOfCircles by mutableStateOf(value = 3)
    var maxShapePoints by mutableStateOf(value = 450)
    var circlesCenterPercent by mutableStateOf(0.3f)
    var showEpicycleCenter by mutableStateOf(value = false)

    // TODO: Maintaining points and drawing them on every frame can become an overhead with complex drawings
    // Eliminate list & directly work with Path
    private val _shapePoints= mutableStateListOf<Offset>()
    val shapePoints: List<Offset>
        get() = _shapePoints

    fun shapePathContinuous(startOffset: Float = 0f): Path = Path().apply {
        _shapePoints.firstOrNull()?.let { moveTo(startOffset, it.y) }
        _shapePoints.drop(n = 1).forEachIndexed { index, wavePoint ->
            lineTo(x = startOffset + index, y = wavePoint.y)
        }
    }

    fun shapePathExact(): Path = Path().apply {
        _shapePoints.firstOrNull()?.let { moveTo(it.x, it.y) }
        _shapePoints.drop(n = 1).forEach { wavePoint ->
            lineTo(x = wavePoint.x, y = wavePoint.y)
        }
    }


    fun addShapePoint(point: Offset) {
        _shapePoints.add(index = 0, element = point)
        if (maxShapePoints > 0) {
            while (_shapePoints.size > maxShapePoints) _shapePoints.removeLast()
        }
    }

    fun clearShapePoints() {
        _shapePoints.clear()
    }
}
