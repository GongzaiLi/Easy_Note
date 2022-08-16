package nz.ac.uclive.gli65.seng440_assignment1_gli65.views

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.Navigation

@Composable
fun TopBar()
{
    val scaffoldState = rememberScaffoldState() // todo Open or Close
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                Row {
                    IconButton(
                        onClick = {
                            scope.launch {
                                //scaffoldState.drawer.State.open()
                                scaffoldState.drawerState.open()
                            }
                        }) {
                        Icon(
                            Icons.Filled.Menu,
                            "contentDescription",
                            //tint = Color.White
                        )
                    }
                    Text(text = "Main Screen")
                }
            }
        },
        drawerContent = {
            Row {
                Text(text = "here")
            }
        }
    ) {
        // Screen content
        Navigation()
    }
}