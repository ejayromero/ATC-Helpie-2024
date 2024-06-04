package com.example.helpie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton

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
                text = "Trajet terminé !",
                color = MaterialTheme.colorScheme.onSurface
            )
            TemplateButton(
                onClick = { recommence() },
                text = "Nouveau trajet",
                padding = false,
                sizeButton = "huge"
            )
        }
    }
}

@Preview(showBackground = true )
@Composable
fun FinalPreview() {
    AppTheme {
        FinalScreen(
        )
    }
}