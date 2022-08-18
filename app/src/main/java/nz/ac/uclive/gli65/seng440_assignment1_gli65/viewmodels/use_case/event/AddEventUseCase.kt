package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.event

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories.IEventRepository
import nz.ac.uclive.gli65.seng440_assignment1_gli65.utils.InvalidException

class AddEventUseCase(private val repository: IEventRepository) {

    @Throws(InvalidException::class)
    suspend operator fun invoke(event: Event) { // todo return some value
        if (event.title.isBlank()) {
            throw InvalidException("The title cannot be empty!")
        }
        repository.insertEvent(event)
    }
}