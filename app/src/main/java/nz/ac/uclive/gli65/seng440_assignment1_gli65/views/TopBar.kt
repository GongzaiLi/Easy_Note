package nz.ac.uclive.gli65.seng440_assignment1_gli65.views

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.Navigation
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.BlueLight

@Composable
fun TopBar() {
    val scaffoldState = rememberScaffoldState() // todo Open or Close
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(backgroundColor = BlueLight) {
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
                            contentDescription = "Menu",
                            tint = Color.White
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
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {/* todo*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    tint = Color.White
                )
            }
        },
    ) {
        // Screen content
        Navigation()
    }
}



