package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.BlueLight

/**
 * Top Bar Row Ui
 */
@Composable
fun TopBarRow(scope: CoroutineScope, scaffoldState: ScaffoldState, screenName: String) {
    TopAppBar(
        backgroundColor = BlueLight
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Menu", // Todo Icon description
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