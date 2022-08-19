package nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor") // todo
private val LightColorPalette = lightColors(
    primary = Color.White,
    background = Color.White,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.White,
    //primaryVariant = Color.Blue,
)

@Composable
fun Seng440_assignment1_gli65Theme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}