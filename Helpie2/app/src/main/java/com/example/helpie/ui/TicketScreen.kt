package com.example.helpie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton

@Composable
fun TicketScreen(
    modifier: Modifier = Modifier,
    showTicket: () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TemplateButton(
                onClick = { showTicket() },
                text = "Montrer le ticket",
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                padding = false
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))

            CustomTextView(
                text = stringResource(R.string.helpie_ticket),
                color = MaterialTheme.colorScheme.onSurface
            )

        }

    }

}

@Preview(showBackground = true)
@Composable
fun TicketPreview() {
    AppTheme {
        TicketScreen(
        )
    }
}