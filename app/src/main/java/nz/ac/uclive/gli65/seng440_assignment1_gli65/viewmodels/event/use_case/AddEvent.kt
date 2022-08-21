package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.use_case

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.IEventRepository
import nz.ac.uclive.gli65.seng440_assignment1_gli65.util.exceptions.InvalidCategoryException

class AddEvent(
    private val repository: IEventRepository
) {
    @Throws(InvalidCategoryException::class)
    suspend operator fun invoke(event: Event) {
        if (event.title.isBlank()) {
            throw InvalidCategoryException("The title cannot be empty")
        }
        repository.insert(event)
    }
}