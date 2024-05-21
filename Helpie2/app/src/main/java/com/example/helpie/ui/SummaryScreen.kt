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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.Localisation
import com.example.helpie.R
import com.example.helpie.StepInfo
import com.example.helpie.TripSummary
import com.example.helpie.transportInfo
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.walkInfo


@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    targetLocation: Localisation,
    summary : TripSummary,
    steps : List<StepInfo>,
    onNext: () -> Unit = {},
) {

    Box(
        modifier = modifier
    ) {


        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                Text(
                    text = "Ton trajet",
                    modifier = Modifier
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp
                )
            }
            // Display destination, end time, and number of steps
            item {
                Text(
                    text = "Ta destination : ${targetLocation.destinationName ?: "Unknown"}",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
            item {
                Text(
                    text = "Arrivé prévu à : ${summary.endTime}",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }

            item {
                Text(
                    text = "nombre d'étapes : ${summary.npSteps}",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            for (step in steps) {
                item {
                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .height(75.dp)
                            .background(color = Color.Black, shape = RoundedCornerShape(16.dp))
                            .padding(16.dp)

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            val (iconResource, contentDescription) = when (step.mode.toString()) {
                                "rail" -> Pair(R.drawable.rail_icon, "Train Icon")
                                "bus" -> Pair(R.drawable.bus_icon, "Bus Icon")
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
                            Column {
                                // Bus line text
                                if (step is walkInfo) {
                                    Text(
                                        text = "marche jusqu'à",
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = step.endName.toString(),
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                } else if (step is transportInfo) {
                                    Text(
                                        text = "transport jusqu'à",
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = step.endName.toString(),
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Button(
                    onClick = {
                        onNext()
                    },
                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.button_destination_height))
                        .width(dimensionResource(R.dimen.button_destination_width) * 2 + 30.dp),
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
    }

@Preview(showBackground = true)
@Composable
fun SummaryPreview() {
    AppTheme {
        SummaryScreen(
            targetLocation = Localisation(),
            summary = TripSummary(),
            steps = listOf(
                walkInfo(
                    startName = "Saint-Sulpice, Innovation Park",
                    endName = "Morges, Gare",
                    mode = "walk"
                ),
                transportInfo(
                    startName = "Saint-Sulpice, Innovation Park",
                    endName = "Morges, Gare",
                    mode = "bus"
                )
            )
        )
    }
}