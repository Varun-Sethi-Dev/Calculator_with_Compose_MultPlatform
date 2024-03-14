package calculatorApp

sealed class CalculatorOperation(val operator: String) {
    data object Addition : CalculatorOperation("+")
    data object Subtract : CalculatorOperation("-")
    data object Division : CalculatorOperation("/")
    data object Multiplication : CalculatorOperation("x")
}