package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.ICategoryRepository
import nz.ac.uclive.gli65.seng440_assignment1_gli65.util.exceptions.InvalidCategoryException

class AddUseCase(
    private val repository: ICategoryRepository
) {
    @Throws(InvalidCategoryException::class)
    suspend operator fun invoke(category: Category) {
        if (category.title.isBlank()) {
            throw InvalidCategoryException("The title cannot be empty")
        }
        repository.updateCategory(category)
    }

}