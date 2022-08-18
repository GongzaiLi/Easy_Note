package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event

data class EventState(
    val categories: List<Category> = emptyList(),
    val events: List<Event> = emptyList(),
)
