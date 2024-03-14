import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() = application {
    val state: WindowState = WindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition(alignment = Alignment.Center),
        size = DpSize(500.dp, 900.dp),
        isMinimized = false
    )
    Window(onCloseRequest = ::exitApplication,
        state = state,
        title = "Local Time App") {
        App()
    }
}