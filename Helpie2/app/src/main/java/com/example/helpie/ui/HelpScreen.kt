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
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton

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
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 164.dp)
        ) {
                Image(
                    painter = painterResource(R.drawable.phone),
                    contentDescription = stringResource(R.string.appele_icon),
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_image_width))
                        .height(dimensionResource(R.dimen.button_image_height))
                        .padding(dimensionResource(R.dimen.button_interior_padding))
                )

                Spacer(modifier = Modifier.height(16.dp))
                CustomTextView(
                    text = stringResource(R.string.appeler_le_proche_aidant),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
                TemplateButton(
                    onClick = {
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
                              },
                    text = stringResource(R.string.appel),
                    padding = false,
                    containerColor = MaterialTheme.colorScheme.primary
                    )
                }
            }

    }


@Preview(showBackground = true)
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