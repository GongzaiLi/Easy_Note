package nz.ac.uclive.gli65.seng440_assignment1_gli65.views

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder

import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen.AddCategoryScreen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen.HomeScreen

@ExperimentalAnimationApi
@Composable
fun Navigation() {

    val navController = rememberAnimatedNavController()
    //val navController = rememberAnimatedNavController()

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
            popEnterTransition = { initial, _ ->
                slideInHorizontally(
                    initialOffsetX = { -width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            enterTransition = { initial, _ ->
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
            route = Screen.AddCategoryScreen.route,
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = { _, target ->
                slideOutHorizontally(
                    targetOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {


            AddCategoryScreen(navController = navController)

        }

    }
/*


        composable(route = Screen.DetailScreen.route + "?name={name}", //?name={name}
            arguments = listOf( // /{} /{}
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Philipp"
                }
            )) { entry ->
            DetailScreen(name = entry.arguments?.getString("name"))
        }

        composable(
            route = Screen.EventScreen.route + "?category={category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )) { entry ->
            EventScreen(
                categoryId = entry.arguments?.getInt("category"),
                navController = navController
            )
        }
    }

 */
}




