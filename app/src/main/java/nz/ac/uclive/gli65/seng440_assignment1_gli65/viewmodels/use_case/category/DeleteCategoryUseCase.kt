package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.category

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories.ICategoryRepository

class DeleteCategoryUseCase(private val repository: ICategoryRepository) {

    suspend operator fun invoke(category: Category) {
        repository.deleteCategory(category)
    }
}
