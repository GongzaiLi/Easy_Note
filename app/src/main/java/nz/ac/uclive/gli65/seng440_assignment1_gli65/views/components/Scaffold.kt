package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.BlueLight
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screens.Test

//@Preview(showBackground = true)
@Composable
fun ScreenScaffold(screenName: String, navController: NavController) {

    val scaffoldState = rememberScaffoldState() //  scaffold state
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = BlueLight
            ) {
                TopBarRow(scope, scaffoldState, screenName) // todo screen name
            }
        },
        drawerContent = {
            Row {
                Text(text = "here") // todo Add Row
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {/* todo to create todo */ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add", // todo description
                    tint = Color.White
                )
            }
        },
    ) {
        // Screen content
        Test(navController = navController) // todo
        //navigation
    }
}

/**
 * Top Bar Row Ui
 */
@Composable
fun TopBarRow(scope: CoroutineScope, scaffoldState: ScaffoldState, screenName: String) {
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

        Text(text = screenName, color = Color.White, textAlign = TextAlign.Center,
            modifier = Modifier.width(150.dp), fontSize = 16.sp )

    }
}