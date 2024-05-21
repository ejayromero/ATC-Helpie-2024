package com.example.helpie.ui

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaitingTransportScreen(
    modifier: Modifier = Modifier,
    stepInfo: transportInfo,
    time : Int
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

                val (iconResource, contentDescription) = when (stepInfo.mode) {
                    "bus" -> Pair(R.drawable.bus_icon, "Bus Icon")
                    "rail" -> Pair(R.drawable.rail_icon, "Train Icon")
                    "walk" -> Pair(R.drawable.walking_icon, "Walk Icon")
                    "metro" -> Pair(R.drawable.metro_icon, "metro Icon")
                    "boat" -> Pair(R.drawable.boat_icon, "boat Icon")
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


                // Spacer to create space between icon and text
                Spacer(modifier = Modifier.width(28.dp))
                Column {
                    // Bus line text
                    Text(
                        text = stepInfo.line.toString(),
                        color = Color.White,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stepInfo.mode.toString(),
                        color = Color.White,
                        fontSize = 16.sp,

                        )
                }
            }
        }

        CustomTextView(
            text = "Le ${stepInfo.mode} arrive dans $time minutes",
            color = Color.Black,
        )
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun WaitingTransportScreenPreview() {
    AppTheme {
        WaitingTransportScreen(
            stepInfo = transportInfo(
                mode = "bus",
                startName = "Morges",
                startTime = "2022-12-12T12:00:00",
                endTime = "2022-12-12T12:30:00"
            ),
            time = 5
        )
    }
}