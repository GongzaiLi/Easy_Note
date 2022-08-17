package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun getIconFromDrawable(iconName: String): Int {
    val context = LocalContext.current
    val drawableId = remember(iconName) {

        context.resources.getIdentifier(
            iconName,
            "drawable",
            context.packageName
        )
    }
    return drawableId
}