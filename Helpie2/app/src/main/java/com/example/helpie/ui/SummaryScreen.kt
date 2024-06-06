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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.StepInfo
import com.example.helpie.TripSummary
import com.example.helpie.transportInfo
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton
import com.example.helpie.walkInfo
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    summary : TripSummary,
    steps : List<StepInfo>,
    onNext: () -> Unit = {}
) {
    val instantEndTime = Instant.parse(summary.endTime)
    val localEndTime = instantEndTime.toLocalDateTime(TimeZone.currentSystemDefault())
    val formattedEndTime = "${localEndTime.hour}h${localEndTime.minute.toString().padStart(2, '0')}"

    Box(
        modifier = modifier
    ) {


        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                CustomTextView(
                    text = stringResource(R.string.ton_trajet),
                    size = 32.sp,
                    padding = false,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            item {
                CustomTextView(
                    text = stringResource(R.string.arriv_e_pr_vue) + formattedEndTime,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            item {
                TemplateButton(
                    onClick = { onNext() },
                    text = stringResource(R.string.commencer),
                    padding = false
                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item{
                CustomTextView(
                    text = stringResource(R.string.r_sum_du_trajet),
                    color = MaterialTheme.colorScheme.onSurface)
            }

            for (step in steps) {
                item {
                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .height(100.dp)
                            .background(color = Color.Black, shape = RoundedCornerShape(16.dp))
                            .padding(16.dp)

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            val (iconResource, contentDescription) = when (step.mode.toString()) {
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
                                if (step is walkInfo) {
                                    CustomTextView(
                                        text = stringResource(R.string.marcher_jusqu),
                                        size = 12.sp,
                                        padding = false,
                                        align = TextAlign.Start
                                    )
                                    CustomTextView(
                                        text = step.endName.toString(),
                                        padding = false,
                                        size = 16.sp,
                                        align = TextAlign.Start
                                    )
                                } else if (step is transportInfo) {
                                    CustomTextView(
                                        text = stringResource(R.string.descendre),
                                        size = 12.sp,
                                        padding = false,
                                        align = TextAlign.Start
                                    )
                                    CustomTextView(
                                        text = step.endName.toString(),
                                        padding = false,
                                        size = 16.sp,
                                        align = TextAlign.Start
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryPreview() {
    AppTheme {
        SummaryScreen(
            summary = TripSummary(),
            steps = listOf(
                walkInfo(
                    startName = "Saint-Sulpice, Innovation Park",
                    endName = "Morges, Gare",
                    endTime = "2024-05-20T17:58:00Z",
                    mode = "walk"
                ),
                transportInfo(
                    startName = "Saint-Sulpice, Innovation Park",
                    endName = "Morges, Gare",
                    endTime = "2024-05-20T17:58:00Z",
                    mode = "bus"
                )
            )
        )
    }
}