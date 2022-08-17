package nz.ac.uclive.gli65.seng440_assignment1_gli65


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.Screen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screens.DetailScreen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screens.HomeScreen
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screens.EventScreen


@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) { // set up start

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

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
}



