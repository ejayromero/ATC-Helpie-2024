package com.example.helpie.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.transportInfo
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.walkInfo

/**
 * Composable function for displaying the screen when the user reaches a stop.
 * It shows information about the next step of the journey, including the mode of transport and details.
 *
 * @param stepInfo The information about the current step of the journey.
 * @param nextStep The information about the next step of the journey.
 */
@Composable
fun ReachStopScreen(
    stepInfo: walkInfo,
    nextStep: transportInfo
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextView(
            text = stringResource(R.string.at_the_stop_question),
            color = MaterialTheme.colorScheme.onSurface
        )

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

                val (iconResource, contentDescription) = when (stepInfo.mode) {
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
                )
                // Spacer to create space between icon and text
                Spacer(modifier = Modifier.width(28.dp))
                Column {
                    // Bus line text
                    CustomTextView(
                        text = nextStep.line.toString(),
                        padding = false,
                        size = 32.sp,
                        )
                    CustomTextView(
                        text = nextStep.mode.toString(),
                        padding = false,
                        size = 16.sp
                    )

                }
            }
        }

        CustomTextView(
            text = nextStep.startName.toString(),
            color = MaterialTheme.colorScheme.onSurface,
        )
        CustomTextView(
            text = stringResource(R.string.diretion) + nextStep.way.toString(),
            padding = false,
            size = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * A preview composable function for the ReachStopScreen.
 * This function is annotated with @Preview and @Composable, allowing it to be previewed in Android Studio's Layout Editor.
 * It displays a sample view of the screen when the user reaches a stop, showing the mode of transport and details of the next step.
 *
 * @see ReachStopScreen
 */
@Preview(showBackground = true)
@Composable
fun ReachStopScreenPreview() {
    AppTheme {
        ReachStopScreen(
            stepInfo = walkInfo(
                "rail",
                "Saint-Sulpice",
                endName = "Morges Gare"
            ),
            nextStep = transportInfo(
                "bus",
                "701",
                "Morges Gare",
                way  = "Bourdonnette"
            )
        )
    }
}