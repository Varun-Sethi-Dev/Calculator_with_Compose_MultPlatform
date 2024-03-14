package preview

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import calculatorApp.ui.CalculatorApp

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AppPreview() {

    CalculatorApp()
}

@Preview(showBackground = true)
@Composable
fun Test() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


        Text(
            text = "fffhfhf", fontSize = 36.sp, modifier = Modifier
                .clip(RoundedCornerShape(40))
                .border(
                    4.dp,
                    Color.Red
                )
        )

    }
}