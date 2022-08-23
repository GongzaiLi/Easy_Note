package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.*

data class EventState(
    val colors: List<Color> = listOf(
        RedOrange,
        LightGreen,
        Violet,
        BabyBlue,
        RedPink,
        TextWhite,
    ), // const value


    val events: List<Event> = emptyList(),
    val isHintVisible: Boolean = true,
    val selectedColor: Int = TextWhite.toArgb()


)
