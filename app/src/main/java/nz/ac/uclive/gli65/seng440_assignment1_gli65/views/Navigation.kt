package nz.ac.uclive.gli65.seng440_assignment1_gli65.views

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen.AddCategoryScreen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen.HomeScreen

@ExperimentalAnimationApi
@Composable
fun Navigation() {

    val navController = rememberNavController()
    //val navController = rememberAnimatedNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            Screen.HomeScreen.route,
        ) {
            EnterAnimation {
                HomeScreen(navController = navController)
            }

        }

        composable(route = Screen.AddCategoryScreen.route) {

            EnterAnimation {
                AddCategoryScreen(navController = navController)
            }

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

@ExperimentalAnimationApi
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}

