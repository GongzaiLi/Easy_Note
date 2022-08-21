package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.use_case


import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.IEventRepository

class GetEvent(
    private val repository: IEventRepository
) {
    // event may be none
    suspend operator fun invoke(id: Long): Event? {
        return repository.findById(id)
    }
}