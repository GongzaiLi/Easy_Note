package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

import androidx.compose.ui.focus.FocusState
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.category.CategoryEvent

sealed class EventEvent {
    data class DeleteEvent(val event: Event) : EventEvent()
    data class GetEvents(val id: Long?) : EventEvent()
    data class AddEvent(val time: String, val categoryId: Long, val isEdit: Boolean) : EventEvent()

    // add and edit event
    data class EnteredTitle(val text: String) : EventEvent()
    data class ChangeTitleFocus(val focusState: FocusState): EventEvent()
    data class EnteredDescription(val text: String) : EventEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): EventEvent()
    data class ChangeColor(val color: Int): EventEvent()
    data class SetUpEventTitle(val hint: String): EventEvent()
    data class SetUpEventDescription(val hint: String): EventEvent()
}
