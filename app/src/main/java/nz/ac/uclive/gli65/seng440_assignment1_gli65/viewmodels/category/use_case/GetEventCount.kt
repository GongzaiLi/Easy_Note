package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.category.use_case

import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.IEventRepository


class GetEventCount(private val repository: IEventRepository) {

    operator fun invoke(category: Category): Flow<Int> {
        if (category.id == 1L) { // all event
            return repository.getCount()
        }
        return repository.getCountEventByCategoryId(category.id)

    }
}