package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.components.ScreenScaffold

@Composable
fun EventScreen(categoryId: Int?, navController: NavController) { // will be in check
    ScreenScaffold("Here is Event", navController)
}