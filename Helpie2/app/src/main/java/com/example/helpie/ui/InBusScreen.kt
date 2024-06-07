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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.transportInfo
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView

@Composable
fun InBusScreen(
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
            text = stringResource(id = R.string.le) + stepInfo.mode.toString() + stringResource(R.string.arrive_bient_t_pr_pare_toi_monter),
            color = Color.Black,
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
                        padding = false,
                        size = 32.sp,
                    )
                    CustomTextView(
                        text = stepInfo.mode.toString(),
                        padding = false,
                        size = 16.sp,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        CustomTextView(
            text = stepInfo.startName.toString(),
            color = Color.Black,
            padding = false
        )

        CustomTextView(
            text = stringResource(id = R.string.diretion) + stepInfo.way.toString(),
            color = MaterialTheme.colorScheme.onSurface,
            padding = false,
            size = 20.sp
        )
    }
}
@Preview(showBackground = true)
@Composable
fun InBusScreenPreview() {
    AppTheme {
        InBusScreen(
            stepInfo = transportInfo(
                startName = "Saint-Sulpice, Innovation Park",
                endName = "Morges, Gare",
                mode = "bus",
                way = "Morges, Gare"
            )
        )
    }
}