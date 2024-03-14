package calculatorApp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import calculatorApp.CalculatorAction
import calculatorApp.CalculatorOperation
import calculatorApp.data.CalculatorState
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class CalculatorViewModel : ViewModel() {
    var uiState by mutableStateOf(CalculatorState())
        private set


    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operator -> enterOperator(action.operator)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Clear -> uiState = CalculatorState()
            is CalculatorAction.Calculate -> performCalculation()
        }
    }

    private fun performCalculation() {
        val num1 = uiState.number1.toDoubleOrNull()
        val num2 = uiState.number2.toDoubleOrNull()
        if (num1 != null && num2 != null) {
            val result = when (uiState.operation) {
                is CalculatorOperation.Addition -> num1 + num2
                is CalculatorOperation.Subtract -> num1 - num2
                is CalculatorOperation.Multiplication -> num1 * num2
                is CalculatorOperation.Division -> num1 / num2
                null -> return
            }
            uiState = uiState.copy(
                number1 = result.toString().take(10), number2 = "", operation = null
            )
        }
    }

    private fun performDeletion() {
        when {
            uiState.number2.isNotBlank() -> {
                uiState = uiState.copy(number2 = uiState.number2.dropLast(1))
            }

            uiState.operation != null -> {
                uiState = uiState.copy(operation = null)
            }

            uiState.number1.isNotBlank() -> {
                uiState = uiState.copy(number1 = uiState.number1.dropLast(1))
            }
        }
    }

    private fun enterDecimal() {
        if (uiState.operation == null && !uiState.number1.contains(".") && uiState.number1.isNotBlank()) {
            uiState = uiState.copy(
                number1 = uiState.number1 + "."
            )
            return
        } else if (!uiState.number2.contains(".") && uiState.number2.isNotBlank()) {
            uiState = uiState.copy(
                number2 = uiState.number2 + "."
            )
        }
    }

    private fun enterOperator(operator: CalculatorOperation) {
        if (uiState.number1.isNotBlank()) {
            uiState = uiState.copy(operation = operator)
        }
    }

    private fun enterNumber(number: Int) {
        if (uiState.operation == null) {
            if (uiState.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            uiState = uiState.copy(
                number1 = uiState.number1 + number
            )
            return
        }
        if (uiState.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        uiState = uiState.copy(
            number2 = uiState.number2 + number
        )
    }


    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}