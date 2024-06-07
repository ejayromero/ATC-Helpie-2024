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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpie.R
import com.example.helpie.ui.theme.AppTheme
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton
import com.example.helpie.walkInfo


/**
 * A composable function representing the WalkScreen.
 * This screen displays information and options for walking to the next step.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param stepInfo The information about the current walking step.
 * @param lauchMaps The callback function to launch maps when the "Navigate" button is clicked.
 */
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

            // Text displaying the instruction to walk to the next step

            CustomTextView(
                text = stringResource(R.string.marche_jusqu_la_prochaine_tape),
                color = MaterialTheme.colorScheme.onSurface
            )
            // Box containing the walking icon and destination information

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

                    val (iconResource, contentDescription) = Pair(R.drawable.walking_icon, stringResource(
                        id = R.string.walk
                    ))

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
                text = stringResource(id = R.string.nave),
                padding = false,
                sizeButton = "huge"
                )
        }
    }
}


/**
 * A preview composable function for the WalkScreen.
 * This function is annotated with @Preview and @Composable, allowing it to be previewed in Android Studio's Layout Editor.
 * It displays a preview of the WalkScreen wrapped inside the AppTheme.
 *
 * @see WalkScreen
 */
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
