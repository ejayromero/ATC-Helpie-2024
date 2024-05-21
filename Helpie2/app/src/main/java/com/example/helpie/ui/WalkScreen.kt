package com.example.helpie.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.walkInfo


@Composable
fun WalkScreen(
    modifier: Modifier = Modifier,
    stepInfo: walkInfo,
    lauchMaps: () -> Unit = {}
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
                text = "Marche jusqu'à la prochaine étape",
                color = Color.Black
            )
            Box(
                modifier = Modifier
                    .width(250.dp)
                    .height(100.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)

            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    val (iconResource, contentDescription) = Pair(R.drawable.walking_icon, "Walk Icon")

                    Image(
                        painter = painterResource(id = iconResource),
                        contentDescription = contentDescription,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(60.dp)
                            .fillMaxSize()
                    )
                    // Spacer to create space between icon and text
                    Spacer(modifier = Modifier.width(28.dp))
                    Column {
                        // Bus line text
                        Text(
                            text = stepInfo.endName.toString(),
                            color = Color.White,
                            fontSize = 16.sp,

                            )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    lauchMaps()
                },
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            )
            {
                Text(
                    text = "Lancer la navigation",
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


@Preview(showBackground = true)
@Composable
fun WalkScreenPreview() {
    AppTheme {
        WalkScreen(
            stepInfo = walkInfo(
                startName = "Saint-Sulpice, Innovation Park",
                endName = "Morges, Gare",
                mode = "walk"
            )
        )
    }
}
