package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event

data class EventState(
    val events: List<Event> = emptyList(), // todo order and section visible
)
