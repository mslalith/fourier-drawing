package dev.mslalith.common.squareWaveCircles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SquareWaveCirclesSettingsPanel(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Gray.copy(alpha = 0.4f),
    settings: SquareWaveCirclesSettings,
    onSettingsUpdate: (SquareWaveCirclesSettings) -> Unit
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
            header = "Number of Circles",
            value = settings.numberOfCircles.toFloat(),
            displayValue = settings.numberOfCircles.toString(),
            onValueChange = { onSettingsUpdate(settings.copy(numberOfCircles = it.toInt())) },
            valueRange = 0f..12f,
        )
        SettingsPanelItemGap()
        SettingsPanelSliderItem(
            header = "Circle Center Percent",
            value = settings.circlesCenterPercent,
            displayValue = displayValue(settings.circlesCenterPercent),
            onValueChange = { onSettingsUpdate(settings.copy(circlesCenterPercent = it)) },
            valueRange = 0f..1f,
        )
        SettingsPanelItemGap()
        SettingsPanelSliderItem(
            header = "Wave Start Percent",
            value = settings.waveStartPercent,
            displayValue = displayValue(settings.waveStartPercent),
            onValueChange = { onSettingsUpdate(settings.copy(waveStartPercent = it)) },
            valueRange = 0f..1f,
        )
        SettingsPanelItemGap()
        SettingsPanelSliderItem(
            header = "Max Wave Points",
            value = settings.maxWavePoints.toFloat(),
            displayValue = settings.maxWavePoints.toString(),
            onValueChange = { onSettingsUpdate(settings.copy(maxWavePoints = it.toInt())) },
            valueRange = 100f..600f,
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
    Column(modifier = modifier) {
        Text(text = "$header : $displayValue")
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
        )
    }
}
