package calculatorApp.data

import calculatorApp.CalculatorOperation

data class CalculatorState(
    val number1: String = "",
    val operation: CalculatorOperation? = null,
    val number2: String = ""
)