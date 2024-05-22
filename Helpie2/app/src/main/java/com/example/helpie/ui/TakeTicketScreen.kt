package com.example.helpie.ui
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton


@Composable
fun TakeTicketScreen(
    modifier: Modifier = Modifier,
    takeTicket: () -> Unit = {},
    take: Boolean = true

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
            CustomTextView(
                text = if (take) {
                    "Activer le billet Easyride"
                } else {
                    "Arrêter Easyride"
                },
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))

            TemplateButton(
                onClick = { takeTicket() },
                text = if (take) {
                    "Prendre le ticket"
                } else {
                    "Arrêter le ticket"
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                padding = false,
                sizeButton = "huge"
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))

            CustomTextView(
                text = if (take) {
                    "Fais glisser de gauche à droite"
                } else {
                    "Fais glisser de droite à gauche"
                },
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TakeTicketScreenPreview() {
    AppTheme {
        TakeTicketScreen(
        )
    }
}
