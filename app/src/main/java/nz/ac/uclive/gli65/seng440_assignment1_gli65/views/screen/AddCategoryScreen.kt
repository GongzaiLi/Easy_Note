package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.Screen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.DrawerBody
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.DrawerFooter
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.DrawerHeader
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.ScreenTopBarRow

@Composable
fun AddCategoryScreen(
    navController: NavController,
) {
    AddCategoryScreenScaffold(navController)
}

@Composable
fun AddCategoryScreenScaffold(navController: NavController) {
    Scaffold(
        topBar = {
            ScreenTopBarRow(
                "ic_arrow_back_24",
                stringResource(R.string.category_edit),
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                }
            )
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
        //Test(navController = navController) // todo
        //navigation

        //ScreenBody(events = events)
    }
}
