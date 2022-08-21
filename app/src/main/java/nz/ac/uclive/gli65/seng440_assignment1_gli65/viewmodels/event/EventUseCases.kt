package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.use_case.AddEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.use_case.DeleteEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.use_case.GetEvents
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.use_case.UpdateEvent

data class EventUseCases(
    val getEvents: GetEvents,
    val deleteEvent: DeleteEvent,
    val addEvent: AddEvent,
    val updateEvent: UpdateEvent,
)