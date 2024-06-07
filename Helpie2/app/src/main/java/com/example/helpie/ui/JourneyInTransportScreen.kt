package com.example.helpie.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.transportInfo
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView

/**
 * Composable function for displaying information during a journey in a particular mode of transport.
 *
 * @param stepInfo Information about the current step of the journey, including mode of transport, line, and destination.
 * @param time Estimated time remaining until the journey ends, in minutes.
 */
@Composable
fun JourneyInTransportScreen(
    stepInfo: transportInfo,
    time: Int
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .width(250.dp)
                .height(100.dp)
                .background(color = Color.Black, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                val (iconResource, contentDescription) = when (stepInfo.mode.toString()) {
                    stringResource(R.string.bus) -> Pair(R.drawable.bus_icon, stringResource(R.string.bus))
                    stringResource(R.string.rail) -> Pair(R.drawable.rail_icon, stringResource(R.string.rail))
                    stringResource(R.string.walk) -> Pair(R.drawable.walking_icon, stringResource(R.string.walk))
                    stringResource(R.string.metro) -> Pair(R.drawable.metro_icon, stringResource(R.string.metro))
                    stringResource(R.string.boat) -> Pair(R.drawable.boat_icon, stringResource(R.string.boat))
                    else -> Pair(R.drawable.travel_icon, stringResource(id = R.string.transport))
                }


                Image(
                    painter = painterResource(id = iconResource),
                    contentDescription = contentDescription,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(60.dp)
                        .fillMaxSize()
                )

                Spacer(modifier = Modifier.width(28.dp))
                Column {
                    // Bus line text
                    CustomTextView(
                        text = stepInfo.line.toString(),
                        size = 32.sp,
                        padding = false
                    )
                    CustomTextView(
                        text = stepInfo.mode.toString(),
                        size = 16.sp,
                        padding = false
                    )
                }
            }
        }
        CustomTextView(
            text = stringResource(id = R.string.le)+ " "+stepInfo.mode.toString()+ " " + stringResource(R.string.arrive_l_arr_t)+ " " + stepInfo.endName+ " " + stringResource(
                id = R.string.dans)+ " " + time+ " " + stringResource(id = R.string.min),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * A preview composable function for the JourneyInTransportScreen.
 * This function is annotated with @Preview and @Composable, allowing it to be previewed in Android Studio's Layout Editor.
 * It displays a sample view of the screen with mock data.
 *
 * @see JourneyInTransportScreen
 */
@Preview(showBackground = true)
@Composable
fun JourneyInTransportScreenPreview() {
    AppTheme {
        JourneyInTransportScreen(
            stepInfo = transportInfo(
                startName = "Saint-Sulpice, Innovation Park",
                endName = "Morges, Gare",
                mode = "bus"
            ),
            time = 3
        )
    }
}