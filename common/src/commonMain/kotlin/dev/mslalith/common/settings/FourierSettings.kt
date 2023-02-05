package dev.mslalith.common.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

abstract class FourierSettings {
    var cycleDuration by mutableStateOf(value = 6_000)
    var numberOfCircles by mutableStateOf(value = 3)
    var maxShapePoints by mutableStateOf(value = 450)

    private val _shapePoints= mutableStateListOf<Offset>()
    val shapePoints: List<Offset>
        get() = _shapePoints

    fun addShapePoint(point: Offset) {
        _shapePoints.add(index = 0, element = point)
        while (_shapePoints.size > maxShapePoints) _shapePoints.removeLast()
    }
}
