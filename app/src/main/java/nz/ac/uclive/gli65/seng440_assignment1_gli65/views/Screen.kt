package nz.ac.uclive.gli65.seng440_assignment1_gli65.views

/**
 * sealed likes enum
 */
sealed class Screen(val route: String) {
    object HomeScreen : Screen("main_screen")
    object AddCategoryScreen : Screen("add_category_screen")
    //object EventScreen : Screen("event_screen")

    // more screen
    /*
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }

        }
    }
    */
}
