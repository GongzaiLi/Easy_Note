package nz.ac.uclive.gli65.seng440_assignment1_gli65.views

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument

import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen.AddEventScreen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen.CategoryScreen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen.HomeScreen

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun Navigation() {

    val navController = rememberAnimatedNavController()

    val width = 300

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            Screen.HomeScreen.route,
            exitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { -width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { -width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { -width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.CategoryScreen.route,
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {

            CategoryScreen(navController = navController)

        }


        composable(
            route = Screen.AddEventScreen.route + "?eventId={eventId}&eventColor={eventColor}&categoryId={categoryId}&isEdit={isEdit}",
            arguments = listOf(
                navArgument(
                    name = "eventId",
                ) {
                    type = NavType.LongType
                    defaultValue = -1L
                },
                navArgument(
                    name = "eventColor",
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "categoryId",
                ) {
                    type = NavType.LongType
                    defaultValue = 1L
                },
                navArgument(
                    name = "isEdit",
                ) {
                    type = NavType.BoolType
                    defaultValue = false
                },

                ),
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {
            val color = it.arguments?.getInt("eventColor") ?: -1
            val categoryId = it.arguments?.getLong("categoryId") ?: 1
            val isEdit = it.arguments?.get("isEdit") ?: false

            AddEventScreen(
                navController = navController,
                eventColor = color,
                categoryId = categoryId,
                isEdit = isEdit as Boolean,
            )

        }

    }
}




