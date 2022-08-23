package nz.ac.uclive.gli65.seng440_assignment1_gli65.views

/**
 * sealed likes enum
 */
sealed class Screen(val route: String) {
    object HomeScreen : Screen("main_screen")
    object CategoryScreen : Screen("category_screen")
    object AddEventScreen : Screen("add_event_screen")
}
