package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.components

import android.content.res.Configuration
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.geometry.Size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Density
import androidx.navigation.NavController
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.Screen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screens.Test
import java.time.LocalDateTime


@Composable
fun ScreenScaffold(screenName: String, navController: NavController) {

    val categoriesList: List<Category> = listOf<Category>( // todo
        Category(1, "type 1", "here is Type 1", icon = "ic_all_type_24"),
        Category(2, "type 2", "here is Type 2", icon = "ic_favorite_24"),
        Category(3, "type 3", "here is Type 3", icon = "ic_star_24"),
    )

    val events: List<Event> = listOf(
        Event(1, "event1asdsadshadkhakjshdjkaasdasdsadsa", LocalDateTime.now(), "Event 1 d", 1),
        Event(2, "event2", LocalDateTime.now(), "Event 1 d", 1),
        Event(3, "event3", LocalDateTime.now(), "Event 1 d", 1),
        Event(3, "event4", LocalDateTime.now(), "Event 1 d", 1),
        Event(3, "event5", LocalDateTime.now(), "Event 1 d", 1),
        Event(3, "event6", LocalDateTime.now(), "Event 1 d", 1),
        Event(3, "event7", LocalDateTime.now(), "Event 1 d", 1),
        Event(3, "event8", LocalDateTime.now(), "Event 1 d", 1),
        Event(3, "event9", LocalDateTime.now(), "Event 1 d", 1),
        Event(3, "event10", LocalDateTime.now(), "Event 1 d", 1),
        Event(3, "event11", LocalDateTime.now(), "Event 1 d", 1),
    )

    val scaffoldState = rememberScaffoldState() //  scaffold state
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarRow(scope, scaffoldState, screenName) // todo screen name
        },
        drawerShape = customShape(),
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                categories = categoriesList,
                onClick = {
                    navController.navigate(Screen.EventScreen.route + "?category=${it.id}")
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

        ScreenBody(events = events)
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



