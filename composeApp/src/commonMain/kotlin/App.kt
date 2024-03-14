import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import calculatorApp.ui.CalculatorApp
import demoTutorial.DemoTutorial
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun App() {
    MaterialTheme {
        CalculatorApp()
    }
}







