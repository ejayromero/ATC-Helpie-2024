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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaitingTransportScreen(
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

                Column() {
                    CustomTextView(
                        text = stepInfo.line.toString(),
                        size = 36.sp,
                        padding = false
                    )
                    CustomTextView(
                        text = if (stepInfo.mode.toString() == stringResource(id = R.string.rail)) stringResource(id = R.string.train) else stepInfo.mode.toString(),
                        size = 16.sp,
                        padding = false
                    )
                }
            }
        }

        CustomTextView(
            text = stringResource(id = R.string.le) + " " + stepInfo.mode + " " +stringResource(id = R.string.arrive)  + " " + time + " "+ stringResource(id = R.string.min) ,
            color = MaterialTheme.colorScheme.onSurface,
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
                mode = "rail",
                startName = "Morges",
                startTime = "2022-12-12T12:00:00",
                endTime = "2022-12-12T12:30:00",
                line = "31"
            ),
            time = 5
        )
    }
}