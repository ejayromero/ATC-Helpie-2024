package com.example.helpie.ui
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme


@Composable
fun TakeTicketScreen(
    modifier: Modifier = Modifier,
    takeTicket: () -> Unit = {}

) {

    Box(
        modifier = modifier
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Prendre le ticket",
                modifier = Modifier,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            /*Text(
                text = "tu vas etre rediriger vers le ticket",
                modifier = Modifier,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )*/
            Button(
                onClick = {
                    takeTicket()
                },
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            )
            {
                Text(
                    text = "prendre le ticket",
                    modifier = Modifier
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun TakeTicketScreenPreview() {
    AppTheme {
        TakeTicketScreen(
        )
    }
}
