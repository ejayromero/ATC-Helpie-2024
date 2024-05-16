package com.example.helpie.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@Composable
fun JourneyInTransportScreen(
    modifier: Modifier = Modifier,
    stepInfo: transportInfo,
    onNext: () -> Unit = {},
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
                    "rail" -> Pair(R.drawable.rail_icon, "Train Icon")
                    "bus" -> Pair(R.drawable.bus_icon, "Bus Icon")
                    else -> throw IllegalArgumentException("Invalid mode: ${stepInfo.mode.toString()}")
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
            text = "Le ${stepInfo.mode.toString()} arrive à l'arrêt ${stepInfo.endName} dans $time minutes !",
            color = Color.Black
        )
    }
}
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