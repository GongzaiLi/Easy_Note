package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

import android.app.admin.NetworkEvent
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryEvent

sealed class EventEvent {
    data class DeleteEvent(val event: Event) : EventEvent()
    data class GetEvents(val id: Long?) : EventEvent()
    data class AddEvent(val time: String, val categoryId: Long) : EventEvent()

    // add and edit event
    data class EnteredTitle(val text: String) : EventEvent()
    data class ChangeTitleFocus(val focusState: FocusState): EventEvent()
    data class EnteredDescription(val text: String) : EventEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): EventEvent()
    data class ChangeColor(val color: Int): EventEvent()


}
