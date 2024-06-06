package com.example.helpie.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
    take: Boolean,
    easyRide: Boolean

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
            if(easyRide) {
                CustomTextView(
                    text = if (take) {
                        stringResource(R.string.activer_le_billet)
                    } else {
                        stringResource(R.string.arr_ter_le_ticker)
                    },
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))

                TemplateButton(
                    onClick = { takeTicket() },
                    text = if (take) {
                        stringResource(R.string.prendre_le_ticket)
                    } else {
                        stringResource(R.string.arr_ter_le_ticket)
                    },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    padding = false,
                    sizeButton = "huge"
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))

                CustomTextView(
                    text = if (take) {
                        stringResource(R.string.fais_glisser_de_gauche_droite)
                    } else {
                        stringResource(R.string.fais_glisser_de_droite_gauche)
                    },
                    color = MaterialTheme.colorScheme.onSurface
                )
            } else {
                Box(
                    modifier = Modifier
                        .width(250.dp)
                        .height(100.dp)
                        .background(color = Color.Black, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    CustomTextView(
                        text = stringResource(R.string.prend_un_ticket),
                        size = 30.sp,
                        padding = false,
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))

                CustomTextView(
                    text = stringResource(R.string.utilise_la_machine_l_arr_t),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TakeTicketScreenPreview() {
    AppTheme {
        TakeTicketScreen(
            easyRide = false,
            take = true
        )
    }
}
