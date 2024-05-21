package com.example.helpie.ui

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme

@Composable
fun HelpScreen(
    usePhone: Boolean,
    phoneNumber: String,
    outlineNumber: String,
    modifier: Modifier = Modifier,
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
                    if (usePhone) {
                        val u = Uri.parse("tel:$phoneNumber")
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
                    } else {
                        val u = Uri.parse("tel:$outlineNumber")
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

@Preview
@Composable
fun HelpPreview() {
    AppTheme {
        HelpScreen(
            usePhone = true,
            phoneNumber = "",
            outlineNumber = ""
        )
    }
}