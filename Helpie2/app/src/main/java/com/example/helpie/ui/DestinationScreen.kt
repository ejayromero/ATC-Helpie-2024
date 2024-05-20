package com.example.helpie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.Localisation
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme

@Composable
fun DestinationScreen(
    registeredLocation: List<Localisation>,
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    editMode: Boolean,
    onRequest: () -> Unit = {},
    setTarget: (Localisation) -> Unit = {},
    setLocalisationName: (Int, String, List<Localisation>) -> Unit = { _, _, _ -> },
    setLocalisationAddress: (Int, String, List<Localisation>) -> Unit = { _, _, _ -> },
    switchDialog: () -> Unit = {}
) {
    val textWeightLeft = 0.4f
    val textWeightRight = 0.6f

    Box(
        modifier = modifier
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            if (!editMode) {
                Text(
                    text = stringResource(R.string.choisir_la_destination),
                    modifier = Modifier
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp
                )
            }
        }

        // 4 buttons for preselected destinations
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Destination management
            if (editMode) {
                // Text field for destination 1
                Text(
                    text = stringResource(R.string.choisir_la_destination) + " 1",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp
                )
                // Button 1 edit
                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = registeredLocation[0].destinationName ?: "",
                        onValueChange = { name ->
                            setLocalisationName(0, name, registeredLocation)
                        },
                        label = { Text(stringResource(R.string.nom)) },
                        modifier = Modifier
                            .padding(10.dp)
                            .height(dimensionResource(R.dimen.destination_textField_height))
                            .weight(textWeightLeft),
                    )
                    TextField(
                        value = registeredLocation[0].destinationAddress ?: "",
                        onValueChange = { address ->
                            setLocalisationAddress(0, address, registeredLocation)
                        },
                        label = { Text(stringResource(R.string.adresse)) },
                        modifier = Modifier
                            .padding(10.dp)
                            .height(dimensionResource(R.dimen.destination_textField_height))
                            .weight(textWeightRight),
                    )
                }

                // Button 2 edit
                Text(
                    text = stringResource(R.string.choisir_la_destination) + " 2",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp
                )
                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = registeredLocation[1].destinationName ?: "",
                        onValueChange = { name ->
                            setLocalisationName(1, name, registeredLocation)
                        },
                        label = { Text(stringResource(R.string.nom)) },
                        modifier = Modifier
                            .padding(10.dp)
                            .height(dimensionResource(R.dimen.destination_textField_height))
                            .weight(textWeightLeft),
                    )
                    TextField(
                        value = registeredLocation[1].destinationAddress ?: "",
                        onValueChange = { address ->
                            setLocalisationAddress(1, address, registeredLocation)
                        },
                        label = { Text(stringResource(R.string.adresse)) },
                        modifier = Modifier
                            .padding(10.dp)
                            .height(dimensionResource(R.dimen.destination_textField_height))
                            .weight(textWeightRight),
                    )
                }

                // Button 3 edit
                Text(
                    text = stringResource(R.string.choisir_la_destination) + " 3",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp
                )
                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = registeredLocation[2].destinationName ?: "",
                        onValueChange = { name ->
                            setLocalisationName(2, name, registeredLocation)
                        },
                        label = { Text(stringResource(R.string.nom)) },
                        modifier = Modifier
                            .padding(10.dp)
                            .height(dimensionResource(R.dimen.destination_textField_height))
                            .weight(textWeightLeft),
                    )
                    TextField(
                        value = registeredLocation[2].destinationAddress ?: "",
                        onValueChange = { address ->
                            setLocalisationAddress(2, address, registeredLocation)
                        },
                        label = { Text(stringResource(R.string.adresse)) },
                        modifier = Modifier
                            .padding(10.dp)
                            .height(dimensionResource(R.dimen.destination_textField_height))
                            .weight(textWeightRight),
                    )
                }

                // Button 4 edit
                Text(
                    text = stringResource(R.string.choisir_la_destination) + " 4",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp
                )

                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = registeredLocation[3].destinationName ?: "",
                        onValueChange = { name ->
                            setLocalisationName(3, name, registeredLocation)
                        },
                        label = { Text(stringResource(R.string.nom)) },
                        modifier = Modifier
                            .padding(10.dp)
                            .height(dimensionResource(R.dimen.destination_textField_height))
                            .weight(textWeightLeft),
                    )
                    TextField(
                        value = registeredLocation[3].destinationAddress ?: "",
                        onValueChange = { address ->
                            setLocalisationAddress(3, address, registeredLocation)
                        },
                        label = { Text(stringResource(R.string.adresse)) },
                        modifier = Modifier
                            .padding(10.dp)
                            .height(dimensionResource(R.dimen.destination_textField_height))
                            .weight(textWeightRight),
                    )
                }

            } else {
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
                        Text(
                            text = registeredLocation[0].destinationName ?: "Default Name",
                            fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.button_destination_font_size).toSp() },
                            textAlign = TextAlign.Center,
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
                        Text(
                            text = registeredLocation[1].destinationName ?: "Default Name",
                            fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.button_destination_font_size).toSp() },
                            textAlign = TextAlign.Center,
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
                        Text(
                            text = registeredLocation[2].destinationName ?: "Default Name",
                            fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.button_destination_font_size).toSp() },
                            textAlign = TextAlign.Center,
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
                        Text(
                            text = registeredLocation[3].destinationName ?: "Default Name",
                            fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.button_destination_font_size).toSp() },
                            textAlign = TextAlign.Center,
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
                        .width(dimensionResource(R.dimen.button_destination_width)*2 + 30.dp),
                )
                {
                    Text(
                        text = "Selectionner une destination",
                        fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.button_destination_font_size).toSp() }
                    )
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            switchDialog()
                        },
                        title = { Text(text = "Nouvelle destination") },
                        text = {
                            TextField(
                                value = registeredLocation[4].destinationAddress ?: "",
                                onValueChange = { address ->
                                    setLocalisationAddress(4, address, registeredLocation)
                                },
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
                                Text("Confirm")
                            }
                        }
                    )
                }
            }
        }
    }
}

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
                    latitude = 46.51727585320471
                )
            ),
            showDialog = false,
            editMode = false,
        )
    }
}