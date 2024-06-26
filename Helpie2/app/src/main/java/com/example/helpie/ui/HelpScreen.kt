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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton

/**
 * Composable function for displaying help information.
 *
 * @param usePhone Flag indicating whether to use the phone number provided directly or the outline number.
 * @param phoneNumber The phone number to be dialed.
 * @param outlineNumber The outline number to be dialed if usePhone is false.
 * @param modifier The modifier to be applied to the composable.
 */
@Composable
fun HelpScreen(
    usePhone: Boolean,
    phoneNumber: String,
    outlineNumber: String,
    modifier: Modifier = Modifier,
) {
    val ctx = LocalContext.current

    Box(
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 48.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.phone),
                contentDescription = stringResource(R.string.appele_icon),
                modifier = modifier
                    .width(dimensionResource(R.dimen.button_image_width))
                    .height(dimensionResource(R.dimen.button_image_height))
                    .padding(dimensionResource(R.dimen.button_interior_padding))
            )

            Spacer(modifier = modifier.height(0.dp))
            CustomTextView(
                text = stringResource(R.string.appeler_le_proche_aidant),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = modifier.height(0.dp))
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
                sizeButton = "huge",
                containerColor = MaterialTheme.colorScheme.primary
            )
        }
    }

}

/**
 * A preview composable function for the HelpScreen.
 * This function is annotated with @Preview and @Composable, allowing it to be previewed in Android Studio's Layout Editor.
 * It displays a sample view of the screen with mock data.
 *
 * @see HelpScreen
 */
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