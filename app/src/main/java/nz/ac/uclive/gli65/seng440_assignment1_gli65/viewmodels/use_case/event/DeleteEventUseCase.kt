package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.event

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories.IEventRepository

class DeleteEventUseCase(private val repository: IEventRepository) {
    suspend operator fun invoke(event: Event) {
        repository.deleteEvent(event)
    }
}