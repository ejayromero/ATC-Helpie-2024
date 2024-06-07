package com.example.helpie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.Localisation
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView

/**
 * A composable function representing the SettingsScreen.
 * This screen displays various settings options such as language selection, ticket settings, contact options, and registered destinations.
 *
 * @param registeredLocation List of registered locations.
 * @param setLocalisationName Callback function to set the name of a registered location.
 * @param setLocalisationAddress Callback function to set the address of a registered location.
 * @param usePhone Flag indicating whether to use phone contact option.
 * @param phoneNumber The phone number for contact.
 * @param outlineNumber The outline number for contact.
 * @param phone Callback function to toggle phone contact option.
 * @param setPhone Callback function to set the phone number.
 * @param debugging Flag indicating whether debugging is enabled.
 * @param switchDebug Callback function to toggle debugging mode.
 * @param easyRide Flag indicating whether to enable easy ride mode.
 * @param switchTicket Callback function to toggle ticket settings.
 * @param setLangage Callback function to set the language.
 * @param currentLangage The currently selected language.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    registeredLocation: List<Localisation>,
    setLocalisationName: (Int, String, List<Localisation>) -> Unit = { _, _, _ -> },
    setLocalisationAddress: (Int, String) -> Unit = { _, _ -> },
    usePhone: Boolean,
    phoneNumber: String,
    outlineNumber: String,
    phone: (Boolean) -> Unit = {},
    setPhone: (String) -> Unit = {},
    debugging: Boolean,
    switchDebug: () -> Unit = {},
    easyRide : Boolean,
    switchTicket: () -> Unit = {},
    setLangage: (String) -> Unit = {},
    currentLangage : String
) {
    val textWeightLeft = 0.4f
    val textWeightRight = 0.6f

    var expanded by remember { mutableStateOf(false) }

    val languagePairs = listOf(
        stringResource(id = R.string.anglais) to "en",
        stringResource(id = R.string.francais) to "fr"
    )

    Box(
        modifier = Modifier
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            //Debugging

            item {
                CustomTextView(
                    text = stringResource(id = R.string.Debugging),
                    size = 32.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = debugging,
                        onCheckedChange = { switchDebug() }
                    )
                    CustomTextView(
                        text = stringResource(id = R.string.skip),
                        color = MaterialTheme.colorScheme.onSurface
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

            //Langage

            item {
                CustomTextView(
                    text = stringResource(R.string.langue),
                    size = 32.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            item {
                Box(modifier = Modifier.padding(20.dp)
                    .fillMaxWidth()) {
                    languagePairs.find { it.second == currentLangage }?.let {
                        Text(
                            text = it.first,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray)
                                .padding(8.dp)
                                .clickable(onClick = { expanded = true })
                        )
                    }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    languagePairs.forEach  { (language, locale) ->
                        DropdownMenuItem(onClick = {
                            expanded = false
                            setLangage(locale)
                        }) {
                            Text(text = language)
                        }
                    }
                }}
            }
            item {
                Divider(
                    color = Color.Black,
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) }

            //Ticket


            item {
                CustomTextView(
                    text = stringResource(id = R.string.ticket),
                    size = 32.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = easyRide,
                        onCheckedChange = { switchTicket() }
                    )
                    CustomTextView(
                        text = stringResource(id = R.string.easyride),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                } }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = !easyRide,
                        onCheckedChange = { switchTicket() }
                    )
                    CustomTextView(
                        text = stringResource(R.string.rappel_de_prendre_le_ticket),
                        color = MaterialTheme.colorScheme.onSurface
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

            //Contacts


            item {
                CustomTextView(
                    text = stringResource(id = R.string.contact),
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

            //Destinations


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
                            placeholder = { Text(text = stringResource(id = R.string.nom)) },

                            modifier = Modifier
                                .padding(16.dp)
                                .height(dimensionResource(R.dimen.destination_textField_height))
                                .weight(textWeightLeft),
                        )
                        TextField(
                            value = registeredLocation[i].destinationAddress ?: "",
                            onValueChange = { address ->
                                setLocalisationAddress(i, address)
                            },
                            placeholder = { Text(text = stringResource(id = R.string.adresse)) },
                            colors = TextFieldDefaults.textFieldColors(
                                focusedTextColor = if (registeredLocation[i].isValid) Color(
                                    0xFF006400
                                ) else Color.Red
                            ),
                            modifier = Modifier
                                .padding(16.dp)
                                .height(dimensionResource(R.dimen.destination_textField_height))
                                .weight(textWeightRight),
                        )
                    }
                }
            }

            }
        }
    }


/**
 * A preview composable function for the SettingsScreen.
 * This function is annotated with @Preview and @Composable, allowing it to be previewed in Android Studio's Layout Editor.
 * It displays a sample view of the settings screen with various settings options such as registered destinations, language selection, contact options, and debugging settings.
 *
 * @see SettingsScreen
 */
@Preview(showBackground = true)
@Composable

fun SettingsPreview() {
    AppTheme {
        SettingsScreen(
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
                )),
            usePhone = true,
            phoneNumber = "",
            outlineNumber = "",
            debugging = false,
            easyRide = true,
            currentLangage = "fr"
        )
    }
}