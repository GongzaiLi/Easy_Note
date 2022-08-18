package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.category

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories.ICategoryRepository
import nz.ac.uclive.gli65.seng440_assignment1_gli65.utils.InvalidException
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.ToggleEvent

class AddCategoryUseCase(private val repository: ICategoryRepository) {

    @Throws(InvalidException::class)
    suspend operator fun invoke(category: Category) {
        if (category.title.isBlank()) {
            throw InvalidException("The title cannot be empty!")
        }
        repository.insertCategory(category)
    }

}