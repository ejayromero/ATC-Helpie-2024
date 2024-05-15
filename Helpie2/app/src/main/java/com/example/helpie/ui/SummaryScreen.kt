package com.example.helpie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.Localisation
import com.example.helpie.R
import com.example.helpie.TripSummary
import com.example.helpie.ui.theme.AppTheme

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    targetLocation: Localisation,
    summary : TripSummary,
    onSummary: () -> Unit = {},
    onNext: () -> Unit = {},
) {

    Box(
        modifier = modifier
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
           Text(
                    text = "Ton trajet",
                    modifier = Modifier
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp
                )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            // Display destination, end time, and number of steps
            Text(
                text = "Ta destination : ${targetLocation.destinationName ?: "Unknown"}",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = "Arrivé prévu à : ${summary.endTime}",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = "nombre d'étapes : ${summary.npSteps}",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )


            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.button_corner_radius)))

            Button(
                onClick = {
                    onNext()
                },
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                modifier = Modifier
                    .height(dimensionResource(R.dimen.button_destination_height))
                    .width(dimensionResource(R.dimen.button_destination_width)*2 + 30.dp),
            )
            {
                Text(
                    text = "Commencer",
                    fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.button_destination_font_size).toSp() }
                )
            }
            }
        }
    }

@Preview
@Composable
fun SummaryPreview() {
    AppTheme {
        SummaryScreen(
            targetLocation = Localisation(),
            summary = TripSummary()
        )
    }
}