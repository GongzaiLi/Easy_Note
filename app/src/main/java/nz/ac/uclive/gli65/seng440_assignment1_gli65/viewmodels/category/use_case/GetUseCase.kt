package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.category.use_case

import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.ICategoryRepository


class GetUseCase(private val repository: ICategoryRepository) {

    operator fun invoke(): Flow<List<Category>> {
        return repository.getCategories()
    }
}