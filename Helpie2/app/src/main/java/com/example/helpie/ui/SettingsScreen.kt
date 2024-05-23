package com.example.helpie.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
fun SettingsScreen(
    _uiState: UiState,
    context: Context,
    modifier: Modifier = Modifier,
    registeredLocation: List<Localisation>,
    setLocalisationName: (Int, String, List<Localisation>) -> Unit = { _, _, _ -> },
    setLocalisationAddress: (Int, String, List<Localisation>, Context) -> Unit = { _, _, _,_ -> },
    usePhone: Boolean,
    phoneNumber: String,
    outlineNumber: String,
    phone: (Boolean) -> Unit = {},
    setPhone: (String) -> Unit = {},
) {
    val textWeightLeft = 0.4f
    val textWeightRight = 0.6f

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
                    text = "Contact",
                    size = 32.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = usePhone,
                        onCheckedChange = { phone(true) }
                    )
                    CustomTextView(
                        text = stringResource(R.string.numero_du_proche_aidant),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            item {TextField(
                // on below line we are specifying value
                // for our  text field.
                value = phoneNumber,

                // on below line we are adding on value
                // change for text field.
                onValueChange = { setPhone(it) },

                // on below line we are adding place holder as text
                placeholder = { Text(text = stringResource(R.string.enter_your_phone_number)) },

                // on below line we are adding modifier to it
                // and adding padding to it and filling max width
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),

                // on below line we are adding text style
                // specifying color and font size to it.
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

                // on below line we are adding single line to it.
                singleLine = true,
            )}
            item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = !usePhone,
                    onCheckedChange = { phone(false) }
                )
                CustomTextView(
                    text = stringResource(R.string.ligne_accessibilit_cff),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            }
            item {
                Card {
                    Text(
                        text = outlineNumber,
                        style = TextStyle(color = Color.Black, fontSize = 15.sp),
                        fontSize = 25.sp,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                }
            }
            item {
                Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) }
            item {
                CustomTextView(
                    text = stringResource(R.string.destinations_enregistr_es),
                    color = MaterialTheme.colorScheme.onSurface,
                    size = 32.sp
                )
            }
            for (i in 0..3) {
                item {
                    // Text field for destination 1
                    CustomTextView(
                        text = stringResource(R.string.choisir_la_destination) + " " + (i + 1).toString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        )
                }
                /*TEST LENA
                item {TextField(
                    // on below line we are specifying value
                    // for our  text field.
                    value = phoneNumber,

                    // on below line we are adding on value
                    // change for text field.
                    onValueChange = { setPhone(it) },

                    // on below line we are adding place holder as text
                    placeholder = { Text(text = stringResource(R.string.enter_your_phone_number)) },

                    // on below line we are adding modifier to it
                    // and adding padding to it and filling max width
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),

                    // on below line we are adding text style
                    // specifying color and font size to it.
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

                    // on below line we are adding single line to it.
                    singleLine = true,
                )} */


                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = registeredLocation[i].destinationName ?: "",
                            onValueChange = { name ->
                                setLocalisationName(i, name, registeredLocation)
                            },
                            placeholder = { Text(text = "Nom") },

                            //label = { Text(stringResource(R.string.nom)) },
                            modifier = Modifier
                                .padding(16.dp)
                                .height(dimensionResource(R.dimen.destination_textField_height))
                                .weight(textWeightLeft),
                        )
                        TextField(
                            value = registeredLocation[i].destinationAddress ?: "",
                            onValueChange = { address ->
                                setLocalisationAddress(i, address, registeredLocation, context)
                            },
                            //label = { Text(stringResource(R.string.adresse)) },
                            placeholder = { Text(text = "Adresse") },
                            colors = TextFieldDefaults.textFieldColors(
                                focusedTextColor = if (_uiState.registeredLocation[i].isValid) Color(0xFF006400) else Color.Red
                            ),
                            modifier = Modifier
                                .padding(16.dp)
                                .height(dimensionResource(R.dimen.destination_textField_height))
                                .weight(textWeightRight),
                        )
                    }
                }
                // Bu
            }
            }
        }
    }


//@Preview(showBackground = true)
//@Composable
/*
fun SettingsPreview() {
    AppTheme {
        SettingsScreen(
            registeredLocation = listOf(
                Localisation(
                    destinationName = "EPFL plasma center",
                    destinationAddress = "Address",
                    longitude = 6.564690632302699,
                    latitude = 46.51727585320471
            ),
                Localisation(
                    destinationName = "EPFL plasma center",
                    destinationAddress = "Address",
                    longitude = 6.564690632302699,
                    latitude = 46.51727585320471
                ),
                Localisation(
                    destinationName = "EPFL plasma center",
                    destinationAddress = "Address",
                    longitude = 6.564690632302699,
                    latitude = 46.51727585320471
                ),
                Localisation(
                    destinationName = "EPFL plasma center",
                    destinationAddress = "Address",
                    longitude = 6.564690632302699,
                    latitude = 46.51727585320471
                )),
            usePhone = true,
            phoneNumber = "",
            outlineNumber = "",
            context =
        )
    }
}

 */