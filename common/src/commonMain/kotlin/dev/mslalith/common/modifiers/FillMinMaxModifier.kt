package dev.mslalith.common.modifiers

import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import kotlin.math.roundToInt

enum class Direction {
    Vertical, Horizontal
}

class FillMinMaxModifier(
    private val direction: Direction,
    private val fraction: Float,
    private val minAllowedSize: Dp = Dp.Unspecified,
    private val maxAllowedSize: Dp = Dp.Unspecified
) : LayoutModifier {

    private fun getSize(size: Int): Int {
        val min = if (minAllowedSize == Dp.Unspecified) 0 else minAllowedSize.value.toInt()
        val max = if (maxAllowedSize == Dp.Unspecified) Int.MAX_VALUE else maxAllowedSize.value.toInt()

        return when {
            size < min -> min
            size > max -> max
            else -> size
        }
    }

    override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
        val minWidth: Int
        val maxWidth: Int
        if (constraints.hasBoundedWidth && direction != Direction.Vertical) {
            val width = (constraints.maxWidth * fraction).roundToInt()
                .coerceIn(constraints.minWidth, constraints.maxWidth)
            minWidth = getSize(width)
            maxWidth = getSize(width)
        } else {
            minWidth = constraints.minWidth
            maxWidth = constraints.maxWidth
        }
        val minHeight: Int
        val maxHeight: Int
        if (constraints.hasBoundedHeight && direction != Direction.Horizontal) {
            val height = (constraints.maxHeight * fraction).roundToInt()
                .coerceIn(constraints.minHeight, constraints.maxHeight)
            minHeight = getSize(height)
            maxHeight = getSize(height)
        } else {
            minHeight = constraints.minHeight
            maxHeight = constraints.maxHeight
        }
        val placeable = measurable.measure(
            Constraints(minWidth, maxWidth, minHeight, maxHeight)
        )

        return layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FillMinMaxModifier

        if (direction != other.direction) return false
        if (fraction != other.fraction) return false
        if (minAllowedSize != other.minAllowedSize) return false
        if (maxAllowedSize != other.maxAllowedSize) return false

        return true
    }

    override fun hashCode(): Int {
        var result = direction.hashCode()
        result = 31 * result + fraction.hashCode()
        result = 31 * result + minAllowedSize.hashCode()
        result = 31 * result + maxAllowedSize.hashCode()
        return result
    }
}
