package com.example.helpie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton

/**
 * Composable function for displaying the final screen.
 *
 * @param modifier The modifier to be applied to the composable.
 * @param recommence The action to be performed when the "Nouveau trajet" button is clicked.
 */
@Composable
fun FinalScreen(
    modifier: Modifier = Modifier,
    recommence: () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CustomTextView(
                text = stringResource(R.string.trajet_termin),
                color = MaterialTheme.colorScheme.onSurface
            )
            TemplateButton(
                onClick = { recommence() },
                text = stringResource(R.string.nouveau_trajet),
                padding = false,
                sizeButton = "huge"
            )
        }
    }
}

/**
 * A preview composable function for the FinalScreen.
 * This function is annotated with @Preview and @Composable, allowing it to be previewed in Android Studio's Layout Editor.
 * It displays a sample view of the screen with mock data.
 *
 * @see FinalScreen
 */
@Preview(showBackground = true )
@Composable
fun FinalPreview() {
    AppTheme {
        FinalScreen(
        )
    }
}