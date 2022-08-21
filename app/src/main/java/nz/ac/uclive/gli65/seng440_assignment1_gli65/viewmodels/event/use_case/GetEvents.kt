package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.use_case

import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.IEventRepository


class GetEvents(
    private val repository: IEventRepository
) {
    operator fun invoke(id: Long): Flow<List<Event>> {

        if (id == 1L) { // all type
            return repository.findAll()
        }
        return repository.getEventByCategoryId(id)
    }
}