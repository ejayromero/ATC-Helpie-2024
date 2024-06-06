package com.example.helpie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.TemplateButton

@Composable
fun PopUpScreen(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onStop : () -> Unit,
    onRestart : () -> Unit
)
{
    Dialog(onDismissRequest = { }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {

            Row(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Right,
            ) {
                TemplateButton(
                    onClick = {
                        onDismiss()
                    },
                    text = stringResource(id = R.string.retour),
                    size = 13.sp,
                    sizeButton = "small",
                    padding = false,
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,

                    )
            }
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.veux_tu_arr_ter_le_trajet_ou_trouver_un_nouveau_chemin),
                    modifier = modifier.padding(16.dp),
                )
                Spacer(modifier = modifier.height(25.dp))

                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Column {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(id = R.string.edit),
                            modifier = modifier.align(Alignment.CenterHorizontally),
                        )
                        TemplateButton(
                            onClick = {
                                onStop() },
                            text = stringResource(id = R.string.arr_ter),
                            size = 13.sp,
                            sizeButton = "small",
                            padding = false,
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    }

                    Column {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = stringResource(id = R.string.edit),
                            modifier = modifier.align(Alignment.CenterHorizontally)
                        )
                        TemplateButton(
                            onClick = {
                                onRestart() },
                            text = stringResource(id = R.string.nouveau),
                            size = 13.sp,
                            sizeButton = "small",
                            padding = false,
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopUpScreenPreview() {
    AppTheme {
        PopUpScreen(
            onRestart = {},
            onStop = {},
            onDismiss = {}
        )
    }
}



