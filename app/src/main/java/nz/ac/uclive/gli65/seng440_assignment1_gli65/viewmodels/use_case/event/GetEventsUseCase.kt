package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.event

import androidx.lifecycle.LiveData
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories.IEventRepository

class GetEventsUseCase( private val repository: IEventRepository) {

    operator fun invoke(): LiveData<List<Event>> { // todo can be
        return repository.getEvents()
    }
}