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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Right,
            ) {
                TemplateButton(
                    onClick = {
                        onDismiss()
                    },
                    text = "Retour",
                    size = 13.sp,
                    sizeButton = "small",
                    padding = false,
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,

                    )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "veux tu arrêter le trajet ou trouver un nouveau chemin ?",
                    modifier = Modifier.padding(16.dp),
                )
                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Column {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Edit Mode",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                        )
                        TemplateButton(
                            onClick = {
                                onStop() },
                            text = "Arrêter",
                            size = 13.sp,
                            sizeButton = "small",
                            padding = false,
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    }

                    Column {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Edit Mode",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        TemplateButton(
                            onClick = {
                                onRestart() },
                            text = "nouveau",
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



