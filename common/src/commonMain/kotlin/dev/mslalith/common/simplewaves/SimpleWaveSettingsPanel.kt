package dev.mslalith.common.simplewaves

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.mslalith.common.settings.SettingsPanelCheckboxItem
import dev.mslalith.common.settings.SettingsPanelSliderItem
import dev.mslalith.common.utils.extensions.VerticalSpacer

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
        VerticalSpacer(height = 12.dp)
        SettingsPanelSliderItem(
            header = "Cycle Duration",
            value = settings.cycleDuration.toFloat(),
            displayValue = settings.cycleDuration.toString(),
            onValueChange = { settings.cycleDuration = it.toInt() },
            valueRange = 1_000f..15_000f
        )
        VerticalSpacer(height = 12.dp)
        SettingsPanelSliderItem(
            header = "Number of Circles",
            value = settings.numberOfCircles.toFloat(),
            displayValue = settings.numberOfCircles.toString(),
            onValueChange = { settings.numberOfCircles = it.toInt() },
            valueRange = 0f..12f,
        )
        VerticalSpacer(height = 12.dp)
        SettingsPanelSliderItem(
            header = "Circle Center Percent",
            value = settings.circlesCenterPercent,
            displayValue = displayValue(settings.circlesCenterPercent),
            onValueChange = { settings.circlesCenterPercent = it },
            valueRange = 0f..1f,
        )
        VerticalSpacer(height = 12.dp)
        SettingsPanelSliderItem(
            header = "Wave Start Percent",
            value = settings.waveStartPercent,
            displayValue = displayValue(settings.waveStartPercent),
            onValueChange = { settings.waveStartPercent = it },
            valueRange = 0f..1f,
        )
        VerticalSpacer(height = 12.dp)
        SettingsPanelSliderItem(
            header = "Max Wave Points",
            value = settings.maxShapePoints.toFloat(),
            displayValue = settings.maxShapePoints.toString(),
            onValueChange = { settings.maxShapePoints = it.toInt() },
            valueRange = 0f..600f,
        )
        VerticalSpacer(height = 12.dp)
        SettingsPanelCheckboxItem(
            header = "Show Epicycle Center",
            checked = settings.showEpicycleCenter,
            onCheckChange = { settings.showEpicycleCenter = it },
        )
    }
}
