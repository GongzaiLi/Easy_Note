package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.ICategoryRepository

class AddUseCase(
    private val repository: ICategoryRepository
) {

    suspend operator fun invoke(category: Category) {
        repository.insertCategory(category)
    }

}