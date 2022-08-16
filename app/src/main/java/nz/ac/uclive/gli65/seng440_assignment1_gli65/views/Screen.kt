package nz.ac.uclive.gli65.seng440_assignment1_gli65.views

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")

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
