package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.use_case

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.IEventRepository

class AddEvent(
    private val repository: IEventRepository
) {

    suspend operator fun invoke(event: Event) {
        repository.insert(event)
    }
}