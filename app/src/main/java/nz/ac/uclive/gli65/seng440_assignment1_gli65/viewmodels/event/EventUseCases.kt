package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.use_case.*

data class EventUseCases(
    val getEvents: GetEvents,
    val deleteEvent: DeleteEvent,
    val addEvent: AddEvent,
    val getEvent: GetEvent,
)