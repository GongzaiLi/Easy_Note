package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.Screen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.components.ScreenScaffold


@Composable
fun HomeScreen(navController: NavController) {
    ScreenScaffold("Here is Main", navController)
}


/**
 * todo screen Ui detail here
 */
@Composable
fun Test(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 10.dp)
    ) {
        TextField(
            value = text, onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            //navController.navigate(Screen.DetailScreen.withArgs(text))
            navController.navigate(Screen.DetailScreen.route + "?name=$text")

        }, modifier = Modifier.align(Alignment.End)) {
            Text(text = "To DetailScreen")
        }
    }
}