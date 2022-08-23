package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

import androidx.compose.ui.focus.FocusState
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event

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
