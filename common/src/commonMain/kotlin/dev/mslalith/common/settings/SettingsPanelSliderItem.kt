package dev.mslalith.common.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingsPanelSliderItem(
    modifier: Modifier = Modifier,
    header: String,
    value: Float,
    displayValue: String,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .background(color = Color.Gray.copy(alpha = 0.6f))
            .padding(horizontal = 12.dp)
            .padding(top = 16.dp),
    ) {
        Row {
            Text(
                text = header,
                modifier = Modifier.weight(weight = 1f),
                maxLines = 1
            )
            Text(text = displayValue)
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
        )
    }
}
