package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.BlueLight


@Composable
fun ScreenTopBarRow(
    icon: String,
    screenName: String,
    onClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = BlueLight
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    onClick()
                }) {
                Icon(
                    painterResource(id = getIconFromDrawable(icon)),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = screenName,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

        }
    }
}