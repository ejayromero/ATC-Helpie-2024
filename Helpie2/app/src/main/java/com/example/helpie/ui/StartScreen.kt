package com.example.helpie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.TemplateButton

/**
 * A composable function representing the StartScreen.
 * This screen displays the start button to initiate a trip.
 *
 * @param modifier The modifier for the StartScreen layout.
 * @param onTicket Callback function invoked when the start button is clicked.
 */
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onTicket: (Boolean) -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            TemplateButton(
                onClick = { onTicket(true) },
                text = stringResource(R.string.commencer_un_trajet),
                padding = false,
                sizeButton = "huge"
            )
        }
    }
}

/**
 * A preview composable function for the StartScreen.
 * This function is annotated with @Preview and @Composable, allowing it to be previewed in Android Studio's Layout Editor.
 * It displays a preview of the StartScreen wrapped inside the AppTheme.
 *
 * @see StartScreen
 */
@Preview(showBackground = true)
@Composable
fun StartPreview() {
    AppTheme {
        StartScreen(
        )
    }
}