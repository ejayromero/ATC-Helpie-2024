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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.transportInfo
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView

@Composable
fun OutBusScreen(
    modifier: Modifier = Modifier,
    stepInfo: transportInfo,
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextView(
            text = "Il est bientot l'heure de descendre.",
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
                    "walk" -> Pair(R.drawable.walking_icon, "walk Icon")
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
                        .fillMaxSize()
                )

                Spacer(modifier = Modifier.width(28.dp))
                Column() {
                    CustomTextView(
                        text = stepInfo.line.toString(),
                        padding = false,
                        size = 36.sp
                    )
                    CustomTextView(
                        text = stepInfo.mode.toString(),
                        padding = false,
                        size = 16.sp
                    )
                }
            }
        }

        CustomTextView(
            text = stepInfo.endName.toString(),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
@Preview(showBackground = true)
@Composable
fun OutBusScreenPreview() {
    AppTheme {
        OutBusScreen(
            stepInfo = transportInfo(
                "rail",
                "Saint-Sulpice, Innovation Park ",
                endName = "Morges, Gare"
            )
        )
    }
}