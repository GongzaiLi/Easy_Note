package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.EventEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.EventViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.Screen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.*

@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    navController: NavController,
) {
    HomeScreenScaffold(navController = navController)
}


@Composable
fun HomeScreenScaffold(
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel<CategoryViewModel>(),
    eventViewModel: EventViewModel = hiltViewModel<EventViewModel>()
) {

    val categoryState = categoryViewModel.state.value
    val eventState = eventViewModel.state.value

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    categoryViewModel.onEvent(CategoryEvent.UpdateEventCount)
    eventViewModel.onEvent(EventEvent.GetEvents(categoryState.pickCategory?.id))

    val screenName: String = categoryState.screenName
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            ScreenTopBarRow(
                "ic_menu_24",
                stringResource(R.string.main_screen_name, screenName),
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                        categoryViewModel.onEvent(CategoryEvent.UpdateEventCount)
                    }
                }
            )
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
                },
                onFooterClick = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(Screen.CategoryScreen.route)
                }
            )
            //DrawerFooter(onClick = )


        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddEventScreen.route + "?categoryId=${categoryState.pickCategory?.id}") }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add", // todo description
                    tint = Color.White
                )
            }
        },
    ) {
        EventBody(events = eventState.events, navController = navController)
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
                size.width * 9f / 10f // todo
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


