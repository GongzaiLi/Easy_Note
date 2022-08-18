package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.components

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
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
import java.time.LocalDateTime

@ExperimentalAnimationApi
@Composable
fun ScreenScaffold(screenName: String, navController: NavController) {

    // todo fix data
    val categoriesList: List<Category> = listOf<Category>( // todo
        Category("type 1", "here is Type 1", icon = "ic_all_type_24"),
        Category("type 2", "here is Type 2", icon = "ic_favorite_24"),
        Category("type 3", "here is Type 3", icon = "ic_star_24"),
    )

    val events: List<Event> = listOf(
        Event("event1asdsadshadkhakjshdjkaasdasdsadsa", toDateString(LocalDateTime.now()), "Event 1 d", 1),
        Event("event2", toDateString(LocalDateTime.now()), "Event 1 d", 1),
        Event("event3", toDateString(LocalDateTime.now()), "Event 1 d", 1),
        Event("event4", toDateString(LocalDateTime.now()), "Event 1 d", 1),
        Event("event5", toDateString(LocalDateTime.now()), "Event 1 d", 1),
        Event("event6", toDateString(LocalDateTime.now()), "Event 1 d", 1),
        Event( "event7", toDateString(LocalDateTime.now()), "Event 1 d", 1),
        Event( "event8", toDateString(LocalDateTime.now()), "Event 1 d", 1),
        Event( "event9", toDateString(LocalDateTime.now()), "Event 1 d", 1),
        Event( "event10", toDateString(LocalDateTime.now()), "Event 1 d", 1),
        Event( "event11", toDateString(LocalDateTime.now()), "Event 1 d", 1),
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



