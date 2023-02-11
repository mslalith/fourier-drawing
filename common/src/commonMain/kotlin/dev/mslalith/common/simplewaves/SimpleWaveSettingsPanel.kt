package dev.mslalith.common.simplewaves

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SimpleWaveSettingsPanel(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Gray.copy(alpha = 0.4f),
    settings: SimpleWaveSettings,
) {
    fun displayValue(value: Float): String {
        return String.format("%.2f", value)
    }

    Column(
        modifier = modifier
            .background(color = backgroundColor)
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        SettingsPanelItemGap()
        SettingsPanelSliderItem(
            header = "Cycle Duration",
            value = settings.cycleDuration.toFloat(),
            displayValue = settings.cycleDuration.toString(),
            onValueChange = { settings.cycleDuration = it.toInt() },
            valueRange = 1_000f..15_000f
        )
        SettingsPanelItemGap()
        SettingsPanelSliderItem(
            header = "Number of Circles",
            value = settings.numberOfCircles.toFloat(),
            displayValue = settings.numberOfCircles.toString(),
            onValueChange = { settings.numberOfCircles = it.toInt() },
            valueRange = 0f..12f,
        )
        SettingsPanelItemGap()
        SettingsPanelSliderItem(
            header = "Circle Center Percent",
            value = settings.circlesCenterPercent,
            displayValue = displayValue(settings.circlesCenterPercent),
            onValueChange = { settings.circlesCenterPercent = it },
            valueRange = 0f..1f,
        )
        SettingsPanelItemGap()
        SettingsPanelSliderItem(
            header = "Wave Start Percent",
            value = settings.waveStartPercent,
            displayValue = displayValue(settings.waveStartPercent),
            onValueChange = { settings.waveStartPercent = it },
            valueRange = 0f..1f,
        )
        SettingsPanelItemGap()
        SettingsPanelSliderItem(
            header = "Max Wave Points",
            value = settings.maxShapePoints.toFloat(),
            displayValue = settings.maxShapePoints.toString(),
            onValueChange = { settings.maxShapePoints = it.toInt() },
            valueRange = 0f..600f,
        )
        SettingsPanelItemGap()
        SettingsPanelCheckboxItem(
            header = "Show Epicycle Center",
            checked = settings.showEpicycleCenter,
            onCheckChange = { settings.showEpicycleCenter = it },
        )
    }
}

@Composable
private fun SettingsPanelItemGap(height: Dp = 12.dp) {
    Spacer(Modifier.height(height = height))
}

@Composable
private fun SettingsPanelSliderItem(
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

@Composable
private fun SettingsPanelCheckboxItem(
    modifier: Modifier = Modifier,
    header: String,
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckChange
        )
        Text(text = header)
    }
}
