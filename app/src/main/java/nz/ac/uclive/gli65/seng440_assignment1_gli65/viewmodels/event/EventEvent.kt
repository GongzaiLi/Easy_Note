package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

import android.app.admin.NetworkEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryEvent

sealed class EventEvent {
    data class DeleteEvent(val event: Event) : EventEvent()
    data class AddEvent(val event: Event) : EventEvent()
    data class UpdateEvent(val event: Event) : EventEvent()

}
