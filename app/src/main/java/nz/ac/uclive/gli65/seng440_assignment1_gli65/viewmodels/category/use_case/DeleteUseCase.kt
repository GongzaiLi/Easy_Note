package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.category.use_case

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.ICategoryRepository

class DeleteUseCase(
    private val repository: ICategoryRepository
) {
    suspend operator fun invoke(category: Category) {
        repository.deleteCategory(category) // and update the event
    }

}