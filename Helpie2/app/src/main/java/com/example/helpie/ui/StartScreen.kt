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

@Preview(showBackground = true)
@Composable
fun StartPreview() {
    AppTheme {
        StartScreen(
        )
    }
}