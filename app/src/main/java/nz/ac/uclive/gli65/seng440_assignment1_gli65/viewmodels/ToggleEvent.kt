package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event

sealed class ToggleEvent {
    // delete
    data class DeleteEvent(val event: Event) : ToggleEvent()
    data class DeleteCategory(val category: Category) : ToggleEvent()

    // add
    data class AddEvent(val event: Event) :ToggleEvent()
    data class AddCategory(val category: Category) :ToggleEvent()
}