package com.example.helpie.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.Localisation
import com.example.helpie.R
import com.example.helpie.UiState
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationScreen(
     _uiState: UiState,
     context: Context,
    registeredLocation: List<Localisation>,
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    onRequest: () -> Unit = {},
    setTarget: (Localisation) -> Unit = {},
    setLocalisationAddress: (Int, String, List<Localisation>, Context) -> Unit = { _, _, _,_ -> },
    switchDialog: () -> Unit = {}
) {

    Box(
        modifier = modifier
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            CustomTextView(
                text = stringResource(R.string.choisir_la_destination),
                color = MaterialTheme.colorScheme.onSurface,
                size = 32.sp
                )
        }

        // 4 buttons for preselected destinations
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // Button 1
                    Button(
                        onClick = {
                            setTarget(registeredLocation[0])
                            onRequest()
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.button_destination_height))
                            .width(dimensionResource(R.dimen.button_destination_width)),
                    )
                    {
                        CustomTextView(
                            text = registeredLocation[0].destinationName ?: stringResource(id = R.string.defN),
                            size = 16.sp,
                            padding = false
                            )
                    }
                    // Button 2
                    Button(
                        onClick = {
                            setTarget(registeredLocation[1])
                            onRequest()
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.button_destination_height))
                            .width(dimensionResource(R.dimen.button_destination_width)),
                    )
                    {
                        CustomTextView(
                            text = registeredLocation[1].destinationName ?: stringResource(id = R.string.defN),
                            size = 16.sp,
                            padding = false
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.destination_spacer)))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Button 3
                    Button(
                        onClick = {
                            setTarget(registeredLocation[2])
                            onRequest()
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.button_destination_height))
                            .width(dimensionResource(R.dimen.button_destination_width)),
                    )
                    {
                        CustomTextView(
                            text = registeredLocation[2].destinationName ?: stringResource(id = R.string.defN),
                            size = 16.sp,
                            padding = false
                        )
                    }
                    // Button 4
                    Button(
                        onClick = {
                            setTarget(registeredLocation[3])
                            onRequest()
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.button_destination_height))
                            .width(dimensionResource(R.dimen.button_destination_width)),
                    )
                    {
                        CustomTextView(
                            text = registeredLocation[3].destinationName ?: stringResource(id = R.string.defN),
                            size = 16.sp,
                            padding = false
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.destination_spacer)))
                // Button for new destination


                Button(
                    onClick = {
                        switchDialog()
                    },
                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.button_destination_height))
                        .width(dimensionResource(R.dimen.button_destination_width) * 2 + 30.dp),
                )
                {
                    Text(
                        text = stringResource(R.string.s_lectionner_une_destination),
                        fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.button_destination_font_size).toSp() }
                    )
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            switchDialog()
                        },
                        title = { Text(text = stringResource(R.string.nouvelle_destination)) },
                        text = {
                            TextField(
                                value = registeredLocation[4].destinationAddress ?: "",
                                onValueChange = { address ->
                                    setLocalisationAddress(4, address, registeredLocation, context)
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedTextColor = if (_uiState.registeredLocation[4].isValid) Color(0xFF006400) else Color.Red
                                ),
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    switchDialog()
                                    setTarget(registeredLocation[4])
                                    onRequest()
                                }
                            )
                            {
                                Text(stringResource(R.string.confirmer))
                            }
                        }
                    )

                }
            }
    }
}


/*
@Preview
@Composable
fun DestinationPreview() {
    AppTheme {
        DestinationScreen(
            registeredLocation = listOf(
                Localisation(
                    destinationName = "EPFL plasma center",
                    destinationAddress = "Address",
                    longitude = 6.564690632302699,
                    latitude = 46.51727585320471,
                    isValid = true
                ),
                Localisation(
                    destinationName = "EPFL plasma center",
                    destinationAddress = "Address",
                    longitude = 6.564690632302699,
                    latitude = 46.51727585320471,
                    isValid = true
                ),
                Localisation(
                    destinationName = "EPFL plasma center",
                    destinationAddress = "Address",
                    longitude = 6.564690632302699,
                    latitude = 46.51727585320471,
                    isValid = true
                ),
                Localisation(
                    destinationName = "EPFL plasma center",
                    destinationAddress = "Address",
                    longitude = 6.564690632302699,
                    latitude = 46.51727585320471,
                    isValid = true
                )
            ),
            showDialog = false
        )
    }
}*/
