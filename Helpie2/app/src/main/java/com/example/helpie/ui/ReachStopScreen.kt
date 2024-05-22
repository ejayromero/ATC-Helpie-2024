package com.example.helpie.ui

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.transportInfo
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.walkInfo

@Composable
fun ReachStopScreen(
    modifier: Modifier = Modifier,
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
            text = "Es-tu à l'arrêt ?",
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
                    "bus" -> Pair(R.drawable.bus_icon, "Bus Icon")
                    "rail" -> Pair(R.drawable.rail_icon, "Train Icon")
                    "walk" -> Pair(R.drawable.walking_icon, "Walk Icon")
                    "metro" -> Pair(R.drawable.metro_icon, "metro Icon")
                    "boat" -> Pair(R.drawable.boat_icon, "metro Icon")
                    else -> Pair(R.drawable.travel_icon, "transport Icon")
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
//                        text = "701",
                        padding = false,
                        size = 32.sp,
                        )
                    CustomTextView(
                        text = stepInfo.mode.toString(),
                        color = Color.White,
                        padding = false,
                        size = 16.sp
                    )
                }
            }
        }

        CustomTextView(
            text = stepInfo.startName.toString(),
            color = Color.Black
        )
    }
}
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
                "Morges Gare"
            )
        )
    }
}