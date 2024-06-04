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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton
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
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomTextView(
                text = "Marche jusqu'à la prochaine étape",
                color = MaterialTheme.colorScheme.onSurface
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
                        CustomTextView(
                            text = stepInfo.endName.toString(),
                            padding = false,
                            size = 16.sp
                            )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            TemplateButton(
                onClick = { lauchMaps() },
                text = "Lancer la navigation",
                padding = false,
                sizeButton = "huge"
                )
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
