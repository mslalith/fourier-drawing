package dev.mslalith.common.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import dev.mslalith.common.modifiers.Direction.Horizontal
import dev.mslalith.common.modifiers.FillMinMaxModifier

fun Modifier.fillMaxWidth(
    fraction: Float,
    minAllowedSize: Dp = Dp.Unspecified,
    maxAllowedSize: Dp = Dp.Unspecified
) = then(
    FillMinMaxModifier(
        direction = Horizontal,
        fraction = fraction,
        minAllowedSize = minAllowedSize,
        maxAllowedSize = maxAllowedSize
    )
)
