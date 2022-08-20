package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.BlueLight
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.DrawerBody
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.DrawerFooter
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.DrawerHeader


@Composable
fun HomeScreen(
    navController: NavController,
) {
    ScreenScaffold(navController = navController)
}

@Composable
fun ScreenScaffold(
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {

    val categoryState = categoryViewModel.state.value

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val screenName: String = categoryState.screenName
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarRow(
                scope,
                scaffoldState,
                stringResource(R.string.main_screen_name, screenName)
            ) // todo screen name
        },
        drawerShape = customShape(),
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                categories = categoryState.categories,
                onClick = {
                    categoryViewModel.onEvent(CategoryEvent.PickCategory(it))

                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    //navController.navigate(Screen.EventScreen.route + "?category=${it.id}") todo
                }
            )
            DrawerFooter()
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

/**
 * https://solveforum.com/forums/threads/solved-how-set-width-to-drawer-in-jetpack-compose.350905/#post-350930
 */
@Composable
fun customShape() = object : Shape {
    val configuration = LocalConfiguration.current

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val right = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                size.width * 4f / 10f
            }
            else -> {
                size.width * 9f / 10f
            }
        }

        return Outline.Rectangle(
            Rect(
                left = 0f,
                top = 0f,
                right = right,
                bottom = size.height
            )
        )
    }
}


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