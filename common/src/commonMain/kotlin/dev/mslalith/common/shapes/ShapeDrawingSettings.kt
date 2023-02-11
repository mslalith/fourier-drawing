package dev.mslalith.common.shapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.mslalith.common.settings.SettingsPanelCheckboxItem
import dev.mslalith.common.settings.SettingsPanelSliderItem
import dev.mslalith.common.shared.FourierSettings
import dev.mslalith.common.utils.extensions.VerticalSpacer

@Composable
fun ShapeDrawingSettingsPanel(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Gray.copy(alpha = 0.4f),
    settings: FourierSettings,
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
            header = "Circle Center Percent",
            value = settings.circlesCenterPercent,
            displayValue = displayValue(settings.circlesCenterPercent),
            onValueChange = { settings.circlesCenterPercent = it },
            valueRange = 0f..1f,
        )
        VerticalSpacer(height = 12.dp)
        SettingsPanelCheckboxItem(
            header = "Show Epicycle Center",
            checked = settings.showEpicycleCenter,
            onCheckChange = { settings.showEpicycleCenter = it },
        )
    }
}
