package demoTutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import calculator.composeapp.generated.resources.Res
import calculator.composeapp.generated.resources.egypt
import calculator.composeapp.generated.resources.france
import calculator.composeapp.generated.resources.indonesia
import calculator.composeapp.generated.resources.japan
import calculator.composeapp.generated.resources.mexico
import kotlinx.datetime.Clock
import kotlinx.datetime.IllegalTimeZoneException
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun DemoTutorial() {

    var showCountry by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf("") }
    var timeAtLocation by remember { mutableStateOf("No Location Selected") }
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
            text = timeAtLocation,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        TextField(
            label = { Text("Enter your city name") },
            value = location,
            onValueChange = { location = it },
            singleLine = true,
            modifier = Modifier.padding(top = 10.dp)
        )


        Button(
            onClick = { timeAtLocation = currentTimeAt(location) ?: "Invalid Location" },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(text = "Show Time")
        }
        Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp))
        {
            DropdownMenu(
                modifier = Modifier.background(Color.LightGray),
                expanded = showCountry,
                onDismissRequest = {
                    timeAtLocation = "No Location Selected"
                    showCountry = false
                }
            ) {
                countries().forEach { (name, zone, image) ->
                    DropdownMenuItem(
                        onClick = {
                            timeAtLocation = currentTimeAt(name, zone) ?: "Invalid Location"
                            showCountry = false
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painterResource(image),
                                contentDescription = "$name flag",
                                modifier = Modifier.size(50.dp).padding(end = 10.dp),
                            )
                            Text(text = name)
                        }
                    }
                }
            }
        }

        Button(modifier = Modifier.padding(top = 10.dp),
            onClick = { showCountry = !showCountry }
        ) {
            Text("Select Location")
        }
    }
}


fun currentTimeAt(location: String): String? {
    fun LocalTime.formatted() = "$hour:$minute:$second"
    return try {
        val time = Clock.System.now()
        val zone = TimeZone.of(location)
        val localTime = time.toLocalDateTime(timeZone = zone).time
        "The Time in $location is ${localTime.formatted()}"
    } catch (ex: IllegalTimeZoneException) {
        null
    }
}


fun currentTimeAt(location: String, zone: TimeZone): String? {
    fun LocalTime.formatted() = "$hour:$minute:$second"


    return try {
        val time = Clock.System.now()
        val localTime = time.toLocalDateTime(zone).time
        "The time in $location is ${localTime.formatted()}"
    } catch (ex: IllegalTimeZoneException) {
        null
    }
}

@OptIn(ExperimentalResourceApi::class)
data class Country(val name: String, val zone: TimeZone, val image: DrawableResource)

@OptIn(ExperimentalResourceApi::class)
fun countries() = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.japan),
    Country("France", TimeZone.of("Europe/Paris"), Res.drawable.france),
    Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.mexico),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.indonesia),
    Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.egypt),
)