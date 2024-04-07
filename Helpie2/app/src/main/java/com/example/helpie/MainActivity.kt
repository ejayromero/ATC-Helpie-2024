package com.example.helpie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.ui.theme.AppTheme

//The Edit mode enable the helper to configure the app
var editMode = mutableStateOf(false)

//Help interface and number to call
var help = mutableStateOf(false)
var phoneNumber = mutableStateOf(TextFieldValue())  //"+33658814083"

//do we have a ticket
var ticketInterface = mutableStateOf(false)
var ticket = mutableStateOf( false)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HelpieApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpieApp() {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            Column(Modifier.fillMaxWidth()) {
                TopAppBar(
                    title = {
                        Column {
                            //Spacer(modifier = Modifier.height(.dp)) // Adding space on the top
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = stringResource(R.string.HELPIE),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 60.sp
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    actions = {
                        Column {
                            //Spacer(modifier = Modifier.height(25.dp))
                            Switch(
                                checked = editMode.value,
                                onCheckedChange = { editMode.value = !editMode.value },
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior, //enable scrolling
                )

                if (ticket.value and !ticketInterface.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .background(color = MaterialTheme.colorScheme.primaryContainer) // Change Color.Green to your desired background color
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(color = MaterialTheme.colorScheme.primaryContainer) // Change Color.Green to your desired background color
                    ) {
                        Button(
                            onClick = { ticketInterface.value = true },
                            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                        )
                        {
                            Text(
                                text = "BILLET",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
        ,
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Button(
                    onClick = {
                        if (ticketInterface.value) {
                            ticketInterface.value = false}
                        else
                        {help.value = !help.value }
                     },
                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                )
                {Text(
                    text = if (!help.value and !ticketInterface.value) {
                        stringResource(R.string.aide)
                    } else {
                        stringResource(R.string.retour)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            AppBody()
        }
    }
}

@Composable
fun AppBody(
    modifier: Modifier = Modifier
) {
    if (help.value) {
        HELP()
    } else if (ticketInterface.value) {
        Billet()
    } else {
        Start()
    }


}

@Composable
fun HELP(
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current

    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (editMode.value) {
                Text(
                    text = stringResource(R.string.numero_du_proche_aidant),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
                TextField(
                    // on below line we are specifying value
                    // for our  text field.
                    value = phoneNumber.value,

                    // on below line we are adding on value
                    // change for text field.
                    onValueChange = { phoneNumber.value = it },

                    // on below line we are adding place holder as text
                    placeholder = { Text(text = "Enter your phone number") },

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
                )
            }
            else {
                Image(
                    painter = painterResource(R.drawable.phone),
                    contentDescription = stringResource(R.string.appelle_icon),
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_image_width))
                        .height(dimensionResource(R.dimen.button_image_height))
                        .padding(dimensionResource(R.dimen.button_interior_padding))
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
                Text(
                    text = stringResource(R.string.appeller_le_proche_aidant),
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
                Button(onClick = {
                    // on below line we are opening the dialer of our
                    // phone and passing phone number.
                    // Use format with "tel:" and phoneNumber created is
                    // stored in u.
                    val u = Uri.parse("tel:" + phoneNumber.value.text)


                    // Create the intent and set the data for the
                    // intent as the phone number.
                    val i = Intent(Intent.ACTION_DIAL, u)
                    try {

                        // Launch the Phone app's dialer with a phone
                        // number to dial a call.
                        ctx.startActivity(i)
                    } catch (s: SecurityException) {

                        // show() method display the toast with
                        // exception message.
                        Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG)
                            .show()
                    }
                }) {
                    // on below line creating a text for our button.
                    Text(
                        // on below line adding a text ,
                        // padding, color and font size.
                        text = stringResource(R.string.appel),
                        modifier = Modifier.padding(10.dp),
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
fun Billet(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                    painter = painterResource(R.drawable.billetcff),
                    contentDescription = stringResource(R.string.billet_cff_qrcode),
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.CFF))
                        .height(dimensionResource(R.dimen.CFF))
                        .padding(dimensionResource(R.dimen.button_interior_padding))
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Text(
                text = "Billet CFF",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun Start(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {
                    ticket.value = true
                },
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            )
            {
                Text(
                    text = stringResource(R.string.commencer_un_trajet),
                    modifier = Modifier
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun HelpiePreview() {
    AppTheme {
        HelpieApp()
    }
}