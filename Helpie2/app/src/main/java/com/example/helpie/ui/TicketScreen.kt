package com.example.helpie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme

@Composable
fun TicketScreen(
    modifier: Modifier = Modifier,
    showTicket: () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            /*Image(
                painter = painterResource(R.drawable.billetcff),
                contentDescription = stringResource(R.string.billet_cff_qrcode),
                modifier = Modifier
                    .width(dimensionResource(R.dimen.CFF))
                    .height(dimensionResource(R.dimen.CFF))
                    .padding(dimensionResource(R.dimen.button_interior_padding))
            )*/
            Button(
                onClick = {
                    showTicket()
                },
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            )
            {
                Text(
                    text = "montrer le ticket",
                    modifier = Modifier
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Text(
                text = stringResource(R.string.helpie_ticket),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(300.dp)
            )

        }

    }

}

@Preview
@Composable
fun TicketPreview() {
    AppTheme {
        TicketScreen(
        )
    }
}