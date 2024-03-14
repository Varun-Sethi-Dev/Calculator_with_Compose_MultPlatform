package calculatorApp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import calculatorApp.CalculatorAction
import calculatorApp.CalculatorOperation
import calculatorApp.data.CalculatorState
import calculatorApp.data.CalculatorViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun CalculatorApp() {

    val viewModel: CalculatorViewModel = getViewModel(
        key = "CalculatorViewModel",
        factory = viewModelFactory { CalculatorViewModel() })
    val state = viewModel.uiState
    val buttonSpacing = 8.dp
    Calculator(
        state,
        modifier = Modifier,
        buttonSpacing = buttonSpacing,
        onAction = viewModel::onAction
    )
}

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit
) {
    val MediumGray = Color(0xFF2E2E2E)
    val LightGray = Color(0xFF818181)
    val Orange = Color(0xFFFF9800)

    Box(
        modifier = modifier.fillMaxSize().background(Color.DarkGray).padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Text(
                text = state.number1 + (state.operation?.operator ?: "") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().border(4.dp, Orange),
                fontSize = 80.sp,
                color = Color.White
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(symbol = "AC",
                    modifier = Modifier.background(LightGray).weight(2f).aspectRatio(2f),
                    onClick = { onAction(CalculatorAction.Clear) })
                CalculatorButton(symbol = "Del",
                    modifier = Modifier.background(LightGray).aspectRatio(1f).weight(1f),
                    onClick = { onAction(CalculatorAction.Delete) })
                CalculatorButton(symbol = "/",
                    modifier = Modifier.background(Orange).aspectRatio(1f).weight(1f),
                    onClick = { onAction(CalculatorAction.Operator(CalculatorOperation.Division)) })

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(symbol = "7",
                    modifier = Modifier.background(MediumGray).weight(1f).aspectRatio(1f),
                    onClick = { onAction(CalculatorAction.Number(7)) })
                CalculatorButton(symbol = "8",
                    modifier = Modifier.background(MediumGray).weight(1f).aspectRatio(1f),
                    onClick = { onAction(CalculatorAction.Number(8)) })
                CalculatorButton(symbol = "9",
                    modifier = Modifier.background(MediumGray).aspectRatio(1f).weight(1f),
                    onClick = { onAction(CalculatorAction.Number(9)) })
                CalculatorButton(symbol = "x",
                    modifier = Modifier.background(Orange).aspectRatio(1f).weight(1f),
                    onClick = { onAction(CalculatorAction.Operator(CalculatorOperation.Multiplication)) })

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(symbol = "4",
                    modifier = Modifier.background(MediumGray).weight(1f).aspectRatio(1f),
                    onClick = { onAction(CalculatorAction.Number(4)) })
                CalculatorButton(symbol = "5",
                    modifier = Modifier.background(MediumGray).weight(1f).aspectRatio(1f),
                    onClick = { onAction(CalculatorAction.Number(5)) })
                CalculatorButton(symbol = "6",
                    modifier = Modifier.background(MediumGray).aspectRatio(1f).weight(1f),
                    onClick = { onAction(CalculatorAction.Number(6)) })
                CalculatorButton(symbol = "-",
                    modifier = Modifier.background(Orange).aspectRatio(1f).weight(1f),
                    onClick = { onAction(CalculatorAction.Operator(CalculatorOperation.Subtract)) })

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(symbol = "1",
                    modifier = Modifier.background(MediumGray).weight(1f).aspectRatio(1f),
                    onClick = { onAction(CalculatorAction.Number(1)) })
                CalculatorButton(symbol = "2",
                    modifier = Modifier.background(MediumGray).weight(1f).aspectRatio(1f),
                    onClick = { onAction(CalculatorAction.Number(2)) })
                CalculatorButton(symbol = "3",
                    modifier = Modifier.background(MediumGray).aspectRatio(1f).weight(1f),
                    onClick = { onAction(CalculatorAction.Number(3)) })
                CalculatorButton(symbol = "+",
                    modifier = Modifier.background(Orange).aspectRatio(1f).weight(1f),
                    onClick = { onAction(CalculatorAction.Operator(CalculatorOperation.Addition)) })

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(symbol = "0",
                    modifier = Modifier.background(MediumGray).weight(2f).aspectRatio(2f),
                    onClick = { onAction(CalculatorAction.Number(0)) })
                CalculatorButton(symbol = ".",
                    modifier = Modifier.background(MediumGray).aspectRatio(1f).weight(1f),
                    onClick = { onAction(CalculatorAction.Decimal) })
                CalculatorButton(symbol = "=",
                    modifier = Modifier.background(Orange).aspectRatio(1f).weight(1f),
                    onClick = { onAction(CalculatorAction.Calculate) })

            }
        }

    }
}
