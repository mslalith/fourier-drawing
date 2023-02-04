import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.mslalith.common.App


fun main() = application {
    val windowState = rememberWindowState(size = DpSize(width = 1200.dp, height = 800.dp))
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState
    ) {
        App()
    }
}
